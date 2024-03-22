package com.example.demo.repositories;

import com.example.demo.model.Friend;
import com.example.demo.model.User;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface FriendRepository extends JpaRepository <Friend, Long> {

    @Query("delete from Friend f where (f.user1 =:user1 and f.user2 =: user2) or (f.user1 =:user2 and f.user2 =: user1)")
    Friend deleteFriend(@Param("user1") Long user1, @Param("user2") Long user2);


}
