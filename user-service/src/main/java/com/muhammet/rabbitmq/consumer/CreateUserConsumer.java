package com.muhammet.rabbitmq.consumer;

import com.muhammet.rabbitmq.model.CreateProfile;

import com.muhammet.repository.entity.UserProfile;
import com.muhammet.service.UserProfileService;
import lombok.RequiredArgsConstructor;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CreateUserConsumer {

    private final UserProfileService userProfileService;

    @RabbitListener(queues = "queue-auth-create-user")
    public void consumeMessage(CreateProfile profile){
        userProfileService.save(UserProfile.builder()
                        .authid(profile.getAuthid())
                        .email(profile.getEmail())
                        .username(profile.getUsername())
                .build());
    }
}
