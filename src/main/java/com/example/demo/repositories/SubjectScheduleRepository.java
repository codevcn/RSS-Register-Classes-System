package com.example.demo.repositories;

import com.example.demo.models.SubjectSchedule;
import java.time.LocalDate;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;

public interface SubjectScheduleRepository extends JpaRepository<SubjectSchedule, Long> {
    @Transactional
    @Modifying
    @Query(value = "INSERT INTO SubjectSchedule (beginDate, endDate, subjectID, dayOfWeek, numberOfSessions,"
        + " roomID, startingSession, slotsCount, partGroup, teamGroup, classID, teacherID, regSessID) "
        + "VALUES (?1, ?2, ?3, ?4, ?5, ?6, ?7, ?8, ?9, ?10, ?11, ?12, ?13)", nativeQuery = true)
    void create(LocalDate beginDate, LocalDate endDate, String subjectID, Long dayOfWeek, Long numberOfSessions,
        Long roomID, Long startingSession, Long slotsCount, String partGroup, String teamGroup, Long classID,
        String teacherID, Long regSessID);

    @Query(value = "SELECT * FROM SubjectSchedule WHERE regSessID = ?1 AND deleted = 0", nativeQuery = true)
    List<SubjectSchedule> findByRegSessID(@Param("regSessID") Long regSessID);

    @Query(value = "SELECT * FROM SubjectSchedule WHERE id IN ?1 AND deleted = 0", nativeQuery = true)
    List<SubjectSchedule> findByIDs(List<Long> IDs);

    @Query(value = "SELECT * FROM SubjectSchedule WHERE classID = ?1 AND regSessID IN ?2 AND deleted = 0",
        nativeQuery = true)
    List<SubjectSchedule> findByClassIDAndRegSessIDs(Long classID, List<Long> regSessIDs);
}
