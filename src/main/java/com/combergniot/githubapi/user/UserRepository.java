package com.combergniot.githubapi.user;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
interface UserRepository extends JpaRepository<User, Long> {

    User findByLogin(String login);
}
