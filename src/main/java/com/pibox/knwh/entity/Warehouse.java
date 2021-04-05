package com.pibox.knwh.entity;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import java.util.Set;

@Entity
public class Warehouse {

    @Id
    private Long id;

    @ManyToMany(mappedBy = "warehouses")
    private Set<User> users;
}
