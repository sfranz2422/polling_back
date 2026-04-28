package org.example.polling_app.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

import java.time.LocalDateTime;

@Entity
public class PollQuestion {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long teacherId;
    private String questionText;

    public void setId(Long id) {
        this.id = id;
    }
    public void setTeacherId(Long teacherId) {
        this.teacherId = teacherId;
    }
    public void setQuestionText(String questionText) {
        this.questionText = questionText;
    }
    public Long getId() {
        return id;
    }
    public Long getTeacherId() {
        return teacherId;
    }
    public String getQuestionText() {
        return questionText;
    }


    // getters/setters
}