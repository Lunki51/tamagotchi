package fr.tama.controller;

import fr.tama.model.*;
import fr.tama.model.*;

import java.io.File;
import java.sql.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * GameSave class represent a save inside the database. A save is identified by a slot id. There can be only 3 saves at the same time. When loading or creating a save, use the static
 * methods to create an object that represent the data in the database. On modified, use the save() method on the given object to update the database with the new data. The save slot cannot be changed
 * A save can be deleted from the database with the delete method.
 */
public class GameSave {

    private boolean deleted = false;
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

    /**
     * Save the GameSave object into the database.Create a new save from the profil wich this GameSave correspond.
     */
    public void save(){
        if(deleted)return;
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

            sql = "UPDATE profile SET name = ?,type=? WHERE slot = ?";
            pstmt = connection.prepareStatement(sql);
            String name = tamagotchi.getClass().getName();
            String[] tab = name.split("\\.");
            pstmt.setString(1,this.getTamagotchi().getName());
            pstmt.setString(2,tab[tab.length-1]);
            pstmt.setInt(3,this.slot);
            pstmt.executeUpdate();
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

    /**
     * Delete the corresponding save from the database.Once deleted, the save cannot be saved again.
     */
    public void delete(){
        this.deleted = true;
        while(connection!=null) {
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
            String sql = "DELETE from profile WHERE slot =?";
            PreparedStatement pstmt = connection.prepareStatement(sql);
            pstmt.setInt(1,this.slot);
            pstmt.executeUpdate();
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

    /**
     * Load a save from the database and return the corresponding object
     * @param slot the slot to load
     * @return an object that represent the save in the database
     */
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

            String request1 = "SELECT * FROM profile WHERE slot =?";
            String request2 = "SELECT * FROM save WHERE profile = ? ORDER BY DATE";
            PreparedStatement ignored = connection.prepareStatement(request1);
            ignored.setInt(1,slot);
            ResultSet rs = ignored.executeQuery();
                if(rs.next()){
                    String name = rs.getString("name");
                    date =  LocalDateTime.parse(rs.getString("creationDate"),FORMAT);
                    String type = rs.getString("type");
                            ignored = connection.prepareStatement(request2);
                            ignored.setInt(1,slot);
                            ResultSet rs2 = ignored.executeQuery();
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

    /**
     * Create a new save in the database and replace an older one if override is needed.
     * @param slot the slot where to create the save
     * @param tamagotchi the tamagotchi that is saved
     * @param defaultLoc the location where the tamagotchi start
     * @return an object that represent the save in the database
     */
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

    /**
     * Return the date when the save have been created
     * @return a LocalDateTime that represent the date of the save creation
     */
    public LocalDateTime getDate() {
        return date;
    }

    /**
     * The slot where the save is saved
     * @return the slot
     */
    public int getSlot() {
        return slot;
    }

    /**
     * Return the tamagotchi object that represent the save animal
     * @return the tamagotchi object
     */
    public Tamagotchi getTamagotchi() {
        return tamagotchi;
    }

    /**
     * Return the current location of the tamagotchi
     * @return a Location object that represent the current location of the tamagotchi
     */
    public Location getLocation() {
        return location;
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
}
