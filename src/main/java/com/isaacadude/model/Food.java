package com.isaacadude.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Food {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String name;
    private String description;
    private Long price;

    @ManyToOne // one food can have many category
    private com.isaacadude.model.Category foodCategory;

    @Column(length = 1000)
    @ElementCollection // will create a separate table for food images
    private List<String> images;

    private boolean available;

    @ManyToOne // one restaurant will have multiple food
    private Restaurant restaurant;

    private boolean isVegetarian;
    private boolean isSeasonal;

    @ManyToMany // many food can have many ingredients
    private List<IngredientsItem> ingredients = new ArrayList<>();

    private Date creationDate;


}
