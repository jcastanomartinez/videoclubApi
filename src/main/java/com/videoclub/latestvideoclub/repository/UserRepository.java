package com.videoclub.latestvideoclub.repository;

import com.videoclub.latestvideoclub.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
        User findByEmail(String email);

}
