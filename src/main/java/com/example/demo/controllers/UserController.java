package com.example.demo.controllers;

import com.example.demo.model.Friend;
import com.example.demo.model.Message;
import com.example.demo.model.User;
import com.example.demo.repositories.FriendRepository;
import com.example.demo.repositories.MessageRepository;
import com.example.demo.services.FriendService;
import com.example.demo.services.MessageService;
import com.example.demo.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private MessageService messageService;


    @GetMapping("/showUser")
    public ResponseEntity<User> showUser() {
        User user = userService.getCurrentUser();
        return ResponseEntity.ok(user);
    }

    @GetMapping("/showMessages/{friendId}")
    public ResponseEntity<List<Message>> showMessage(@PathVariable Long friendId) {
        User user1 = userService.getCurrentUser();
        List<Message> message = messageService.getDialog(user1.getId(), friendId);
        return ResponseEntity.ok(message);
    }


    @GetMapping("/showFriends")
    public ResponseEntity<List<User>> showFriends() {
        User user = userService.getCurrentUser();
        List<User> friends = userService.friendsOf(user.getId());
        return ResponseEntity.ok(friends);
    }

//    @GetMapping("/addFriend")
//    public Friend addFriend(Long user2) {
//        User user1 = showUser();
//        Friend newFriend = new Friend();
//        newFriend.setUser1(user1.getId());
//        newFriend.setUser2(user2);
//        return friendRepository.save(newFriend);
//    }
//
//    @GetMapping("/deleteFriend")
//    public Friend deleteFriend(Long user2) {
//        User user = showUser();
//        return friendRepository.deleteFriend(user.getId(), user2);
//    }
//
//    public Message sendMessage(String text, Long senderId) {
//        Message message = new Message();
//        message.setContent(text);
//        message.setDialogId(senderId);
//        message.getMessageDate(LocalDate.now());
//        return messageRepository.save(message);
//    }
//
//    //    @GetMapping
//    public void deleteDialog(Long id) {
//    }
//
//
//    public void deleteMessage(Long id) {
//    }

}
