package org.example.polling_app.repository;

import org.example.polling_app.model.PollQuestion;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PollQuestionRepository extends JpaRepository<PollQuestion, Long> {
}