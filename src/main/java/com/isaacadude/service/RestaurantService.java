package com.isaacadude.service;

import com.isaacadude.dto.RestaurantDto;
import com.isaacadude.model.Restaurant;
import com.isaacadude.model.User;
import com.isaacadude.request.CreateRestaurantRequest;

import java.util.List;

public interface RestaurantService {


    public Restaurant createRestaurant(CreateRestaurantRequest req, User user);

    public Restaurant updateRestaurant(Long restaurantId, CreateRestaurantRequest updateRestaurant) throws Exception;

    public void deleteRestaurant(Long restaurantId) throws Exception;

    public List<Restaurant> getAllRestaurants(); // only admin can do it

    public List<Restaurant> searchRestaurant(String keyword);

    public Restaurant findRestaurantById(Long id) throws Exception;

    public Restaurant getRestaurantById(Long userId) throws Exception;

    public RestaurantDto addToFavorites(Long restaurantId, User user) throws Exception;

    public Restaurant updateRestaurantStatus(Long id) throws Exception;
}
