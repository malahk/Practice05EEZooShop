package com.kirill.app.dao;

import com.kirill.app.connectors.Connector;
import com.kirill.app.models.Accessories;
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
public class AccessoriesDAOImpl implements AccessoriesDAO {
    public static final String CREATE_ACCS = "insert accessories set id = ?, title = ?";
    public static final String GET_ALL = "select * from accessories";
    public static final String GET_BY_ID = "select * from accessories where id = ?";
    public static final String UPDATE_ACCS = "update accessories set title = ? where id = ?";
    public static final String DELETE_ACCS = "delete from accessories where id = ?";

    private static Connection connection;

    public AccessoriesDAOImpl () {
        connection = Connector.getConn();
    }

    @Override
    public boolean create(Accessories accs) {

        boolean result = false;

        try {

            PreparedStatement createAccs = connection.prepareStatement(CREATE_ACCS, Statement.RETURN_GENERATED_KEYS);
            createAccs.setInt(1, accs.getId());
            createAccs.setString(2, accs.getAccsName());

            result = createAccs.execute();

            ResultSet createdAccsRS = createAccs.getGeneratedKeys();
            createdAccsRS.next();
            accs.setId(createdAccsRS.getInt(1));
            createAccs.close();

        } catch (SQLException e){
            e.printStackTrace();
        }

        return result;
    }

    @Override
    public List<Accessories> getAll()
    {
        ArrayList<Accessories> accsList = new ArrayList<Accessories>();
        try
        {
            Statement getAll  = connection.createStatement();
            ResultSet allAccsRS = getAll.executeQuery(GET_ALL);
            Accessories accs;

            while (allAccsRS.next())
            {
                accs = new Accessories();

                Integer id = allAccsRS.getInt(1);
                String itemName = allAccsRS.getString(2);

                accs.setId(id);
                accs.setAccsName(itemName);

                accsList.add(accs);
            }
            getAll.close();

        } catch (SQLException e){
            e.printStackTrace();
        }

        return accsList;
    }

    @Override
    public Accessories getAccs (Integer id)
    {
        Accessories accs = null;
        try {

            PreparedStatement getById = connection.prepareStatement(GET_BY_ID);
            getById.setInt(1, id);
            ResultSet getAccsRS = getById.executeQuery();

            while (getAccsRS.next())
            {
                String itemName = getAccsRS.getString(2);
                accs.setId(id);
                accs.setAccsName(itemName);
            }
            getById.close();

        } catch (SQLException e){
            e.printStackTrace();
        }

        return accs;
    }

    @Override
    public boolean update(Accessories accs) {
        boolean result = false;

        try {

            PreparedStatement updateAccs = connection.prepareStatement(UPDATE_ACCS);
            updateAccs.setString(1, accs.getAccsName());
            updateAccs.setInt(2, accs.getId());

            result = updateAccs.execute();
            updateAccs.close();
        } catch (SQLException e){
            e.printStackTrace();
        }

        return result;
    }

    @Override
    public boolean delete(Accessories accs)
    {
        boolean result = false;

        try {
            PreparedStatement deleteAccs = connection.prepareStatement(DELETE_ACCS);
            deleteAccs.setInt(1, accs.getId());

            result = deleteAccs.execute();
            deleteAccs.close();
        } catch (SQLException e){
            e.printStackTrace();
        }

        return result;
    }
}
