package org.example.polling_app.controller;

import org.example.polling_app.model.PollQuestion;
import org.example.polling_app.model.PollResponse;
import org.example.polling_app.model.Room;
import org.example.polling_app.repository.PollQuestionRepository;
import org.springframework.web.bind.annotation.*;
import org.example.polling_app.repository.RoomRepository;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/questions")
public class PollQuestionController {

    private final PollQuestionRepository repository;
    private final RoomRepository roomRepository;

    public PollQuestionController(PollQuestionRepository repository, RoomRepository roomRepository) {
        this.repository = repository;
        this.roomRepository = roomRepository;
    }

    // Create a question (teacher action)
    @PostMapping
    public PollQuestion create(@RequestBody PollQuestion question) {
        return repository.save(question);
    }

    // Get all questions (teacher dashboard)
    @GetMapping
    public List<PollQuestion> getAll() {
        return repository.findAll();
    }

    @GetMapping("/teacher/{teacherId}")
    public List<PollQuestion> getQuestionsByTeacher(@PathVariable Long teacherId) {
        return repository.findByTeacherId(teacherId);
    }


    @GetMapping("/room/{roomId}")
    public List<PollQuestion> getQuestionsByRoom(@PathVariable Long roomId) {
        return repository.findByRoomId(roomId);
    }

    @GetMapping("/active/{roomCode}")
    public PollQuestion getActiveQuestion(@PathVariable String roomCode) {
        Optional<Room> roomOptional = roomRepository.findByRoomCode(roomCode);

        if (roomOptional.isEmpty()) {
            return null;
        }

        Room room = roomOptional.get();

        return repository.findFirstByRoomIdOrderByCreatedAtDesc(room.getId());
    }




    @GetMapping("/{id}")
    public PollQuestion getById(@PathVariable Long id) {
        return repository.findById(id).orElse(null);
    }
}