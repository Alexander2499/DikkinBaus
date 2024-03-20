package com.example.demo.model;

import jakarta.persistence.*;

@Entity
@Table(name = "friends")
public class Friends {
    @Id
    private Long id;



//    @Column(name = "friend")
//    @ManyToMany(mappedBy = "users",cascade = CascadeType.ALL,fetch = FetchType.LAZY)
//    private Set<User> users = new HashSet<>();
//
//    public Set<User> getUsers() {
//        return users;
//    }
//
//    public void setUsers(Set<User> users) {
//        this.users = users;
//    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }
}
