package com.example.quiz_app.DTO;

import com.example.quiz_app.Entity.question;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.http.HttpStatus;

import java.util.List;
import java.util.Optional;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class responsedto {


    private String massage;


    private List<question> body;

}
