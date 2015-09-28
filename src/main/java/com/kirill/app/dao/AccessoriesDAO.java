package com.kirill.app.dao;

import com.kirill.app.models.Accessories;

import java.util.List;

/**
 * Created by Admin
 *
 * @author Admin
 * @since 24.09.2015
 */

public interface AccessoriesDAO {

    boolean create(Accessories accs);
    List<Accessories> getAll();
    Accessories getAccs(Integer id);
    boolean update(Accessories accs);
    boolean delete(Accessories accs);
}
