package org.example.polling_app.repository;

import org.example.polling_app.model.PollResponse;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PollResponseRepository extends JpaRepository<PollResponse, Long> {
    List<PollResponse> findByQuestionId(Long questionId);

}