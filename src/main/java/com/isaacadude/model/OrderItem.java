package com.isaacadude.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class OrderItem {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @ManyToOne // many order item can have the same food
    private com.isaacadude.model.Food food;

    private int quantity;
    private Long totalPrice;

    private List<String> ingredients;


}
