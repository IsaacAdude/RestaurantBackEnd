package com.isaacadude.request;

import com.isaacadude.model.Category;
import com.isaacadude.model.IngredientsItem;
import lombok.Data;

import java.util.List;

@Data

public class CreateFoodRequest {
    private String name;
    private String description;
    private Long price;

    private Category category;
    private List<String> images;

    private Long restaurantId;
    private boolean vegetarian;
    private boolean seasional;
    private List<IngredientsItem> ingredients;
}
