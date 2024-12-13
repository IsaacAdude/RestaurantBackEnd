package com.isaacadude.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class Restaurant {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @ManyToOne // one owner can have multiple restaurants
    private User owner;

    private String name;
    private String description;
    private String cuisineType;

    @OneToOne
    private com.isaacadude.model.Address address;

    @Embedded
    private com.isaacadude.model.ContactInformation contactInformation;

    private String openingHours;

    @OneToMany(mappedBy = "restaurant", cascade = CascadeType.ALL, orphanRemoval = true) // telling it to the restaurant table// one restaurant can have multiple orders
    private List<com.isaacadude.model.Order> orders = new ArrayList<>();

    @ElementCollection // to create a table for images
    @Column(length = 1000)
    private List<String>images;

    private LocalDateTime registrationDate;

    private boolean open; // if the owner closes the restaurant no one can order food

    @JsonIgnore // I don't need the food list inside of the restaurant
    @OneToMany(mappedBy = "restaurant", cascade = CascadeType.ALL)
    private List<com.isaacadude.model.Food> foods = new ArrayList<>();



}
