package org.example.polling_app.controller;

import org.example.polling_app.model.PollResponse;
import org.example.polling_app.repository.PollResponseRepository;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/poll-responses")
@CrossOrigin(origins = "*")
public class PollResponseController {

    private final PollResponseRepository repository;

    public PollResponseController(PollResponseRepository repository) {
        this.repository = repository;
    }

    @PostMapping
    public PollResponse save(@RequestBody PollResponse response) {
        return repository.save(response);
    }

    @GetMapping
    public List<PollResponse> getAll() {
        return repository.findAll();
    }

    @GetMapping("/{questionId}")
    public List<PollResponse> getByQuestion(@PathVariable Long questionId) {
        return repository.findByQuestionId(questionId);
    }
}