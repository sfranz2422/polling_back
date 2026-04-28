package org.example.polling_app.controller;

import org.example.polling_app.model.PollQuestion;
import org.example.polling_app.model.PollResponse;
import org.example.polling_app.repository.PollQuestionRepository;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/questions")
@CrossOrigin(origins = "*")
public class PollQuestionController {

    private final PollQuestionRepository repository;

    public PollQuestionController(PollQuestionRepository repository) {
        this.repository = repository;
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
}