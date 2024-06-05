package com.example.demo.DTOs.response;

import java.time.LocalDate;
import java.util.List;
import com.example.demo.models.SubjectSchedule;
import java.sql.Timestamp;

public class RegisterSessionDTO {
    public static record GetMajorResDTO(Long id, String code, String name) {
    }

    public static record GetClassOfMajorDTO(Long id, String code) {
    }

    public static record GetSubjectOfMajorDTO(Long id, String code, String name) {
    }

    public static record GetTeacherOfMajorDTO(Long id, String code, String name) {
    }

    public static record SubjectOfSearchDTO(Long id, String code, String name, Long creditsCount) {
    }

    public static record TeacherOfSearchDTO(Long id, String code, String name) {
    }

    public static record RegisterSessionForSearchDTO(String code) {
    }

    public static record ScheduledSubjectOfRegSessDTO(SubjectOfSearchDTO subject, TeacherOfSearchDTO teacher,
        List<SubjectSchedule> schedules) {
    }

    public static record SearchRegisterSessionDTO(RegisterSessionForSearchDTO registerSession,
        List<ScheduledSubjectOfRegSessDTO> scheduledSubject) {
    }

    public static record RoomForRegisterSessionDTO(Long id, String roomCode) {
    }

    public static record SubjectOfNewTermDTO(Long id, String code, String name, Long creditsCount) {
    }

    public static record TeacherOfNewTermDTO(String code, String name) {
    }

    public static record ScheduleOfRegSessNewTermDTO(Long scheduleID, LocalDate beginDate, LocalDate endDate,
        SubjectOfNewTermDTO subject, String teamGroup, String partGroup, Long dayOfWeek, Long startingSession,
        Long numberOfSessions, TeacherOfNewTermDTO teacher, String classCode, String roomCode, Long slotsCount) {
    }

    public static record GeNewTermForStudentDTO(Long regSessID, List<ScheduleOfRegSessNewTermDTO> schedules) {
    }

    public static record ScheduleOfNewTermResultDTO(Long id, String teamGroup, String partGroup) {
    }

    public static record SubjectOfNewTermResultDTO(SubjectOfNewTermDTO subject, String classCode,
        Timestamp registerDate, ScheduleOfNewTermResultDTO schedule) {
    }

    public static record ResultOfRegisterNewTermDTO(Long totalSubjects, Long totalPayAmount, Long totalCredits,
        List<SubjectOfNewTermResultDTO> resultSubjects) {
    }
}
