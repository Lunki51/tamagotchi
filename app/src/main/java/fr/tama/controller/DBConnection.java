package fr.tama.controller;

import java.io.File;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnection {

    private static Connection connection = null;

    private DBConnection(){}

    public static Connection getConnection(){
        if(connection==null){
            try{
                File dbfile =new File(".");
                String url = "jdbc:sqlite:"+dbfile.getAbsolutePath().split("/\\.")[0]+"/tama-db.db";
                connection= DriverManager.getConnection(url);
            }catch(SQLException e){
                e.printStackTrace();
            }
        }
        return connection;
    }

    public static void closeConnection(){
        try{
            connection.close();
            connection = null;
        }catch (SQLException e){
            e.printStackTrace();
        }

    }
}
