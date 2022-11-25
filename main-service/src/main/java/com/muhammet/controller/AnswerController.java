package com.muhammet.controller;

import com.muhammet.dto.request.AnswerRequestDto;
import com.muhammet.respository.entity.Answer;
import com.muhammet.service.AnswerService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.muhammet.constants.ApiUrls.*;
@RestController
@RequestMapping("/answer")
@RequiredArgsConstructor
public class AnswerController {

    private final AnswerService answerService;

    @PostMapping(SAVE)
    @CrossOrigin(origins = "*", allowedHeaders = "*")
    public ResponseEntity<Void> save(@RequestBody List<AnswerRequestDto> dto){
        answerService.save(dto);
        return ResponseEntity.ok().build();
    }
    @GetMapping(FINDALL)
    @CrossOrigin(origins = "*", allowedHeaders = "*")
    public ResponseEntity<List<Answer>> findAll(){
        return ResponseEntity.ok(answerService.findAll());
    }
}
