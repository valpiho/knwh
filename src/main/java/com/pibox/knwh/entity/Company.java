package com.pibox.knwh.entity;

import javax.persistence.*;
import java.util.Set;

@Entity
public class Company {

    @Id
    private Long id;

    @OneToMany(mappedBy = "company", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private Set<User> users;
}
