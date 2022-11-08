package com.muhammet.controller;

import com.muhammet.dto.request.QuestionSaveRequestDto;
import com.muhammet.dto.response.QuestionListResponseDto;
import com.muhammet.respository.entity.Question;
import com.muhammet.service.AnswerService;
import com.muhammet.service.QuestionService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static com.muhammet.constants.ApiUrls.*;
import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/question")
@RequiredArgsConstructor
public class QuestionController {

    private final QuestionService questionService;
    private final AnswerService answerService;

    @PostMapping(SAVE)
    public ResponseEntity<Void> save(@RequestBody @Valid QuestionSaveRequestDto dto){
        questionService.save(dto);
        return ResponseEntity.ok().build();
    }

    @GetMapping(FINDALL)
    public ResponseEntity<List<QuestionListResponseDto>> findAll(){
        List<Question> qlist = questionService.findAll();
        List<QuestionListResponseDto> result = new ArrayList<>();
        qlist.forEach(q->{
            result.add(QuestionListResponseDto.builder()
                            .question(q)
                            .answerlist( answerService.findAllByQuestionid(q.getId()))
                    .build());
        });
        return ResponseEntity.ok(result);
    }
}
