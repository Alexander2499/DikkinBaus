package com.example.demo.repositories;

import com.example.demo.model.Message;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface MessageRepository extends JpaRepository<Message, Long> {

    @Query("select m from Message m " +
            "join Friend f on m.dialogId = f.id " +
            "where (f.user1 = :user1 and f.user2 = :user2) or (f.user2 = :user1 and f.user1 = :user2)")
    List<Message> getDialog(@Param("user1") Long user1, @Param("user2") Long user2);

    List<Message> getAllByDialogId(Long dialogId);

    @Modifying
    @Transactional
    @Query("DELETE FROM Message m WHERE m.dialogId = :dialogId ")
    void deleteDialog(@Param("dialogId") Long dialogId);

}
