package org.example.polling_app.config;



import org.example.polling_app.model.PollQuestion;
import org.example.polling_app.model.Teacher;
import org.example.polling_app.repository.PollQuestionRepository;
import org.example.polling_app.repository.TeacherRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import org.springframework.security.crypto.password.PasswordEncoder;


@Configuration
public class DataLoader {

    @Bean
    CommandLineRunner loadData(
            TeacherRepository teacherRepository,
            PollQuestionRepository pollQuestionRepository,
            PasswordEncoder passwordEncoder
    ) {
        return args -> {

            if (teacherRepository.count() == 0) {
                Teacher teacher1 = new Teacher(
                        "teacher1",
                        passwordEncoder.encode("password123")
                );

                Teacher teacher2 = new Teacher(
                        "teacher2",
                        passwordEncoder.encode("password123")
                );

                teacherRepository.save(teacher1);
                teacherRepository.save(teacher2);
            }

            if (pollQuestionRepository.count() == 0) {
                Teacher teacher = teacherRepository.findByUsername("teacher1").get();

                PollQuestion question = new PollQuestion();
                question.setQuestionText("What is 2 + 2?");
                question.setOptionA("3");
                question.setOptionB("4");
                question.setOptionC("5");
                question.setOptionD("6");
                question.setTeacherId(teacher.getId());

                pollQuestionRepository.save(question);
            }
        };
    }
}