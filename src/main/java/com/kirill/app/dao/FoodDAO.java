package com.kirill.app.dao;

import com.kirill.app.models.Food;

import java.util.List;

/**
 * Created by Admin
 *
 * @author Admin
 * @since 24.09.2015
 */
public interface FoodDAO {

    boolean create(Food food);
    List<Food> getAll();
    Food getFood(Integer id);
    boolean update(Food food);
    boolean delete(Food food);
}
