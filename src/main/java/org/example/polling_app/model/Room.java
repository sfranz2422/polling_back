package org.example.polling_app.model;

import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
public class Room {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;



    @Column(unique = true, nullable = false)
    private String roomCode;

    private String roomName;
    private LocalDateTime createdAt = LocalDateTime.now();

    public Room() {}

    public Room(String roomCode, String roomName) {

        this.roomCode = roomCode;
        this.roomName = roomName;
    }

    public Long getId() {
        return id;
    }


    public String getRoomCode() {
        return roomCode;
    }
    public void setRoomCode(String roomCode) {
        this.roomCode = roomCode;
    }
    public String getRoomName() {
        return roomName;
    }
    public void setRoomName(String roomName) {
        this.roomName = roomName;
    }
    public LocalDateTime getCreatedAt() {
        return createdAt;
    }
    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }







}
