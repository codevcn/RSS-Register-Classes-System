package com.example.demo.controllers;

import com.example.demo.DTOs.request.AddRegisterSessionDTO;
import com.example.demo.DTOs.request.NewTermDTO;
import com.example.demo.DTOs.request.StudentCancelRegisterDTO;
import com.example.demo.DTOs.response.RegisterSessionDTO.GeNewTermForStudentDTO;
import com.example.demo.DTOs.response.RegisterSessionDTO.GetClassOfMajorDTO;
import com.example.demo.DTOs.response.RegisterSessionDTO.GetMajorResDTO;
import com.example.demo.DTOs.response.RegisterSessionDTO.GetSubjectOfMajorDTO;
import com.example.demo.DTOs.response.RegisterSessionDTO.GetTeacherOfMajorDTO;
import com.example.demo.DTOs.response.RegisterSessionDTO.ResultOfRegisterNewTermDTO;
import com.example.demo.DTOs.response.RegisterSessionDTO.RoomForRegisterSessionDTO;
import com.example.demo.DTOs.response.RegisterSessionDTO.SearchRegisterSessionDTO;
import com.example.demo.DTOs.response.SuccessResDTO;
import com.example.demo.models.Major;
import com.example.demo.models.StudentClass;
import com.example.demo.models.Subject;
import com.example.demo.services.RegisterSessionService;
import com.example.demo.utils.exceptions.CustomBaseException;
import jakarta.validation.Valid;
import java.security.Principal;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/register-session")
public class RegisterSessionController {

    @Autowired
    private RegisterSessionService registerSessionService;

    @PostMapping("add-register-session")
    public ResponseEntity<SuccessResDTO> addRegisterSession(
        @Valid @RequestBody AddRegisterSessionDTO addRegisterSessionDTO) throws CustomBaseException {
        registerSessionService.addRegisterSession(addRegisterSessionDTO);
        return ResponseEntity.ok(new SuccessResDTO(true));
    }

    @GetMapping("get-majors")
    public ResponseEntity<List<GetMajorResDTO>> getMajors() {
        List<Major> fetchedMajors = registerSessionService.getMajors();
        List<GetMajorResDTO> majors = new ArrayList<>();
        for (Major major : fetchedMajors) {
            majors.add(new GetMajorResDTO(major.getId(), major.getMajorCode(), major.getName()));
        }
        return ResponseEntity.ok(majors);
    }

    @GetMapping("get-classes/{majorID}")
    public ResponseEntity<List<GetClassOfMajorDTO>> getClassesOfMajor(@PathVariable("majorID") String majorID) {
        List<StudentClass> studentClasses = registerSessionService.getClassesOfMajor(Long.parseLong(majorID));
        List<GetClassOfMajorDTO> classesOfMajor = new ArrayList<>();
        for (StudentClass studentClass : studentClasses) {
            classesOfMajor.add(new GetClassOfMajorDTO(studentClass.getId(), studentClass.getCode()));
        }
        return ResponseEntity.ok(classesOfMajor);
    }

    @GetMapping("get-subjects/{majorID}")
    public ResponseEntity<List<GetSubjectOfMajorDTO>> getSubjectsOfMajor(@PathVariable("majorID") String majorID) {
        List<Subject> subjects = registerSessionService.getSubjectsOfMajor(Long.parseLong(majorID));
        List<GetSubjectOfMajorDTO> subjectsOfMajor = new ArrayList<>();
        for (Subject subject : subjects) {
            subjectsOfMajor.add(new GetSubjectOfMajorDTO(subject.getId(), subject.getSubjectCode(), subject.getName()));
        }
        return ResponseEntity.ok(subjectsOfMajor);
    }

    @GetMapping("get-teachers/{majorID}")
    public ResponseEntity<List<GetTeacherOfMajorDTO>> getTeachersOfMajor(@PathVariable("majorID") String majorID) {
        List<GetTeacherOfMajorDTO> teachers = registerSessionService.getTeachersOfMajor(Long.parseLong(majorID));
        return ResponseEntity.ok(teachers);
    }

    @GetMapping("search-register-session/{regSessCode}")
    public ResponseEntity<SearchRegisterSessionDTO> searchRegisterSession(
        @PathVariable("regSessCode") String regSessCode) throws CustomBaseException {
        if (regSessCode == null) {
            throw new CustomBaseException("ID đợt đăng ký không thể trống!");
        }
        SearchRegisterSessionDTO registerSession = registerSessionService.searchRegisterSession(regSessCode);
        return ResponseEntity.ok(registerSession);
    }

    @GetMapping("get-rooms")
    public ResponseEntity<List<RoomForRegisterSessionDTO>> getRoomsForRegisterSession() {
        List<RoomForRegisterSessionDTO> rooms = registerSessionService.getRoomsForRegisterSession();
        return ResponseEntity.ok(rooms);
    }

    @GetMapping("get-reg-sess-new-term")
    public ResponseEntity<GeNewTermForStudentDTO> getRegisterSessionForNewTerm(Principal principal)
        throws CustomBaseException {
        String accountUsername = principal.getName();
        if (accountUsername == null) {
            throw new CustomBaseException("ID sinh viên không thể trống!");
        }
        GeNewTermForStudentDTO newTerm = registerSessionService.getRegisterSessionForNewTerm(accountUsername);
        return ResponseEntity.ok(newTerm);
    }

    @PostMapping("reg-new-term")
    public ResponseEntity<SuccessResDTO> registerNewTerm(Principal principal,
        @Valid @RequestBody NewTermDTO registerNewTermDTO) throws CustomBaseException {
        String accountUsername = principal.getName();
        if (accountUsername == null) {
            throw new CustomBaseException("ID sinh viên không thể trống!");
        }
        registerSessionService.registerNewTerm(registerNewTermDTO.getRegisterNewTermDTOs(),
            Long.parseLong(registerNewTermDTO.getRegSessID()), accountUsername);
        return ResponseEntity.ok(new SuccessResDTO(true));
    }

    @GetMapping("get-result-new-term/{regSessID}")
    public ResponseEntity<ResultOfRegisterNewTermDTO> getResultOfNewTerm(@PathVariable("regSessID") String regSessID,
        Principal principal) throws CustomBaseException {
        String accountUsername = principal.getName();
        if (accountUsername == null || regSessID == null) {
            throw new CustomBaseException("Dữ liệu đầu vào không thể trống!");
        }
        ResultOfRegisterNewTermDTO registerNewTermDTO =
            registerSessionService.getResultOfNewTerm(Long.parseLong(regSessID), accountUsername);
        return ResponseEntity.ok(registerNewTermDTO);
    }

    @PostMapping("student-cancel-register")
    public ResponseEntity<SuccessResDTO> studentCancelRegister(@Valid StudentCancelRegisterDTO studentCancelRegisterDTO,
        Principal principal) throws CustomBaseException {
        String accountUsername = principal.getName();
        if (accountUsername == null) {
            throw new CustomBaseException("Dữ liệu đầu vào không thể trống!");
        }
        registerSessionService.studentCancelRegister(accountUsername,
            Long.parseLong(studentCancelRegisterDTO.getScheduleID()),
            Long.parseLong(studentCancelRegisterDTO.getRegSessID()));
        return ResponseEntity.ok(new SuccessResDTO(true));
    }
}
