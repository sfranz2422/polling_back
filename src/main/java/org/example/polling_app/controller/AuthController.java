package org.example.polling_app.controller;

import org.example.polling_app.dto.LoginRequest;
import org.example.polling_app.dto.LoginResponse;
import org.example.polling_app.model.Teacher;
import org.example.polling_app.repository.TeacherRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/api/auth")
@CrossOrigin("*")
public class AuthController {

    private final TeacherRepository teacherRepository;

    public AuthController(TeacherRepository teacherRepository) {
        this.teacherRepository = teacherRepository;
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginRequest request) {

        Optional<Teacher> teacherOptional =
                teacherRepository.findByUsername(request.getUsername());

        if (teacherOptional.isEmpty()) {
            return ResponseEntity.status(401).body("Invalid username or password");
        }

        Teacher teacher = teacherOptional.get();

        if (!teacher.getPassword().equals(request.getPassword())) {
            return ResponseEntity.status(401).body("Invalid username or password");
        }

        String token = "teacher-" + teacher.getId();

        return ResponseEntity.ok(new LoginResponse(token));
    }
}