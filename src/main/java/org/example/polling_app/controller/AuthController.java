package org.example.polling_app.controller;

import org.example.polling_app.dto.LoginRequest;
import org.example.polling_app.dto.LoginResponse;
import org.example.polling_app.model.Teacher;
import org.example.polling_app.repository.TeacherRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/api/auth")
@CrossOrigin("*")
public class AuthController {

    private final TeacherRepository teacherRepository;
    private final PasswordEncoder passwordEncoder;

    public AuthController(
            TeacherRepository teacherRepository,
            PasswordEncoder passwordEncoder
    ) {
        this.teacherRepository = teacherRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginRequest request) {

        Optional<Teacher> teacherOptional =
                teacherRepository.findByUsername(request.getUsername());

        if (teacherOptional.isEmpty()) {
            return ResponseEntity.status(401).body("Invalid username or password");
        }

        Teacher teacher = teacherOptional.get();

        if (!passwordEncoder.matches(request.getPassword(), teacher.getPassword())) {
            return ResponseEntity.status(401).body("Invalid username or password");
        }

        String token = "teacher-" + teacher.getId();

        return ResponseEntity.ok(new LoginResponse(token));
    }
}