package com.example.quiz_app.Repostory;

import com.example.quiz_app.Entity.question;
import com.example.quiz_app.Entity.quiz;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface questionrepostory extends JpaRepository<question,Integer> {
        public List<question> findByquizCatogery(String cat);
    @Query(value = "SELECT * FROM question  WHERE quiz_catogery=:cat ORDER BY RAND() LIMIT :numQ" ,nativeQuery = true)
    public List<question> findRandomQuizByCatogery(String cat, int numQ);



}
