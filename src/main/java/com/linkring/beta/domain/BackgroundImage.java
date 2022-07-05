package com.linkring.beta.domain;

import javax.persistence.*;

@Entity
public class BackgroundImage {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long Id;

    private String url;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User username;

    public BackgroundImage(){

    };

    public BackgroundImage(String url, User username) {
        this.url = url;
        this.username = username;
    }

    public Long getId() {
        return Id;
    }

    public void setId(Long id) {
        Id = id;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public User getUsername() {
        return username;
    }

    public void setUsername(User username) {
        this.username = username;
    }
}
