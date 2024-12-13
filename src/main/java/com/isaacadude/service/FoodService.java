package com.isaacadude.service;

import com.isaacadude.model.Category;
import com.isaacadude.model.Food;
import com.isaacadude.model.Restaurant;
import com.isaacadude.request.CreateFoodRequest;

import java.util.List;

public interface FoodService {
    public Food createFood(CreateFoodRequest req, Category category, Restaurant restaurant);

    void deleteFood(Long foodId) throws Exception;

    public List<Food> getRestaurantFood(Long restaurantId,
                                        boolean isVegitarian,
                                        boolean isNonVegitarian,
                                        boolean isSeasonal,
                                        String foodCategory);

    public List<Food> searchFood(String keyword);

    public Food findFoodById(Long foodId) throws Exception;

    public Food updateAvailabiltyStatus(Long foodId) throws Exception;
}
