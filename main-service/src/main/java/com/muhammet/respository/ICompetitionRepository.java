package com.muhammet.respository;

import com.muhammet.respository.entity.Competition;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ICompetitionRepository extends MongoRepository<Competition, String> {

}
