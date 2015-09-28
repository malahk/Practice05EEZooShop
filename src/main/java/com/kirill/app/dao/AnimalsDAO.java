package com.kirill.app.dao;

import com.kirill.app.models.Animals;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by Admin
 *
 * @author Admin
 * @since 24.09.2015
 */
public interface AnimalsDAO {

    boolean create(Animals animal);
    List<Animals> getAll();
    Animals getAnimal(Integer id);
    boolean update(Animals animal);
    boolean delete(Animals animal);
}
