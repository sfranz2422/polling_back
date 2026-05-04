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
@CrossOrigin("*")
public class PollQuestionController {

    private final PollQuestionRepository pollQuestionRepository;

    public PollQuestionController(PollQuestionRepository pollQuestionRepository) {
        this.pollQuestionRepository = pollQuestionRepository;
    }

    // ✅ Get logged-in teacher's questions
    @GetMapping("/my")
    public List<PollQuestion> getMyQuestions(@RequestHeader("Authorization") String token) {

        Long teacherId = extractTeacherId(token);

        return pollQuestionRepository.findByTeacherId(teacherId);
    }

    // ✅ Create question (assign teacher automatically)
    @PostMapping
    public PollQuestion createQuestion(
            @RequestHeader("Authorization") String token,
            @RequestBody PollQuestion question) {

        Long teacherId = extractTeacherId(token);

        question.setTeacherId(teacherId);

        return pollQuestionRepository.save(question);
    }

    // ✅ Student gets active question
    @GetMapping("/active")
    public PollQuestion getActiveQuestion() {
        return pollQuestionRepository.findTopByOrderByIdDesc();
    }

    // ⚠️ Keep LAST
    @GetMapping("/{id}")
    public PollQuestion getQuestionById(@PathVariable Long id) {
        return pollQuestionRepository.findById(id).orElse(null);
    }

    // 🔑 helper method
    private Long extractTeacherId(String token) {
        String clean = token.replace("Bearer ", "").replace("teacher-", "");
        return Long.parseLong(clean);
    }
}