package com.isaacadude.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.isaacadude.dto.RestaurantDto;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.boot.autoconfigure.amqp.RabbitConnectionDetails;

import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO) // to auto generate IDs
    private Long id;
    private String fullName;
    private String email;
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private String password;


    private USER_ROLE role = USER_ROLE.ROLE_CUSTOMER; // created new Enum

    @JsonIgnore // I don't want to see all the list when I fetch order
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "customer") // relationship between order and user
    // mappedBy customer: telling springbbot not to create a seprate table but to use order table
    private List<com.isaacadude.model.Order> orders = new ArrayList<>();

    @ElementCollection // This annotation allows an entity to have a collection of values that are not themselves
    // entities, such as a list of strings or integers, or a collection of embeddable objects
    private List<RestaurantDto>favorites = new ArrayList(); // create a Restaurant dto (data xsfer object)

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)// cascadeType.ALL whenever user is deleted all address of the user will also be deleted
    // one user with multiple address. (user can change address after order)
    private List<com.isaacadude.model.Address> addresses = new ArrayList(); // whenever user order food, the address will be stored in the user object profile for later use



}
