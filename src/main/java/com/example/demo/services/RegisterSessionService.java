package com.example.demo.services;

import java.sql.Timestamp;
import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Map;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.Set;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.demo.DTOs.request.AddRegisterSessionDTO;
import com.example.demo.DTOs.request.RegisterNewTermDTO;
import com.example.demo.DTOs.request.RegisterSessionDTO;
import com.example.demo.DTOs.request.ScheduleDTO;
import com.example.demo.DTOs.request.ScheduledSubjectDTO;
import com.example.demo.DTOs.response.RegisterSessionDTO.GeNewTermForStudentDTO;
import com.example.demo.DTOs.response.RegisterSessionDTO.GetTeacherOfMajorDTO;
import com.example.demo.DTOs.response.RegisterSessionDTO.RegisterSessionForSearchDTO;
import com.example.demo.DTOs.response.RegisterSessionDTO.ResultOfRegisterNewTermDTO;
import com.example.demo.DTOs.response.RegisterSessionDTO.RoomForRegisterSessionDTO;
import com.example.demo.DTOs.response.RegisterSessionDTO.ScheduleOfNewTermResultDTO;
import com.example.demo.DTOs.response.RegisterSessionDTO.ScheduledSubjectOfRegSessDTO;
import com.example.demo.DTOs.response.RegisterSessionDTO.SearchRegisterSessionDTO;
import com.example.demo.DTOs.response.RegisterSessionDTO.SubjectOfNewTermDTO;
import com.example.demo.DTOs.response.RegisterSessionDTO.SubjectOfNewTermResultDTO;
import com.example.demo.DTOs.response.RegisterSessionDTO.ScheduleOfRegSessNewTermDTO;
import com.example.demo.DTOs.response.RegisterSessionDTO.SubjectOfSearchDTO;
import com.example.demo.DTOs.response.RegisterSessionDTO.TeacherOfNewTermDTO;
import com.example.demo.DTOs.response.RegisterSessionDTO.TeacherOfSearchDTO;
import com.example.demo.models.Account;
import com.example.demo.models.BlockOfMajor;
import com.example.demo.models.CreditDetail;
import com.example.demo.models.Major;
import com.example.demo.models.ReceiptSubject;
import com.example.demo.models.RegisterReceipt;
import com.example.demo.models.RegisterSession;
import com.example.demo.models.Room;
import com.example.demo.models.Student;
import com.example.demo.models.StudentClass;
import com.example.demo.models.Subject;
import com.example.demo.models.SubjectSchedule;
import com.example.demo.models.Teacher;
import com.example.demo.models.TeacherMajor;
import com.example.demo.repositories.AccountRepository;
import com.example.demo.repositories.CreditDetailRepository;
import com.example.demo.repositories.MajorRepository;
import com.example.demo.repositories.ReceiptSubjectRepository;
import com.example.demo.repositories.RegisterReceiptRepository;
import com.example.demo.repositories.RegisterSessionRepository;
import com.example.demo.repositories.RoomRepository;
import com.example.demo.repositories.StudentClassRepository;
import com.example.demo.repositories.StudentRepository;
import com.example.demo.repositories.SubjectRepository;
import com.example.demo.repositories.SubjectScheduleRepository;
import com.example.demo.repositories.TeacherMajorRepository;
import com.example.demo.repositories.TeacherRepository;
import com.example.demo.utils.exceptions.CustomBaseException;


record TeacherAndSubject(Teacher teacher, Subject subject) {
}


@Service
public class RegisterSessionService {
    @Autowired
    private SubjectScheduleRepository subjectScheduleRepository;

    @Autowired
    private RegisterSessionRepository registerSessionRepository;

    @Autowired
    private MajorRepository majorRepository;

    @Autowired
    private StudentClassRepository studentClassRepository;

    @Autowired
    private SubjectRepository subjectRepository;

    @Autowired
    private TeacherMajorRepository teacherMajorRepository;

    @Autowired
    private TeacherRepository teacherRepository;

    @Autowired
    private RoomRepository roomRepository;

    @Autowired
    private StudentRepository studentRepository;

    @Autowired
    private AccountRepository accountRepository;

    @Autowired
    private RegisterReceiptRepository registerReceiptRepository;

    @Autowired
    private CreditDetailRepository creditDetailRepository;

    @Autowired
    private ReceiptSubjectRepository receiptSubjectRepository;

