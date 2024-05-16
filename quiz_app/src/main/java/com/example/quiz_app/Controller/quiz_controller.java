package com.example.quiz_app.Controller;

import com.example.quiz_app.DTO.responsequizdto;
import com.example.quiz_app.DTO.submitresponse;
import com.example.quiz_app.Service.quizservice;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/createdQuiz")
@AllArgsConstructor
public class quiz_controller {


    private quizservice quizservice;
    @PostMapping("/addnewquiz")
    public ResponseEntity createQuiz(@RequestParam String cat,@RequestParam int numQ,@RequestParam String title){
        return quizservice.createQuiz(cat,numQ,title);
    }

    @GetMapping("/getquiz/{id}")
    public ResponseEntity<responsequizdto> getCreatedQuiz(@PathVariable Integer id) {

      return quizservice.getCreatedQuiz(id);
    }

    @PostMapping("/submitQuiz/{id}")
    public Integer submitQuiz(@PathVariable Integer id, @RequestBody List<submitresponse> response){
               return quizservice.submitQuiz(id,response);
    }
}
