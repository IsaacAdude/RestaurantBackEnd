package com.isaacadude.dto;

import jakarta.persistence.Column;
import jakarta.persistence.ElementCollection;
import jakarta.persistence.Embeddable;
import jakarta.persistence.FetchType;
import lombok.Data;

import java.util.List;

@Data
@Embeddable
public class RestaurantDto { //DTO data transfer object
    private String title;

    // @ElementCollection(fetch = FetchType.LAZY)
    @Column(length = 1000)
    private List<String> images;

    private String description;
    private Long id;

}
