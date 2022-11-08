package com.muhammet.respository;

import com.muhammet.respository.entity.Answer;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IAnswerRepository extends MongoRepository<Answer, String> {

    List<Answer> findAllByQuestionId(String questionId);
}
