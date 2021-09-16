package com.combergniot.githubapi.user;

import java.math.BigDecimal;
import java.math.RoundingMode;

import com.combergniot.githubapi.infrastructure.GithubProvider;
import com.combergniot.githubapi.user.dto.UserProfileSnapshot;
import com.combergniot.githubapi.user.exception.UserNotFoundException;
import com.combergniot.githubapi.user.dto.UserInfoDto;
import org.springframework.stereotype.Service;

@Service
class UserService {

    private final GithubProvider githubProvider;
    private final UserRepository userRepository;

    UserService(GithubProvider githubProvider, UserRepository userRepository) {
        this.githubProvider = githubProvider;
        this.userRepository = userRepository;
    }

    UserProfileSnapshot fetchUserProfile(String login) {
        return githubProvider.getUserInfo(login);
    }

    UserInfoDto getUserInfo(String login) {
        UserProfileSnapshot userProfileSnapshot = fetchUserProfile(login);
        increaseRequestCount(login);
        if (userProfileSnapshot.getLogin() != null) {
            return UserInfoDto.builder()
                    .id(userProfileSnapshot.getId())
                    .login(userProfileSnapshot.getLogin())
                    .name(userProfileSnapshot.getName())
                    .type(userProfileSnapshot.getType())
                    .avatarUrl(userProfileSnapshot.getAvatar_url())
                    .created_at(userProfileSnapshot.getCreated_at())
                    .calculations(calculate(userProfileSnapshot))
                    .build();
        } else throw new UserNotFoundException(login);
    }

    private double calculate(UserProfileSnapshot userProfileSnapshot) {
        if (userProfileSnapshot.getFollowers() == 0) {
            return 0;
        }
        return BigDecimal.valueOf(6.0 / userProfileSnapshot.getFollowers() * (2 + userProfileSnapshot.getPublic_repos()))
                .setScale(2, RoundingMode.HALF_UP).doubleValue();
    }

    protected void increaseRequestCount(String login) {
        User user = userRepository.findByLogin(login);
        if (user != null) {
            int requestCount = user.getRequest_count();
            user.modifyRequest_count(requestCount + 1);
            userRepository.saveAndFlush(user);
        } else {
            userRepository.saveAndFlush(
                    User.builder()
                            .login(login)
                            .request_count(1)
                            .build());
        }
    }
}
