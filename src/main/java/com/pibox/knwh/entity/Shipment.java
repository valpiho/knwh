package com.pibox.knwh.entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.pibox.knwh.enumeration.Status;
import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.Date;
import java.util.List;

@Getter
@Setter
@ToString
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "wh_shipments")
public class Shipment {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @NotEmpty
    @Column(nullable = false)
    private String country;

    @NotEmpty
    @Column(nullable = false)
    private String city;

    @NotEmpty
    @Column(nullable = false)
    private String address;

    @NotEmpty
    @Column(nullable = false)
    private String zipCode;

    @NotEmpty
    @Column(nullable = false)
    private String phoneNumber;

    @Email
    @Column(nullable = false)
    private String email;

    @NotNull
    @DateTimeFormat(pattern = "dd/MM/yyyy")
    private Date createdAt;

    @DateTimeFormat(pattern = "dd/MM/yyyy")
    private Date updatedAt;

    @DateTimeFormat(pattern = "dd/MM/yyyy")
    private Date archivedAt;

    @JsonProperty
    private boolean isActive;

    @NotNull
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Status status;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "created_by_user_id")
    private User createdBy;

    @ManyToOne(fetch = FetchType.LAZY)
    private Customer customer;

    @ManyToOne(fetch = FetchType.LAZY)
    private Vendor vendor;

    @ManyToOne(fetch = FetchType.LAZY)
    private Task task;

    @OneToMany(mappedBy = "shipment")
    private List<Order> orders;
}
