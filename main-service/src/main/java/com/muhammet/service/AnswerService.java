package com.muhammet.service;

import com.muhammet.dto.request.AnswerRequestDto;
import com.muhammet.respository.IAnswerRepository;
import com.muhammet.respository.entity.Answer;
import com.muhammet.utility.ServiceManager;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AnswerService extends ServiceManager<Answer,String> {
    private final IAnswerRepository answerRepository;
    public AnswerService(IAnswerRepository answerRepository) {
        super(answerRepository);
        this.answerRepository = answerRepository;
    }

    public void save(List<AnswerRequestDto> dto){
        dto.forEach(answer -> {
            save(Answer.builder()
                    .answer(answer.getAnswer())
                    .questionId(answer.getQuestionId())
                    .isCorrect(answer.getIsCorrect())
                    .build());
        });
    }

    public List<Answer> findAllByQuestionid(String questionid){
        return answerRepository.findAllByQuestionId(questionid);
    }
}
