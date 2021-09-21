package fr.tama.controller;

import fr.tama.model.*;

import java.io.File;
import java.sql.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class GameSave {

    private LocalDateTime date;
    private int slot;
    private Tamagotchi tamagotchi;
    private Location location;
    private static Connection connection;
    private static final DateTimeFormatter FORMAT = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

    private GameSave(LocalDateTime date, Tamagotchi tamagotchi,int slot,Location location){
        this.date=date;
        this.slot = slot;
        this.tamagotchi=tamagotchi;
        this.location = location;
    }

    public void save(){
        while(connection!=null){
            try{
                Thread.sleep(100);
            }catch(InterruptedException e){
                e.printStackTrace();
            }

        }
        try{
            File dbfile =new File(".");
            String url = "jdbc:sqlite:"+dbfile.getAbsolutePath()+"/app/tama-db.db";
            connection= DriverManager.getConnection(url);
            String sql = "INSERT INTO save(date,location,mood,shape,current,profile) VALUES(?,?,?,?,?,?)";

            PreparedStatement pstmt = connection.prepareStatement(sql);
            pstmt.setString(1, this.date.format(FORMAT));
            pstmt.setString(2,this.location.getName());
            pstmt.setString(3, tamagotchi.getMood().toString());
            pstmt.setString(4, tamagotchi.getShape().toString());
            pstmt.setString(5, tamagotchi.getCurrent().toString());
            pstmt.setInt(6,slot);
            pstmt.executeUpdate();
            ResultSet generatedKeys = pstmt.getGeneratedKeys();
            generatedKeys.next();
            int id = generatedKeys.getInt(1);
            System.out.println(id);

                for(Attribute attr : tamagotchi.getAttributes()){
                    sql = "INSERT INTO attribute VALUES(null,?,?,?)";
                    try (PreparedStatement pstmt2 = connection.prepareStatement(sql)) {
                        pstmt2.setString(1, attr.getName());
                        pstmt2.setInt(2, attr.getValue());
                        pstmt2.setInt(3, id);
                        pstmt2.executeUpdate();
                    } catch (SQLException e) {
                        System.out.println(e.getMessage());
                    }

                }



        }catch(SQLException e){
                e.printStackTrace();
        }
        finally{
            try{
                connection.close();
                connection = null;
            }catch(SQLException e){
                e.printStackTrace();
            }
        }
    }

    public void delete(){
        while(connection!=null){
            try{
                Thread.sleep(100);
            }catch(InterruptedException e){
                e.printStackTrace();
            }

        }
        try{
            File dbfile =new File(".");
            String url = "jdbc:sqlite:"+dbfile.getAbsolutePath()+"/app/tama-db.db";
            connection= DriverManager.getConnection(url);
            String sql = "DELETE from profile WHERE slot ="+this.slot;
            String sql2 = "DELETE FROM save WHERE profile="+this.slot;
            String sql3 = "SELECT saveID FROM save WHERE profile="+this.slot;
            Statement ignored = connection.createStatement();
            ResultSet rs = ignored.executeQuery(sql3);
            while(rs.next()){
                String sql4 = "DELETE FROM attribute WHERE save="+rs.getString("id");
                ignored.executeQuery(sql3);
            }
            ignored.executeQuery(sql2);
            ignored.executeQuery(sql);
        }catch(SQLException e){
            e.printStackTrace();
        }
        finally{
            try{
                connection.close();
                connection = null;
            }catch(SQLException e){
                e.printStackTrace();
            }
        }
    }

    private static Status statusFromString(String status){
        return switch (status) {
            case "VERY_BAD" -> Status.VERY_BAD;
            case "BAD" -> Status.BAD;
            case "GOOD" -> Status.GOOD;
            case "VERY_GOOD" -> Status.VERY_GOOD;
            default -> Status.GOOD;
        };
    }

    private static Current currentFromString(String current){
        return switch (current) {
            case "AWAKE" -> Current.AWAKE;
            case "ASLEEP" -> Current.ASLEEP;
            case "DEAD" -> Current.DEAD;
            default -> Current.DEAD;
        };
    }

    public static GameSave loadSave(int slot){
        while(connection!=null){
            try{
                Thread.sleep(100);
            }catch(InterruptedException e){
                e.printStackTrace();
            }

        }

        Tamagotchi tamagotchi = null;
        LocalDateTime date = LocalDateTime.now();
        Location location = null;

        try{
            File dbfile =new File(".");
            String url = "jdbc:sqlite:"+dbfile.getAbsolutePath()+"/app/tama-db.db";
            connection= DriverManager.getConnection(url);

            String request1 = "SELECT * FROM profile WHERE slot ="+slot;
            String request2 = "SELECT * FROM save WHERE profile ="+slot+" ORDER BY DATE";
            Statement ignored = connection.createStatement();
            ResultSet rs = ignored.executeQuery(request1);
                if(rs.next()){
                    String name = rs.getString("name");
                    date =  LocalDateTime.parse(rs.getString("date"),FORMAT);
                    String type = rs.getString("type");
                            ResultSet rs2 = ignored.executeQuery(request2);
                        if(rs.next()){
                            tamagotchi = switch (type) {
                                case "Chien" -> new Chien(
                                        statusFromString(rs2.getString("mood")),
                                        statusFromString(rs2.getString("shape")),
                                        currentFromString(rs2.getString("status")),
                                        name);
                                case "Chat" -> new Chat(
                                        statusFromString(rs2.getString("mood")),
                                        statusFromString(rs2.getString("shape")),
                                        currentFromString(rs2.getString("status")),
                                        name);
                                case "Lapin" -> new Lapin(
                                        statusFromString(rs2.getString("mood")),
                                        statusFromString(rs2.getString("shape")),
                                        currentFromString(rs2.getString("status")),
                                        name);
                                case "Robot" -> new Robot(
                                        statusFromString(rs2.getString("mood")),
                                        statusFromString(rs2.getString("shape")),
                                        currentFromString(rs2.getString("status")),
                                        name);
                                default -> new Robot(Status.GOOD, Status.GOOD, Current.AWAKE,"");
                            };
                            location = Location.getLocation(rs2.getString("location"));
                            String sql = "SELECT * FROM attribute WHERE save ="+rs2.getInt("saveID");
                            ResultSet rs3 = ignored.executeQuery(request1);
                            while(rs3.next()){
                                tamagotchi.setAttribute(rs3.getString("name"),rs3.getInt("value"));
                            }
                        }
                }
        }catch(SQLException e){
            e.printStackTrace();
        }
        finally{
            try{
                connection.close();
                connection = null;
            }catch(SQLException e){
                e.printStackTrace();
            }
        }

        return new GameSave(date,tamagotchi,slot,location);
    }

    public static GameSave createSave(int slot,Tamagotchi tamagotchi,Location defaultLoc){
        while(connection!=null){
            try{
                Thread.sleep(100);
            }catch(InterruptedException e){
                e.printStackTrace();
            }

        }

        LocalDateTime date = LocalDateTime.now();

        try{

        File dbfile =new File(".");
        String url = "jdbc:sqlite:"+dbfile.getAbsolutePath()+"/app/tama-db.db";
        connection= DriverManager.getConnection(url);

        String sql = "INSERT INTO profile VALUES(?,?,?,?)";


        PreparedStatement pstmt = connection.prepareStatement(sql);
            pstmt.setInt(1, slot);
            String name = tamagotchi.getClass().getName();
            String[] tab = name.split("\\.");
            pstmt.setString(2, tab[tab.length-1]);
            pstmt.setString(3, tamagotchi.getName());
            pstmt.setString(4, FORMAT.format(date));
            pstmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }finally{
            try{
                connection.close();
                connection = null;
            }catch(SQLException e){
                e.printStackTrace();
            }
        }
        GameSave save = new GameSave(date,tamagotchi,slot,defaultLoc);
        save.save();
        return save;
    }

    public LocalDateTime getDate() {
        return date;
    }

    public int getSlot() {
        return slot;
    }

    public Tamagotchi getTamagotchi() {
        return tamagotchi;
    }

    public Location getLocation() {
        return location;
    }
}
