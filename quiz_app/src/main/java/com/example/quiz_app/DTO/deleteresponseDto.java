package com.example.quiz_app.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import com.example.quiz_app.Entity.question;

import java.util.Optional;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class deleteresponseDto {

    private String massage;
    private Optional<question> question;
}
