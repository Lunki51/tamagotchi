package fr.tama.controller;

import java.io.File;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
*   Class that ensures that only one connection is opened with the database
*/
public class DBConnection {

    private static Connection connection = null;

    private DBConnection(){}

    /**
     * If no connection is established with the database, initialize the connection
     * Return the established connection
     * @return Connection instance opened with the database
     */
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

    /**
     * Close connection with the database
     */
    public static void closeConnection(){
        try{
            connection.close();
            connection = null;
        }catch (SQLException e){
            e.printStackTrace();
        }

    }
}
