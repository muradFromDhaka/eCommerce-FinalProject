package com.example.PracticeCRUD.entity;

import java.math.BigDecimal;

import com.example.PracticeCRUD.Enum.VendorOrderStatus;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name = "vendor_orders")
public class VendorOrder extends BaseEntity {

    @ManyToOne
    @JoinColumn(name = "order_id")
    private Order order;

    @ManyToOne
    @JoinColumn(name = "vendor_id")
    private Vendor vendor;

    private BigDecimal subtotal;

    @Enumerated(EnumType.STRING)
    private VendorOrderStatus status;
}

