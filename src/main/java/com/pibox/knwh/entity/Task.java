package com.pibox.knwh.entity;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import java.util.Set;

@Entity
public class Task {

    @Id
    private Long id;

    @ManyToMany(mappedBy = "tasks")
    private Set<User> users;
}
