package com.example.PracticeCRUD.entity;

import com.example.PracticeCRUD.Enum.VendorStatus;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Entity
@Setter
@Getter
@Table(name = "vendors")
public class Vendor extends BaseEntity {

    @Column(nullable = false, unique = true)
    private String shopName;

    @Column(nullable = false, unique = true)
    private String slug;

    @Column(length = 500)
    private String description;

    @Enumerated(EnumType.STRING)
    private VendorStatus status = VendorStatus.PENDING;

    private Double rating = 0.0;

    @OneToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;
}

