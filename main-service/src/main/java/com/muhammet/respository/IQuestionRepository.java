package com.muhammet.respository;

import com.muhammet.respository.entity.Question;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IQuestionRepository extends MongoRepository<Question, String> {

}
