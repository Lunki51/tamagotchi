package fr.tama.controller;

import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashSet;

import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class DBConfig {
    private static HashSet<String> columns;
    
    static
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
            Statement stm = DBConnection.getConnection().createStatement();
            ResultSet st = stm.executeQuery("SELECT * FROM config");
            if(st.next() && columns.contains(columnName))
                return st;
        }
        catch(SQLException e)
        {
            e.printStackTrace();
        }

        throw new RuntimeException("Query returned null");
    }

    
    public static int getInt(String columnName)
    {
        try {
            return getValue(columnName).getInt(columnName);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        throw new RuntimeException("Query returned null");
    }
    
    public static boolean getBoolean(String columnName)
    {
        try {
            return getValue(columnName).getBoolean(columnName);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        
        throw new RuntimeException("Query returned null");
    }
    
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
                throw new RuntimeException("Unknown ColumnName in config table: " + columnName);
            
            return DBConnection.getConnection().prepareStatement("UPDATE config SET " + columnName + "=? WHERE TRUE");
        }
        catch(SQLException e)
        {
            e.printStackTrace();
        }

        throw new RuntimeException("SQL error was encountered");
    }

    public static void setBoolean(String columnName, Boolean o)
    {
        try {
            PreparedStatement p = setValue(columnName);
            p.setBoolean(1, o);
            p.executeUpdate();
            
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    
    public static void setInt(String columnName, int o)
    {
        try {
            PreparedStatement p = setValue(columnName);
            p.setInt(1, o);
            p.executeUpdate();
            
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void setString(String columnName, String o)
    {
        try {
            PreparedStatement p = setValue(columnName);
            p.setString(1, o);
            p.executeUpdate();
            
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}
