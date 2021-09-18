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
    private static Connection connection;
    private static final DateTimeFormatter FORMAT = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

    private GameSave(LocalDateTime date, Tamagotchi tamagotchi,int slot){
        this.date=date;
        this.slot = slot;
        this.tamagotchi=tamagotchi;
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
            String url = "jdbc:sqlite:"+dbfile.getAbsolutePath()+"\\tama-db";
            connection= DriverManager.getConnection(url);
            String sql = "INSERT INTO save(date, mood, shape, current, faim, fatigue, toilette, proprete, bonheur, profile) VALUES(?,?,?,?,?,?,?,?,?,?)";

            try (PreparedStatement pstmt = connection.prepareStatement(sql)) {
                pstmt.setString(1, this.date.format(FORMAT));
                pstmt.setString(2, tamagotchi.getMood().toString());
                pstmt.setString(3, tamagotchi.getShape().toString());
                pstmt.setString(4, tamagotchi.getCurrent().toString());
                pstmt.setInt(5, tamagotchi.getAttribute("faim").getValue());
                pstmt.setInt(6,  tamagotchi.getAttribute("fatigue").getValue());
                pstmt.setInt(7,  tamagotchi.getAttribute("toilette").getValue());
                pstmt.setInt(8,  tamagotchi.getAttribute("propete").getValue());
                pstmt.setInt(9,  tamagotchi.getAttribute("bonheur").getValue());
                pstmt.setInt(10,slot);
                pstmt.executeUpdate();
            } catch (SQLException e) {
                System.out.println(e.getMessage());
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

        try{
            File dbfile =new File(".");
            String url = "jdbc:sqlite:"+dbfile.getAbsolutePath()+"\\tama-db";
            connection= DriverManager.getConnection(url);

            String request1 = "SELECT * FROM profile WHERE slot ="+slot;
            String request2 = "SELECT * FROM save WHERE profile ="+slot+" ORDER BY DATE";
            try (Statement ignored = connection.createStatement();ResultSet rs = ignored.executeQuery(request1)){
                if(rs.next()){

                    String name = rs.getString("name");
                    date =  LocalDateTime.parse(rs.getString("date"),FORMAT);
                    String type = rs.getString("type");
                    try (
                            Statement ignored2 = connection.createStatement();
                            ResultSet rs2 = ignored2.executeQuery(request2)
                    ){
                        if(rs.next()){
                            tamagotchi = switch (type) {
                                case "Chien" -> new Chien(
                                        statusFromString(rs2.getString("mood")),
                                        statusFromString(rs2.getString("shape")),
                                        currentFromString(rs2.getString("status")));
                                case "Chat" -> new Chat(
                                        statusFromString(rs2.getString("mood")),
                                        statusFromString(rs2.getString("shape")),
                                        currentFromString(rs2.getString("status")));
                                case "Lapin" -> new Lapin(
                                        statusFromString(rs2.getString("mood")),
                                        statusFromString(rs2.getString("shape")),
                                        currentFromString(rs2.getString("status")));
                                case "Robot" -> new Robot(
                                        statusFromString(rs2.getString("mood")),
                                        statusFromString(rs2.getString("shape")),
                                        currentFromString(rs2.getString("status")));
                                default -> new Robot(Status.GOOD, Status.GOOD, Current.AWAKE);
                            };
                            tamagotchi.setAttribute("faim",rs2.getInt("faim"));
                            tamagotchi.setAttribute("fatigue",rs2.getInt("fatigue"));
                            tamagotchi.setAttribute("toilette",rs2.getInt("toilette"));
                            tamagotchi.setAttribute("proprete",rs2.getInt("proprete"));
                            tamagotchi.setAttribute("bonheur",rs2.getInt("bonheur"));
                        }
                    }catch(SQLException e){
                        System.out.println(e.getMessage());
                    }
                }
            }
            catch (SQLException e) {
                System.out.println(e.getMessage());
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

        return new GameSave(date,tamagotchi,slot);
    }

    public static GameSave createSave(int slot,Tamagotchi tamagotchi){
        while(connection!=null){
            try{
                Thread.sleep(100);
            }catch(InterruptedException e){
                e.printStackTrace();
            }

        }

        LocalDateTime date = LocalDateTime.now();
        String sql = "INSERT INTO profile VALUES(?,?,?,?)";

        try (PreparedStatement pstmt = connection.prepareStatement(sql)) {
            pstmt.setInt(1, slot);
            pstmt.setString(2, tamagotchi.getClass().getName());
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

        return new GameSave(date,tamagotchi,slot);
    }

}
