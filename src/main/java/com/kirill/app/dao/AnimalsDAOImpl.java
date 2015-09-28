package com.kirill.app.dao;

import com.kirill.app.connectors.Connector;
import com.kirill.app.models.Animals;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Admin
 *
 * @author Admin
 * @since 24.09.2015
 */
public class AnimalsDAOImpl implements AnimalsDAO {

    public static final String CREATE_ANIMAL = "insert animals set id = ?, name = ?, price = ?, food_id = ?, accs_id = ?, type = ?";
    public static final String GET_ALL = "select * from animals";
    public static final String GET_BY_ID = "select * from animals where id = ?";
    public static final String UPDATE_ANIMAL = "update animals set name = ?, price = ?, food_id = ?, accs_id = ?, type = ? where id = ?";
    public static final String DELETE_ANIMAL = "delete from animals where id = ?";

    private static Connection connection;
    private static FoodDAOImpl foodImpl;
    private static AccessoriesDAOImpl accsImpl;

    public AnimalsDAOImpl () {
        connection = Connector.getConn();
        foodImpl = new FoodDAOImpl();
        accsImpl = new AccessoriesDAOImpl();
    }

    @Override
    public boolean create(Animals animal) {

        boolean result = false;

        try {
            PreparedStatement createAnimal = connection.prepareStatement(CREATE_ANIMAL, Statement.RETURN_GENERATED_KEYS);
            createAnimal.setInt(1, animal.getId());
            createAnimal.setString(2, animal.getName());
            createAnimal.setInt(3, animal.getPrice());
            createAnimal.setInt(4, animal.getFood().getId());
            createAnimal.setInt(5, animal.getAccs().getId());
            createAnimal.setString(6, animal.getType());

            result = createAnimal.execute();

            ResultSet createdAnimalsRS = createAnimal.getGeneratedKeys();
            while (createdAnimalsRS.next()){
                animal.setId(createdAnimalsRS.getInt(1));
            }
            createAnimal.close();

        } catch (SQLException e){
            e.printStackTrace();
        }

        return result;
    }

    @Override
    public List<Animals> getAll()
    {
        ArrayList<Animals> animalsList = new ArrayList<Animals>();
        try
        {
            Statement getAll  = connection.createStatement();
            ResultSet allAnimalsRS = getAll.executeQuery(GET_ALL);
            Animals animal;

            while (allAnimalsRS.next())
            {
                animal = new Animals();

                Integer id = allAnimalsRS.getInt(1);
                String name = allAnimalsRS.getString(2);
                Integer price = allAnimalsRS.getInt(3);
                String type = allAnimalsRS.getString(6);

                animal.setId(id);
                animal.setName(name);
                animal.setPrice(price);
                animal.setFood(foodImpl.getFood(allAnimalsRS.getInt(4)));
                animal.setAccs(accsImpl.getAccs(allAnimalsRS.getInt(5)));
                animal.setType(type);

                animalsList.add(animal);
            }
            getAll.close();

        } catch (SQLException e){
            e.printStackTrace();
        }

        return animalsList;
    }

    @Override
    public Animals getAnimal (Integer id)
    {
        Animals animal = null;
        try {
            PreparedStatement getById = connection.prepareStatement(GET_BY_ID);
            getById.setInt(1, id);
            ResultSet getAnimalRS = getById.executeQuery();

            while (getAnimalRS.next())
            {
                String name = getAnimalRS.getString(2);
                Integer price = getAnimalRS.getInt(3);
                String type = getAnimalRS.getString(6);

                animal.setId(id);
                animal.setName(name);
                animal.setPrice(price);
                animal.setFood(foodImpl.getFood(getAnimalRS.getInt(4)));
                animal.setAccs(accsImpl.getAccs(getAnimalRS.getInt(5)));
                animal.setType(type);
            }
            getById.close();

        } catch (SQLException e){
            e.printStackTrace();
        }

        return animal;
    }

    @Override
    public boolean update(Animals animal) {
        boolean result = false;

        try {
            PreparedStatement updateAnimal = connection.prepareStatement(UPDATE_ANIMAL);
            updateAnimal.setString(1, animal.getName());
            updateAnimal.setInt(2, animal.getPrice());
            updateAnimal.setInt(3, animal.getFood().getId());
            updateAnimal.setInt(4, animal.getAccs().getId());
            updateAnimal.setString(5, animal.getType());
            updateAnimal.setInt(6, animal.getId());

            result = updateAnimal.execute();
            updateAnimal.close();
        } catch (SQLException e){
            e.printStackTrace();
        }

        return result;
    }

    @Override
    public boolean delete(Animals animal)
    {
        boolean result = false;

        try {
            PreparedStatement deleteAnimal = connection.prepareStatement(DELETE_ANIMAL);
            deleteAnimal.setInt(1, animal.getId());

            result = deleteAnimal.execute();
            deleteAnimal.close();
        } catch (SQLException e){
            e.printStackTrace();
        }

        return result;
    }
}
