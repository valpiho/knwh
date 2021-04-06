package com.pibox.knwh.entity;

import com.fasterxml.jackson.annotation.JsonProperty;
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
@Table(name = "wh_customers", uniqueConstraints = {
        @UniqueConstraint(name = "unique_customer_email", columnNames = "email"),
        @UniqueConstraint(name = "unique_customer_vat_number", columnNames = "vat_number")
})
public class Customer {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @NotEmpty
    @Column(nullable = false)
    private String title;

    @NotEmpty
    @Column(nullable = false)
    private String phoneNumber;

    @Email
    @Column(nullable = false)
    private String email;

    @NotNull
    @Column(name = "vat_number", nullable = false)
    private String vatNumber;

    @NotNull
    @DateTimeFormat(pattern = "dd/MM/yyyy")
    private Date createdAt;

    @DateTimeFormat(pattern = "dd/MM/yyyy")
    private Date updatedAt;

    @DateTimeFormat(pattern = "dd/MM/yyyy")
    private Date archivedAt;

    @JsonProperty
    private boolean isActive;

    @OneToMany(mappedBy = "customer")
    private List<Order> orders;
}