    public ResultOfRegisterNewTermDTO getResultOfNewTerm(Long regSessID, String accountUsername)
        throws CustomBaseException {
        Account account = accountRepository.findByUsername(accountUsername);
        Student student = studentRepository.findByUsername(account.getId());
        RegisterReceipt registerReceipt = registerReceiptRepository.findForResultOfNewTerm(regSessID, student.getId());
        if (registerReceipt == null) {
            return null;
        }
        List<ReceiptSubject> receiptSubjects =
            receiptSubjectRepository.findByRegisterReceiptID(registerReceipt.getId());
        List<Long> scheduleIDs = new ArrayList<>();
        for (ReceiptSubject receiptSubject : receiptSubjects) {
            scheduleIDs.add(receiptSubject.getSubjectSchedule().getId());
        }
        List<SubjectSchedule> subjectSchedules = subjectScheduleRepository.findByIDs(scheduleIDs);
        List<SubjectOfNewTermResultDTO> subjectsOfResult = new ArrayList<>();
        StudentClass studentClass = student.getStudentClass();
        for (SubjectSchedule subjectSchedule : subjectSchedules) {
            Subject subject = subjectSchedule.getSubject();
            subjectsOfResult.add(new SubjectOfNewTermResultDTO(
                new SubjectOfNewTermDTO(subject.getId(), subject.getSubjectCode(), subject.getName(),
                    subject.getCreditsCount()),
                studentClass.getCode(), registerReceipt.getCreatedAt(), new ScheduleOfNewTermResultDTO(
                    subjectSchedule.getId(), subjectSchedule.getTeamGroup(), subjectSchedule.getPartGroup())));
        }
        return new ResultOfRegisterNewTermDTO(registerReceipt.getTotalSubjects(), registerReceipt.getTotalPayAmount(),
            registerReceipt.getTotalCredits(), subjectsOfResult);
    }

    private void checkForRegisterNewTerm(List<RegisterNewTermDTO> registerNewTermDTOs) throws CustomBaseException {
        Set<Long> idSet = new HashSet<>();
        for (RegisterNewTermDTO registerNewTermDTO : registerNewTermDTOs) {
            if (!idSet.add(Long.parseLong(registerNewTermDTO.getSubjectID()))) {
                throw new CustomBaseException("Đăng ký trùng lặp các môn học!");
            }
        }
    }

    public void registerNewTerm(List<RegisterNewTermDTO> registerNewTermDTOs, Long regSessID, String accountUsername)
        throws CustomBaseException {
        checkForRegisterNewTerm(registerNewTermDTOs);

        Account account = accountRepository.findByUsername(accountUsername);
        Student student = studentRepository.findByUsername(account.getId());
        int totalCredits = 0;
        int totalPayAmount = 0;
        Subject subject = null;
        for (RegisterNewTermDTO registerNewTermDTO : registerNewTermDTOs) {
            Optional<Subject> fetchedSubject =
                subjectRepository.findById(Long.parseLong(registerNewTermDTO.getSubjectID()));
            try {
                subject = fetchedSubject.get();
            } catch (Exception e) {
                throw new CustomBaseException("Không tìm thấy môn học");
            }
            totalCredits += subject.getCreditsCount();
            BlockOfMajor blockOfMajor = subject.getMajor().getBlockOfMajor();
            if (blockOfMajor == null) {
                throw new CustomBaseException("Không tìm thấy khối ngành");
            }
            CreditDetail creditDetail = creditDetailRepository.findByMajorBlockID(blockOfMajor.getId());
            Long totalCreditsOfASubject = subject.getCreditsCount() * creditDetail.getPayPerCredit();
            totalPayAmount += totalCreditsOfASubject;
        }
        int totalSubjects = registerNewTermDTOs.size();
        Optional<RegisterSession> registerSession = registerSessionRepository.findById(regSessID);
        RegisterSession regSess = null;
        try {
            regSess = registerSession.get();
        } catch (Exception e) {
            throw new CustomBaseException("Không tìm thấy đơt đăng ký");
        }

        RegisterReceipt registerReceipt = new RegisterReceipt();
        registerReceipt.setTotalCredits(Long.parseLong(String.valueOf(totalCredits)));
        registerReceipt.setTotalPayAmount(Long.parseLong(String.valueOf(totalPayAmount)));
        registerReceipt.setStudent(student);
        registerReceipt.setTotalSubjects(Long.parseLong(String.valueOf(totalSubjects)));
        registerReceipt.setCreatedAt(new Timestamp(System.currentTimeMillis()));
        registerReceipt.setRegisterSession(regSess);

        RegisterReceipt registerReceiptCreated = registerReceiptRepository.save(registerReceipt);

        // data for receipt subject
        for (RegisterNewTermDTO registerNewTermDTO : registerNewTermDTOs) {
            SubjectSchedule subjectSchedule = new SubjectSchedule();
            subjectSchedule.setId(Long.parseLong(registerNewTermDTO.getScheduleID()));
            ReceiptSubject receiptSubject = new ReceiptSubject();
            receiptSubject.setReceipt(registerReceiptCreated);
            receiptSubject.setSubjectSchedule(subjectSchedule);

            receiptSubjectRepository.save(receiptSubject);
        }
    }

