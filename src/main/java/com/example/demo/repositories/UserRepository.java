package com.example.demo.repositories;

import com.example.demo.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    User findByLogin(String login);
    boolean existsByLogin(String login);
    boolean existsByEmail(String email);

    @Query("select u from User u join Friend f on (f.user1 = u.id and f.user2 = :user) or (f.user2 = u.id and f.user1 = :user) ")
    List<User> friendsOf(@Param("user") Long userId);

}
