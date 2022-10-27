package com.muhammet.utility;

import com.muhammet.dto.request.UserProfileRequestDto;
import com.muhammet.manager.IUserProfileManager;
import com.muhammet.repository.entity.UserProfile;
import com.muhammet.service.UserProfileService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.List;

@Component
@RequiredArgsConstructor
public class UserComponent {

    private final IUserProfileManager userProfileManager;
    private final UserProfileService userProfileService;
    @PostConstruct
    public void firstRun(){
        List<UserProfile> userProfiles = userProfileManager.userList().getBody();

        userProfiles.forEach(userProfile -> {
            userProfile.setId(null);
            userProfile.setUserid(Long.getLong(userProfile.getId()));
            userProfileService.save(userProfile);
        });
    }
}
