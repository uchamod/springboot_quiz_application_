package com.example.quiz_app.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class responsequizdto {

    private String massage;
    private List<responsequiz> response;
}
