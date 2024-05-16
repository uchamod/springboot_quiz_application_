package com.example.quiz_app.Service;

import com.example.quiz_app.DTO.deleteresponseDto;
import com.example.quiz_app.DTO.responsedto;
import com.example.quiz_app.Entity.question;
import com.example.quiz_app.Repostory.questionrepostory;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
@AllArgsConstructor

public class questionservice {

    private questionrepostory repo;


    public ResponseEntity<responsedto> getAllQuiz() {
        try {
            List<question> quizes = repo.findAll();
            if (quizes.isEmpty()) {
                responsedto response = new responsedto("no quiz found", quizes);
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
            } else {
                responsedto response = new responsedto("quiz found", quizes);
                return ResponseEntity.status(HttpStatus.OK).body(response);
            }
        } catch (Exception e) {
            responsedto response=new responsedto("unexpected error",null);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
        }

    }

    public ResponseEntity<responsedto> getAllByCatogery(String cat) {
        try {
        List<question> quizes = repo.findByquizCatogery(cat);
        if (quizes.isEmpty()) {
            responsedto response = new responsedto("no quiz found", quizes);
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
        }else {
            responsedto response = new responsedto("quiz found", quizes);
            return ResponseEntity.status(HttpStatus.OK).body(response);
        }
    } catch (Exception e) {
            responsedto response=new responsedto("unexpected error",null);
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
    }

    }

    public ResponseEntity<responsedto> saveQuiz(question quiz) {
        try{
            if (repo.existsById(quiz.getQuizId())) {
                responsedto response=new responsedto("quiz already exists",null);
                return  ResponseEntity.status(HttpStatus.ALREADY_REPORTED).body(response);
            }else{
                repo.save(quiz);
                responsedto response=new responsedto("quiz is saved",null);
                return  ResponseEntity.status(HttpStatus.CREATED).body(response);
            }


        } catch(Exception e){
            responsedto response=new responsedto("unexpected error",null);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
        }

        }

    public ResponseEntity<responsedto> updateQuiz(question question) {
            try{
                if(repo.existsById(question.getQuizId())){
                    repo.save(question);
                    responsedto response=new responsedto("question is updated",null);
                    return ResponseEntity.status(HttpStatus.OK).body(response);
                }else{
                    responsedto response=new responsedto("question is not updated:check qusetion details and try agin",null);
                    return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
                }
            }catch (Exception  e){
                responsedto response=new responsedto("unexpected error "+e,null);
                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
            }
    }

    public String deleteQuiz(question question) {
        try{
            if(repo.existsById(question.getQuizId())){
                repo.delete(question);
               // deleteresponseDto response=new  deleteresponseDto("question is deleted",null);
               // return ResponseEntity.status(HttpStatus.OK).body(response);
                return "delete sucssus";
            }else{
                deleteresponseDto response=new  deleteresponseDto("question is not deleted",null);
                //return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
                return "delete not sucssus";
            }
        }catch (Exception e){
            deleteresponseDto response=new  deleteresponseDto("unexpected error"+e,null);
           // return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
            return "reeor";
        }
    }
}


