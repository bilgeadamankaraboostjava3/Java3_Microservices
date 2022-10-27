package com.muhammet.graphql.mutation;

import com.muhammet.graphql.model.UserProfileInput;
import com.muhammet.service.UserProfileService;
import graphql.kickstart.tools.GraphQLMutationResolver;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class UserProfileMutationResolver implements GraphQLMutationResolver {

    private final UserProfileService userProfileService;

    public Boolean createUserProfile(UserProfileInput userProfileInput){
        userProfileService.saveInput(userProfileInput);
        return true;
    }

}
