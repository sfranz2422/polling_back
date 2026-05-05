package org.example.polling_app.controller;

import org.example.polling_app.model.PollQuestion;
import org.example.polling_app.repository.PollQuestionRepository;
import org.example.polling_app.security.JwtUtil;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/questions")
@CrossOrigin("*")
public class PollQuestionController {

    private final PollQuestionRepository pollQuestionRepository;
    private final JwtUtil jwtUtil;

    public PollQuestionController(
            PollQuestionRepository pollQuestionRepository,
            JwtUtil jwtUtil
    ) {
        this.pollQuestionRepository = pollQuestionRepository;
        this.jwtUtil = jwtUtil;
    }

    @GetMapping("/my")
    public List<PollQuestion> getMyQuestions(@RequestHeader("Authorization") String token) {
        Long teacherId = jwtUtil.extractTeacherId(token);
        return pollQuestionRepository.findByTeacherId(teacherId);
    }

    @PostMapping
    public PollQuestion createQuestion(
            @RequestHeader("Authorization") String token,
            @RequestBody PollQuestion question
    ) {
        Long teacherId = jwtUtil.extractTeacherId(token);
        question.setTeacherId(teacherId);
        return pollQuestionRepository.save(question);
    }

    @GetMapping("/active")
    public PollQuestion getActiveQuestion() {
        return pollQuestionRepository.findTopByOrderByIdDesc();
    }

    @GetMapping("/{id}")
    public PollQuestion getQuestionById(@PathVariable Long id) {
        return pollQuestionRepository.findById(id).orElse(null);
    }
}