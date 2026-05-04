package org.example.polling_app.config;



import org.example.polling_app.model.PollQuestion;
import org.example.polling_app.model.Teacher;
import org.example.polling_app.repository.PollQuestionRepository;
import org.example.polling_app.repository.TeacherRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class DataLoader {

    @Bean
    CommandLineRunner loadData(TeacherRepository teacherRepository, PollQuestionRepository pollQuestionRepository) {
        return args -> {

            if (teacherRepository.count() == 0) {
                Teacher teacher1 = new Teacher("teacher1", "password123");
                teacherRepository.save(teacher1);
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
                PollQuestion q2 = new PollQuestion();
                q2.setQuestionText("What is 3 + 3?");
                q2.setOptionA("5");
                q2.setOptionB("6");
                q2.setOptionC("7");
                q2.setOptionD("8");
                q2.setTeacherId(teacher.getId());

                pollQuestionRepository.save(q2);

                pollQuestionRepository.save(question);
            }
        };
    }
}