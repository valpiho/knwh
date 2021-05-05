package com.pibox.knwh.entity;

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
@Table(name = "wh_company", uniqueConstraints = {
        @UniqueConstraint(name = "unique_company_title", columnNames = "title"),
        @UniqueConstraint(name = "unique_company_email", columnNames = "email"),
        @UniqueConstraint(name = "unique_company_vat_number", columnNames = "vat_number")
})
public class Company {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;
    private String country;
    private String city;
    private String address;
    private String zipCode;
    private String phoneNumber;
    private String email;

    @Column(name = "vat_number")
    private String vatNumber;

    @DateTimeFormat(pattern = "dd/MM/yyyy")
    private Date createdAt;

    @DateTimeFormat(pattern = "dd/MM/yyyy")
    private Date updatedAt;

    @OneToMany(mappedBy = "company", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<Warehouse> warehouseList;

    @PrePersist
    protected void onCreate() {
        this.createdAt = new Date();
    }

    @PreUpdate
    protected void onUpdate() {
        this.updatedAt = new Date();
    }
}
