package com.muhammet.dto.response;

import com.muhammet.respository.entity.Answer;
import com.muhammet.respository.entity.Question;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class QuestionListResponseDto {
    Question question;
    List<Answer> answerlist;
}
