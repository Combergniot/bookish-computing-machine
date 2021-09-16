package com.combergniot.githubapi.user;

import com.combergniot.githubapi.user.dto.UserProfileSnapshot;
import com.combergniot.githubapi.user.dto.UserInfoDto;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/users")
class UserController {

    private final UserService userService;

    UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping(value = "/profile/{login}", produces = MediaType.APPLICATION_JSON_VALUE)
    UserProfileSnapshot fetchFullUserProfile(@PathVariable String login) {
        return userService.fetchUserProfile(login);
    }

    @GetMapping(value = "/{login}", produces = MediaType.APPLICATION_JSON_VALUE)
    UserInfoDto fetchUserInfo(@PathVariable String login) {
        return userService.getUserInfo(login);
    }
}