    public GeNewTermForStudentDTO getRegisterSessionForNewTerm(String accountUsername) throws CustomBaseException {
        Account account = accountRepository.findByUsername(accountUsername);
        if (account == null) {
            throw new CustomBaseException("Không tìm thấy tài khoản sinh viên");
        }
        Student student = studentRepository.findByUsername(account.getId());
        if (student == null) {
            
            throw new CustomBaseException("Không tìm thấy sinh viên");
        }
        StudentClass studentClass = student.getStudentClass();
        List<RegisterSession> registerSessions = registerSessionRepository.findByCurrentlyOpenings();
        if (registerSessions.size() == 0) {
            return null;
        }
        List<Long> regSessIDs = registerSessions.stream().map(RegisterSession::getId).collect(Collectors.toList());
        List<SubjectSchedule> subjectSchedules =
            subjectScheduleRepository.findByClassIDAndRegSessIDs(studentClass.getId(), regSessIDs);
        List<ScheduleOfRegSessNewTermDTO> schedules = new ArrayList<>();
        for (SubjectSchedule subjectSchedule : subjectSchedules) {
            Subject subject = subjectSchedule.getSubject();
            Teacher teacher = subjectSchedule.getTeacher();
            schedules.add(new ScheduleOfRegSessNewTermDTO(subjectSchedule.getId(), subjectSchedule.getBeginDate(),
                subjectSchedule.getEndDate(),
                new SubjectOfNewTermDTO(subject.getId(), subject.getSubjectCode(), subject.getName(),
                    subject.getCreditsCount()),
                subjectSchedule.getTeamGroup(), subjectSchedule.getPartGroup(), subjectSchedule.getDayOfWeek(),
                subjectSchedule.getStartingSession(), subjectSchedule.getNumberOfSessions(),
                new TeacherOfNewTermDTO(teacher.getTeacherCode(), teacher.getFullName()),
                subjectSchedule.getStudentClass().getCode(), subjectSchedule.getRoom().getRoomCode(),
                subjectSchedule.getSlotsCount()));
        }
        return new GeNewTermForStudentDTO(subjectSchedules.get(0).getRegisterSession().getId(), schedules);
    }

    public List<RoomForRegisterSessionDTO> getRoomsForRegisterSession() {
        List<Room> rooms = roomRepository.findAll();
        List<RoomForRegisterSessionDTO> roomForRegisterSessions = new ArrayList<>();
        for (Room room : rooms) {
            roomForRegisterSessions.add(new RoomForRegisterSessionDTO(room.getId(), room.getRoomCode()));
        }
        return roomForRegisterSessions;
    }

    public SearchRegisterSessionDTO searchRegisterSession(String regSessCode) throws CustomBaseException {
        Optional<RegisterSession> fetchedRegisterSession = registerSessionRepository.findByRegSessCode(regSessCode);
        RegisterSession registerSession = null;
        try {
            registerSession = fetchedRegisterSession.get();
        } catch (Exception e) {
            throw new CustomBaseException("Không tìm thấy đợt đăng ký");
        }

        List<SubjectSchedule> schedules = subjectScheduleRepository.findByRegSessID(registerSession.getId());

        Map<TeacherAndSubject, List<SubjectSchedule>> groupedSchedules = schedules.stream().collect(
            Collectors.groupingBy(schedule -> new TeacherAndSubject(schedule.getTeacher(), schedule.getSubject())));

        List<ScheduledSubjectOfRegSessDTO> searchRegisterSessions = new ArrayList<>();
        for (TeacherAndSubject scheduleKey : groupedSchedules.keySet()) {
            List<SubjectSchedule> schedulesItem = groupedSchedules.get(scheduleKey);
            Subject subject = scheduleKey.subject();
            SubjectOfSearchDTO subjectOfSearchDTO = new SubjectOfSearchDTO(subject.getId(), subject.getSubjectCode(),
                subject.getName(), subject.getCreditsCount());
            Teacher teacher = scheduleKey.teacher();
            TeacherOfSearchDTO teacherOfSearchDTO =
                new TeacherOfSearchDTO(teacher.getId(), teacher.getTeacherCode(), teacher.getFullName());
            searchRegisterSessions
                .add(new ScheduledSubjectOfRegSessDTO(subjectOfSearchDTO, teacherOfSearchDTO, schedulesItem));
        }

        RegisterSessionForSearchDTO registerSessionForSearchDTO = new RegisterSessionForSearchDTO(regSessCode);
        SearchRegisterSessionDTO searchRegisterSessionDTO =
            new SearchRegisterSessionDTO(registerSessionForSearchDTO, searchRegisterSessions);

        return searchRegisterSessionDTO;
    }

