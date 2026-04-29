package org.example.polling_app.controller;

import org.example.polling_app.model.PollQuestion;
import org.example.polling_app.model.Room;
import org.example.polling_app.repository.PollQuestionRepository;
import org.example.polling_app.repository.RoomRepository;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/rooms")
public class RoomController {

    private final RoomRepository repository;

    public RoomController(RoomRepository repository) {
        this.repository = repository;
    }

    // Create a question (teacher action)
    @PostMapping
    public Room create(@RequestBody Room room) {
        return repository.save(room);
    }

    @GetMapping("/code/{roomCode}")
    public Optional<Room> getRoomByRoomCode(@PathVariable String roomCode) {
        return repository.findByRoomCode(roomCode);
    }

}
