package com.muhammet.repository;

import com.muhammet.repository.entity.UserProfile;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface IUserProfileRepository extends MongoRepository<UserProfile,String> {

    Optional<UserProfile> findOptionalByAuthid(Long authid);

    List<UserProfile> findAllByIdIn(List<String> ids);
}
