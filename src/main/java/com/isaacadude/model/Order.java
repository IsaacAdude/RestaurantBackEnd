package com.isaacadude.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "orders")
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @ManyToOne // many order can have the same user
    private User customer;

    @JsonIgnore
    @ManyToOne // one restaurant can have multiple order
    private Restaurant restaurant;

    private Long totalAmount;
    private String orderStatus;
    private Date createdAt;

    @ManyToOne // many orders can have one address
    private com.isaacadude.model.Address deliveryAddress;

    @OneToMany // many item in one order
    private List<OrderItem> items;


    private int totalItem;
    private int totalPrice;



}
