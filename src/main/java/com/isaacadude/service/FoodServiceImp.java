package com.isaacadude.service;

import com.isaacadude.model.Category;
import com.isaacadude.model.Food;
import com.isaacadude.model.Restaurant;
import com.isaacadude.repository.FoodRepository;
import com.isaacadude.request.CreateFoodRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class FoodServiceImp implements FoodService {

    @Autowired
    private FoodRepository foodRepository;

    @Override
    public Food createFood(CreateFoodRequest req, Category category, Restaurant restaurant) {

        Food food = new Food();
        food.setFoodCategory(category);
        food.setRestaurant(restaurant);
        food.setDescription(req.getDescription());
        food.setPrice(req.getPrice());
        food.setImages(req.getImages());
        food.setName(req.getName());
        food.setIngredients(req.getIngredients());
        food.setSeasonal(req.isSeasional());
        food.setVegetarian(req.isVegetarian());

        Food saveFood = foodRepository.save(food);
        restaurant.getFoods().add(saveFood);

        return saveFood;
    }

    @Override
    public void deleteFood(Long foodId) throws Exception {

        Food food = findFoodById(foodId);
        food.setRestaurant(null);
        foodRepository.save(food);

    }

    @Override
    public List<Food> getRestaurantFood(Long restaurantId,
                                        boolean isVegitarian,
                                        boolean isNonVegitarian,
                                        boolean isSeasonal, String foodCategory) {

        List<Food> foods = foodRepository.findByRestaurantId(restaurantId);

        if(isVegitarian){
            foods = filterByVegetarian(foods, isVegitarian);
        }
        if(isNonVegitarian){
            foods = filterByNonVegetarian(foods, isNonVegitarian);
        }
        if(isSeasonal){
            foods = filterBySeasonal(foods, isSeasonal);
        }
        if(foodCategory != null && !foodCategory.equals("")){
            foods = filterByCategory(foods, foodCategory);
        }

        return foods;
    }

    private List<Food> filterByCategory(List<Food> foods, String foodCategory) {
        return foods.stream().filter(food -> {
            if(food.getFoodCategory()!=null){
                return food.getFoodCategory().getName().equals(foodCategory);
            }
            return false;
        }).collect(Collectors.toList());

    }


    private List<Food> filterBySeasonal(List<Food> foods, boolean isSeasonal) {
        return foods.stream().filter(food -> food.isSeasonal()==isSeasonal).collect(Collectors.toList());
    }

    private List<Food> filterByNonVegetarian (List<Food> foods, boolean isNonVegitarian) {
        return foods.stream().filter(food -> food.isVegetarian()==false).collect(Collectors.toList()); // if nonVeg is true then veget will be false
    }

    private List<Food> filterByVegetarian(List<Food> foods, boolean isVegetarian) {

        return foods.stream().filter(food -> food.isVegetarian()==isVegetarian).collect(Collectors.toList());
    }

    @Override
    public List<Food> searchFood(String keyword) {
        return List.of();
    }

    @Override
    public Food findFoodById(Long foodId) throws Exception {
        return null;
    }

    @Override
    public Food updateAvailabiltyStatus(Long foodId) throws Exception {
        return null;
    }
}
