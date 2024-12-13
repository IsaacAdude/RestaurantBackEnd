package com.isaacadude.request;

import com.isaacadude.model.Address;
import com.isaacadude.model.ContactInformation;
import lombok.Data;

import java.util.List;

@Data
public class CreateRestaurantRequest {

    private Long id;
    private String name;
    private Address address;
    private String description;
    private String cuisineType;
    private ContactInformation contactInformation;
    private String openingHours;
    private List<String> images;
}
