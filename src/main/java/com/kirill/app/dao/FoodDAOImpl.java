package com.kirill.app.dao;

import com.kirill.app.connectors.Connector;
import com.kirill.app.models.Food;
import org.springframework.stereotype.Repository;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Admin
 *
 * @author Admin
 * @since 24.09.2015
 */
@Repository
public class FoodDAOImpl implements FoodDAO {

    public static final String CREATE_FOOD = "insert food set id = ?, title = ?";
    public static final String GET_ALL = "select * from food";
    public static final String GET_BY_ID = "select * from food where id = ?";
    public static final String UPDATE_FOOD = "update food set title = ? where id = ?";
    public static final String DELETE_FOOD = "delete from food where id = ?";

    private static Connection connection;

    public FoodDAOImpl () {
        connection = Connector.getConn();
    }

    @Override
    public boolean create(Food food) {

        boolean result = false;

        try {
            PreparedStatement createFood = connection.prepareStatement(CREATE_FOOD, Statement.RETURN_GENERATED_KEYS);
            createFood.setInt(1, food.getId());
            createFood.setString(2, food.getItemName());

            result = createFood.execute();

            ResultSet createdFoodRS = createFood.getGeneratedKeys();
            createdFoodRS.next();
            food.setId(createdFoodRS.getInt(1));

        } catch (SQLException e){
            e.printStackTrace();
        }

        return result;
    }

    @Override
    public List<Food> getAll()
    {
        ArrayList<Food> foodList = new ArrayList<Food>();
        try
        {
            Statement getAll  = connection.createStatement();
            ResultSet allFoodRS = getAll.executeQuery(GET_ALL);
            Food food;

            while (allFoodRS.next())
            {
                food = new Food();

                Integer id = allFoodRS.getInt(1);
                String itemName = allFoodRS.getString(2);

                food.setId(id);
                food.setItemName(itemName);

                foodList.add(food);
            }

        } catch (SQLException e){
            e.printStackTrace();
        }

        return foodList;
    }

    @Override
    public Food getFood(Integer id)
    {
        Food food = null;
        try {
            PreparedStatement getById = connection.prepareStatement(GET_BY_ID);
            getById.setInt(1, id);
            ResultSet getFoodRS = getById.executeQuery();

            while (getFoodRS.next())
            {
                String itemName = getFoodRS.getString(2);
                food.setId(id);
                food.setItemName(itemName);
            }

        } catch (SQLException e){
            e.printStackTrace();
        }

        return food;
    }

    @Override
    public boolean update(Food food) {
        boolean result = false;

        try {
            PreparedStatement updateFood = connection.prepareStatement(UPDATE_FOOD);
            updateFood.setString(1, food.getItemName());
            updateFood.setInt(2, food.getId());

            result = updateFood.execute();
            updateFood.close();
        } catch (SQLException e){
            e.printStackTrace();
        }

        return result;
    }

    @Override
    public boolean delete(Food food)
    {
        boolean result = false;

        try {
            PreparedStatement deleteFood = connection.prepareStatement(DELETE_FOOD);
            deleteFood.setInt(1, food.getId());

            result = deleteFood.execute();

        } catch (SQLException e){
            e.printStackTrace();
        }

        return result;
    }
}
