package org.example.polling_app.repository;

import org.example.polling_app.model.PollQuestion;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PollQuestionRepository extends JpaRepository<PollQuestion, Long> {

    List<PollQuestion> findByTeacherId(Long teacherId);

}