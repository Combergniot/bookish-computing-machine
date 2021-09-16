package com.combergniot.githubapi.user.dto;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonPOJOBuilder;
import lombok.Builder;
import lombok.Value;

@Value
@Builder
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonDeserialize(builder = UserInfoDto.UserInfoDtoBuilder.class)
public class UserInfoDto {

    String login;
    int id;
    String name;
    String type;
    String avatarUrl;
    Date created_at;
    double calculations;


    @JsonPOJOBuilder(withPrefix = "")
    @JsonIgnoreProperties(ignoreUnknown = true)
   public static class UserInfoDtoBuilder {
    }
}
