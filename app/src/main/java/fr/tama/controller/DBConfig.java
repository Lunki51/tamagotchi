package fr.tama.controller;

import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashSet;

import java.sql.PreparedStatement;
import java.sql.ResultSet;

/**
 * Class used as an interface to get or set configurations in database
 */
public class DBConfig {
    private static HashSet<String> columns;
    
    static //Columns of the table in database
    {
        columns = new HashSet<>();
        columns.add("lang");
        columns.add("mute");
        columns.add("volume");
    }
    
    private static ResultSet getValue(String columnName)
    {
        try
        {
            if(!columns.contains(columnName))
                throw new RuntimeException("Unknown COLUMN : " + columnName);

            Statement stm = DBConnection.getConnection().createStatement();
            ResultSet st = stm.executeQuery("SELECT * FROM config");

            if(st.next())
                return st;
        }
        catch(SQLException e)
        {
            e.printStackTrace();
        }

        throw new RuntimeException("Query returned null");
    }

    /**
     * Method that retrieve an integer in a specified column
     * @param columnName Column to query
     * @return Integer stored in queried column
     */
    public static int getInt(String columnName)
    {
        try {
            return getValue(columnName).getInt(columnName);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        throw new RuntimeException("Query returned null");
    }
    
    /**
     * Method that retrieve a boolean in a specified column
     * @param columnName Column to query
     * @return Boolean stored in queried column
     */
    public static boolean getBoolean(String columnName)
    {
        try {
            return getValue(columnName).getBoolean(columnName);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        
        throw new RuntimeException("Query returned null");
    }
    
    /**
     * Method that retrieve a string in a specified column
     * @param columnName Column to query
     * @return String stored in queried column
     */
    public static String getString(String columnName)
    {
        try {
            return getValue(columnName).getString(columnName);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        throw new RuntimeException("Query returned null");
    }

    private static PreparedStatement setValue(String columnName)
    {
        try
        {
            //Prevents SQL injections and invalid columnName
            if(!columns.contains(columnName))
                throw new RuntimeException("Unknown COLUMN : " + columnName);
            
            return DBConnection.getConnection().prepareStatement("UPDATE config SET " + columnName + "=? WHERE TRUE");
        }
        catch(SQLException e)
        {
            e.printStackTrace();
        }

        throw new RuntimeException("An SQL error was encountered");
    }

    /**
     * Method that put a boolean in a specified column
     * @param columnName Column to query
     * @param value Value to be put
     */
    public static void setBoolean(String columnName, Boolean value)
    {
        try {
            PreparedStatement p = setValue(columnName);
            p.setBoolean(1, value);
            p.executeUpdate();
            
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    
    /**
     * Method that put an integer in a specified column
     * @param columnName Column to query
     * @param value Value to be put
     */
    public static void setInt(String columnName, int value)
    {
        try {
            PreparedStatement p = setValue(columnName);
            p.setInt(1, value);
            p.executeUpdate();
            
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * Method that put a string in a specified column
     * @param columnName Column to query
     * @param value Value to be put
     */
    public static void setString(String columnName, String value)
    {
        try {
            PreparedStatement p = setValue(columnName);
            p.setString(1, value);
            p.executeUpdate();
            
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}
