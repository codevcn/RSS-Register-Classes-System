package com.example.demo.repositories;

import com.example.demo.models.Room;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface RoomRepository extends JpaRepository<Room, Long> {
    @Query(value = "SELECT * FROM Room WHERE roomCode = ?1", nativeQuery = true)
    Optional<Room> findByRoomCode(String roomCode);
}

