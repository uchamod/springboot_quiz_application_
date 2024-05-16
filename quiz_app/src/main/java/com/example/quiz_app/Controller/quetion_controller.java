package com.example.quiz_app.Controller;

import com.example.quiz_app.DTO.responsedto;
import com.example.quiz_app.Entity.question;
import com.example.quiz_app.Service.questionservice;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/quiz")
@AllArgsConstructor
public class quetion_controller {

    private questionservice service;
    @GetMapping("/getquestions")
    public ResponseEntity<responsedto> getAllQuiz() {
        return service.getAllQuiz();
    }
    @GetMapping("/getquizbycategory/{cat}")
    public ResponseEntity<responsedto> getAllByCatogery(@PathVariable String cat) {
        return service.getAllByCatogery(cat);
    }

    @PostMapping("/addquizes")
    public ResponseEntity<responsedto> saveQuiz(@RequestBody question quiz){

        return service.saveQuiz(quiz);
    }

    @PutMapping("/updatequiz")
    public ResponseEntity<responsedto> updateQuiz(@RequestBody question question){
       return service.updateQuiz(question);
    }

    @DeleteMapping("/deletequiz")
    public String deleteQuiz(@RequestBody question question){

        return service.deleteQuiz(question);
    }

}
