package com.empik.githubapi.user.dto;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonPOJOBuilder;
import lombok.Builder;
import lombok.Value;


@Value
@Builder
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonDeserialize(builder = UserProfileSnapshot.UserProfileSnapshotBuilder.class)
public class UserProfileSnapshot {
    String login;
    int id;
    String avatar_url;
    String node_id;
    String gravatar_id;
    String url;
    String html_url;
    String followers_url;
    String following_url;
    String gists_url;
    String starred_url;
    String subscriptions_url;
    String organizations_url;
    String repos_url;
    String events_url;
    String received_events_url;
    String type;
    boolean site_admin;
    String name;
    String company;
    String blog;
    String location;
    String email;
    String hireable;
    String bio;
    String twitter_username;
    int public_repos;
    int public_gists;
    int followers;
    int following;
    Date created_at;
    Date updated_at;

    @JsonPOJOBuilder(withPrefix = "")
    public static class UserProfileSnapshotBuilder {
    }
}
