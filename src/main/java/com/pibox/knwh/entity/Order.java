package com.pibox.knwh.entity;

import javax.persistence.*;

@Entity
public class Order {

    @Id
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;
}
