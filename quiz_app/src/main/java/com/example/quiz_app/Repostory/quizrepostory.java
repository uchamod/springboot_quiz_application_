package com.example.quiz_app.Repostory;

import com.example.quiz_app.Entity.quiz;
import org.springframework.data.jpa.repository.JpaRepository;

public interface quizrepostory extends JpaRepository<quiz,Integer> {
}
