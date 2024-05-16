package com.example.quiz_app.Service;
import com.example.quiz_app.DTO.responsequiz;
import com.example.quiz_app.DTO.responsequizdto;
import com.example.quiz_app.Entity.quiz;
import com.example.quiz_app.Entity.question;
import com.example.quiz_app.Repostory.questionrepostory;
import com.example.quiz_app.Repostory.quizrepostory;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import com.example.quiz_app.DTO.submitresponse;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
@Transactional
public class quizservice {

    private quizrepostory  quizrepostory;

    private questionrepostory questionrepostory;




    public ResponseEntity createQuiz(String cat, int numQ, String title) {
        try{
            List<question> questions=questionrepostory.findRandomQuizByCatogery(cat,numQ);
            quiz quiz = new quiz();
            quiz.setTitle(title);
            quiz.setQuizs(questions);
            quizrepostory.save(quiz);
            return new ResponseEntity("quiz creted", HttpStatus.CREATED);
        }catch (Exception e){
            return new ResponseEntity("quiz not created"+e, HttpStatus.BAD_REQUEST);
        }



    }

    public ResponseEntity<responsequizdto> getCreatedQuiz(Integer id) {

            try{
                Optional<quiz> quiz=quizrepostory.findById(id);
                if(quiz.isEmpty()){
                    responsequizdto responseDto=new responsequizdto("quiz is not found",null);
                    return ResponseEntity.status(HttpStatus.NOT_FOUND).body(responseDto);
                }else{
                    List<question> questionsList=quiz.get().getQuizs();

                    List<responsequiz> responseQuiz=new ArrayList<>();
                    for(question q:questionsList){
                        responsequiz resQuiz=new responsequiz(q.getQuizId(),
                                q.getQuizTitle(),
                                q.getQuizCatogery(),
                                q.getOption1(),q.getOption2()
                                ,q.getOption3(),q.getOption4());
                        responseQuiz.add(resQuiz);

                    }
                    responsequizdto responseDto=new responsequizdto("quiz is fetched succsefuly",responseQuiz);
                    return ResponseEntity.status(HttpStatus.OK).body(responseDto);

                }


            }catch (Exception e){
                responsequizdto responseQuiz=new responsequizdto("unexpected error:"+e,null);
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(responseQuiz);
            }




        }

         public Integer submitQuiz(Integer id,List<submitresponse> responses){
                Optional<quiz> q= quizrepostory.findById(id);
                List<question> questions=q.get().getQuizs();
                int i=0;
                int right=0;
                for(submitresponse sr: responses){
                    if(sr.getResponse().equals(questions.get(i).getRightAnswer())){
                        right++;
                    }
                    i++;
                }
                return right;


    }




}
