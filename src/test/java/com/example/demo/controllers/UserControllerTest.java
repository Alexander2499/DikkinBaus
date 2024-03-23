package com.example.demo.controllers;

import com.example.demo.model.Friend;
import com.example.demo.model.User;
import com.example.demo.repositories.FriendRepository;
import com.example.demo.repositories.MessageRepository;
import com.example.demo.repositories.UserRepository;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.Matchers.hasSize;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@WithMockUser(username = "admin", authorities = {"ADMIN", "USER"})
@Transactional
class UserControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private FriendRepository friendRepository;

    @Autowired
    private MessageRepository messageRepository;





    @BeforeEach
    void setUp() {
        User admin = new User();
        admin.setLogin("admin");
        admin.setPassword("adminPassword");
        userRepository.save(admin);
    }

    @Test
    void showUser() throws Exception {
        this.mockMvc.perform(get("/user/showUser"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.login").value("admin"))
                .andExpect(jsonPath("$.password").value("adminPassword"));
//                .andExpect(content().string(containsString("user")));
    }

    @Test
    void showMessage() throws Exception {
        User admin = userRepository.findByLogin("admin");

        User user1 = new User();
        user1.setLogin("user1");
        userRepository.save(user1);

        Friend friend1 = new Friend();
        friend1.setUser1(admin.getId());
        friend1.setUser2(user1.getId());
        friendRepository.save(friend1);



        this.mockMvc.perform(get("/user/showMessages/" + user1.getId()))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(2)))
                .andExpect(jsonPath("$.[0].login").value("user1"));

    }

    @Test
    void showFriends() throws Exception {
        User admin = userRepository.findByLogin("admin");

        User user1 = new User();
        user1.setLogin("user1");
        userRepository.save(user1);
        User user2 = new User();
        user2.setLogin("user2");
        userRepository.save(user2);

        Friend friend1 = new Friend();
        friend1.setUser1(admin.getId());
        friend1.setUser2(user1.getId());
        friendRepository.save(friend1);

        Friend friend2 = new Friend();
        friend2.setUser1(admin.getId());
        friend2.setUser2(user2.getId());
        friendRepository.save(friend2);

        this.mockMvc.perform(get("/user/showFriends"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(2)))
                .andExpect(jsonPath("$.[0].login").value("user1"))
                .andExpect(jsonPath("$.[1].login").value("user2"));
//                .andExpect(content().string(containsString("user")));
    }
}