package com.isaacadude.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class IngredientsItem {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String name;

    @ManyToOne
    private com.isaacadude.model.IngredientCategory category;

    @JsonIgnore
    @ManyToOne
    private Restaurant restaurant;

    private boolean inStoke = true; // by default it will be true but the owner can change it
}
