package com.empik.githubapi.user;

import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.then;

import com.empik.githubapi.infrastructure.GithubProvider;
import com.empik.githubapi.user.dto.UserInfoDto;
import com.empik.githubapi.user.dto.UserProfileSnapshot;
import com.empik.githubapi.user.exception.UserNotFoundException;
import org.assertj.core.api.BDDSoftAssertions;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.junit.jupiter.MockitoSettings;
import org.mockito.quality.Strictness;

@ExtendWith(MockitoExtension.class)
@MockitoSettings(strictness = Strictness.LENIENT)
class UserServiceTest {

    private static final String LOGIN = "test_user";

    @InjectMocks
    private UserService userService;
    @Mock
    private GithubProvider githubProvider;
    @Mock
    private UserRepository userRepository;


    @Test
    void shouldGetCorrectUserInfo() {
        //given
        UserProfileSnapshot userProfileSnapshot = createProfile();
        given(githubProvider.getUserInfo(LOGIN)).willReturn(userProfileSnapshot);
        double calculations = 0.72;

        //when
        UserInfoDto userInfoDto = userService.getUserInfo(LOGIN);

        //then
        BDDSoftAssertions and = new BDDSoftAssertions();
        and.then(userInfoDto.getId()).isEqualTo(userProfileSnapshot.getId());
        and.then(userInfoDto.getAvatarUrl()).isEqualTo(userProfileSnapshot.getAvatar_url());
        and.then(userInfoDto.getCreated_at()).isEqualTo(userProfileSnapshot.getCreated_at());
        and.then(userInfoDto.getCalculations()).isEqualTo(calculations);
        and.assertAll();
    }

    @Test
    void shouldThrowUserNotFoundExceptionWhenUserProfileDoesntExist() {
        //given
        UserProfileSnapshot userProfileSnapshot = UserProfileSnapshot.builder().build();
        given(githubProvider.getUserInfo(LOGIN)).willReturn(userProfileSnapshot);
        //when
        //then
        Assertions.assertThrows(UserNotFoundException.class, () -> {
            userService.getUserInfo(LOGIN);
        });
    }

    @Test
    void shouldIncreaseRequestCountNumberForExistingUser() {
        //given
        User user = new User("test_user", 1);
        UserProfileSnapshot userProfileSnapshot = createProfile();
        given(githubProvider.getUserInfo(LOGIN)).willReturn(userProfileSnapshot);
        given(userRepository.findByLogin(LOGIN)).willReturn(user);

        //when
        userService.getUserInfo(LOGIN);

        //then
        then(userRepository).should().saveAndFlush(user);
        BDDSoftAssertions and = new BDDSoftAssertions();
        and.then(user.getRequest_count()).isEqualTo(2);
        and.assertAll();
    }


    @Test
    void shouldSaveRequestCountForNonExistingUser() throws UserNotFoundException{
        //given
        given(userRepository.findByLogin(LOGIN)).willReturn(null);

        //when
        userService.increaseRequestCount(LOGIN);

        //then
        then(userRepository).should().saveAndFlush(new User(LOGIN, 1));
    }



    private UserProfileSnapshot createProfile() {
        return UserProfileSnapshot.builder()
                .login("test_user")
                .id(666)
                .name("The test")
                .followers(100)
                .public_repos(10)
                .build();
    }
}