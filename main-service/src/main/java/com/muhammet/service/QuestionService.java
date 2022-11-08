package com.muhammet.service;

import com.muhammet.dto.request.QuestionSaveRequestDto;
import com.muhammet.respository.IQuestionRepository;
import com.muhammet.respository.entity.Question;
import com.muhammet.utility.ServiceManager;
import org.springframework.stereotype.Service;

@Service
public class QuestionService extends ServiceManager<Question,String> {
    private final IQuestionRepository questionRepository;
    public QuestionService(IQuestionRepository questionRepository) {
        super(questionRepository);
        this.questionRepository = questionRepository;
    }

    public void save(QuestionSaveRequestDto dto){
        save(Question.builder()
                .question(dto.getQuestion())
                .answerTime(dto.getAnswerTime())
                .point(dto.getPoint())
                .build());
    }
}
