package com.pibox.knwh.entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.pibox.knwh.enumeration.Gender;
import com.pibox.knwh.enumeration.Role;
import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Getter
@Setter
@ToString
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "wh_users", uniqueConstraints = {
        @UniqueConstraint(name = "unique_user_email", columnNames = "email")
})
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String firstName;

    @Column(nullable = false)
    private String lastName;

    @DateTimeFormat(pattern = "dd/MM/yyyy")
    private Date birthDate;

    @Column(nullable = false)
    private String phoneNumber;

    @Column(nullable = false)
    private String email;

    @DateTimeFormat(pattern = "dd/MM/yyyy")
    private Date createdAt;

    @DateTimeFormat(pattern = "dd/MM/yyyy")
    private Date updatedAt;

    @DateTimeFormat(pattern = "dd/MM/yyyy")
    private Date archivedAt;

    @JsonProperty
    private boolean isActive;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Role role;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Gender gender;

    @ManyToOne
    @JoinColumn(name = "company_id", referencedColumnName = "id")
    private Company company;

    @OneToMany(mappedBy = "createdBy")
    private List<Order> orders;

    @OneToMany(mappedBy = "createdBy")
    private List<Shipment> shipments;

    @ManyToMany
    @JoinTable(name = "wh_users_tasks",
            joinColumns = @JoinColumn(name = "user_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "task_id", referencedColumnName = "id"))
    private List<Task> tasks;

    @PrePersist
    protected void onCreate() {
        this.createdAt = new Date();
    }

    @PreUpdate
    protected void onUpdate() {
        this.updatedAt = new Date();
    }
}
