package com.example.demo.controllers;

import com.example.demo.model.Friend;
import com.example.demo.model.Message;
import com.example.demo.model.User;
import com.example.demo.repositories.FriendRepository;
import com.example.demo.repositories.MessageRepository;
import com.example.demo.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


import java.time.LocalDate;
import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private FriendRepository friendRepository;

    @Autowired
    private MessageRepository messageRepository;


    @GetMapping("/showUser")
    public User showUser() {
        String login = currentUserLogin();
        return userRepository.findByLogin(login);
    }

    @GetMapping("/showMessage")
    public String showMessage(@PathVariable("id") Long id) {

        return "message";
    }

    @GetMapping
    public Message sendMessage(String text, Long senderId) {
        Message message = new Message();
        message.setContent(text);
        message.setDialogId(senderId);
        message.getMessageDate(LocalDate.now());
        return messageRepository.save(message);
    }

    @GetMapping
    public void deleteMessage() {
//        messageRepository.deleteById();
    }

    @GetMapping("/showFriends")
    public List<User> showFriends() {
        String login = currentUserLogin();
        User user = userRepository.findByLogin(login);
        return userRepository.friendsOf(user.getId());
//        return "friends";
    }

    @GetMapping("/addFriend")
    public Friend addFriend(Long user2) {
        User user1 = showUser();
        Friend newFriend = new Friend();
        newFriend.setUser1(user1.getId());
        newFriend.setUser2(user2);
        return friendRepository.save(newFriend);
    }

    @GetMapping("/deleteFriend")
    public Friend deleteFriend(Long user2) {
        User user = showUser();
        return friendRepository.deleteFriend(user.getId(),user2);
    }


    private String currentUserLogin() {
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if (principal instanceof UserDetails) {
            return ((UserDetails) principal).getUsername();
        } else {
            return principal.toString();
        }
    }


//    @GetMapping
//    public String greet() {
//        return "Hello";
//    }

//    @Bean
//    public DataSource dataSource() {
//        DriverManagerDataSource dataSource = new DriverManagerDataSource();
//        dataSource.setDriverClassName("com.mysql.cj.jdbc.Driver");
//        dataSource.setUrl("jdbc:mysql://localhost:3306/web_project");
//        dataSource.setUsername("root");
//        dataSource.setPassword("root");
//        return (DataSource) dataSource;
//    }

}