    public void addRegisterSession(AddRegisterSessionDTO addRegisterSessionDTO) throws CustomBaseException {
        RegisterSessionDTO registerSessionDTO = addRegisterSessionDTO.getRegisterSessionDTO();

        Timestamp beginTime = new Timestamp(Long.parseLong(registerSessionDTO.getBeginTime()));
        Timestamp endTime = new Timestamp(Long.parseLong(registerSessionDTO.getEndTime()));
        RegisterSession registerSession = new RegisterSession();
        registerSession.setBeginTime(beginTime);
        registerSession.setEndTime(endTime);
        registerSession.setRegSessCode(registerSessionDTO.getRegSessCode());
        registerSession.setCreatedAt(new Timestamp(System.currentTimeMillis()));

        RegisterSession registerSessionCreated = registerSessionRepository.save(registerSession);

        ScheduledSubjectDTO[] scheduledSubjectDTOs = addRegisterSessionDTO.getScheduledSubjectDTOs();
        for (ScheduledSubjectDTO scheduledSubject : scheduledSubjectDTOs) {
            for (ScheduleDTO scheduleDTO : scheduledSubject.getSchedules()) {
                Instant beginDate_instant = Instant.ofEpochMilli(Long.parseLong(scheduleDTO.getBeginDate()));
                LocalDate beginDate = beginDate_instant.atZone(ZoneId.systemDefault()).toLocalDate();
                Instant endDate_instant = Instant.ofEpochMilli(Long.parseLong(scheduleDTO.getEndDate()));
                LocalDate endDate = endDate_instant.atZone(ZoneId.systemDefault()).toLocalDate();

                Optional<Room> fetchedRoom = roomRepository.findByRoomCode(scheduleDTO.getRoomCode());
                Room room = null;
                try {
                    room = fetchedRoom.get();
                } catch (Exception e) {
                    throw new CustomBaseException("Không tìm thấy phòng học");
                }

                subjectScheduleRepository.create(beginDate, endDate, scheduledSubject.getSubject().getId(),
                    Long.parseLong(scheduleDTO.getDayOfWeek()), Long.parseLong(scheduleDTO.getNumberOfSessions()),
                    room.getId(), Long.parseLong(scheduleDTO.getStartingSession()),
                    Long.parseLong(scheduleDTO.getSlotsCount()), scheduledSubject.getSubjectInfo().getPartGroup(),
                    scheduledSubject.getSubjectInfo().getTeamGroup(), Long.parseLong(scheduleDTO.getForClass().getId()),
                    scheduleDTO.getTeacher().getId(), registerSessionCreated.getId());
            }
        }
    }

    public List<Major> getMajors() {
        return majorRepository.findMajors();
    }

    public List<StudentClass> getClassesOfMajor(Long majorID) {
        return studentClassRepository.findByMajorID(majorID);
    }

    public List<Subject> getSubjectsOfMajor(Long majorID) {
        return subjectRepository.findByMajorID(majorID);
    }

    public List<GetTeacherOfMajorDTO> getTeachersOfMajor(Long majorID) {
        List<TeacherMajor> teacherMajors = teacherMajorRepository.findByMajorID(majorID);
        List<GetTeacherOfMajorDTO> teachers = new ArrayList<>();
        for (TeacherMajor teacherMajor : teacherMajors) {
            Optional<Teacher> optionalTeacher = teacherRepository.findById(teacherMajor.getTeacher().getId());
            Teacher teacher = optionalTeacher.get();
            teachers.add(new GetTeacherOfMajorDTO(teacher.getId(), teacher.getTeacherCode(), teacher.getFullName()));
        }
        return teachers;
    }
}
