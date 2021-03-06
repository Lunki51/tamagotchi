package fr.tama.model;

import fr.tama.controller.DBConnection;

import java.sql.*;
//import java.time.format.DateTimeFormatter;
import java.util.Date;

/**
 * GameSave class represent a save inside the database. A save is identified by a slot id. There can be only 3 saves at the same time. When loading or creating a save, use the static
 * methods to create an object that represent the data in the database. On modified, use the save() method on the given object to update the database with the new data. The save slot cannot be changed
 * A save can be deleted from the database with the delete method.
 */
public class GameSave {

    private boolean deleted = false;
    private final Date creationDate;
    private Date lastSeen;
    private final int slot;
    private final Tamagotchi tamagotchi;
    private Location location;
    private int difficulty;
    //private static final DateTimeFormatter FORMAT = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

    private GameSave(Date creationDate,Date lastSeen, Tamagotchi tamagotchi,int slot,Location location){
        this.creationDate=creationDate;
        this.lastSeen=lastSeen;
        this.slot = slot;
        this.tamagotchi=tamagotchi;
        this.location = location;
    }

    public void updateLastSeen(){
        this.lastSeen = new Date();
    }

    public void setLastSeen(long time){
        this.lastSeen = new Date(time);
    }

    /**
     * Save the GameSave object into the database.Create a new save from the profil wich this GameSave correspond.
     */
    public void save(){
        if(deleted)return;
        try{
            String sql = "INSERT INTO save(date,location,mood,shape,current,profile,level) VALUES(?,?,?,?,?,?,?)";

            PreparedStatement pstmt = DBConnection.getConnection().prepareStatement(sql);
            pstmt.setLong(1, lastSeen.getTime());
            pstmt.setString(2,this.location.getName());
            pstmt.setString(3, tamagotchi.getMood().toString());
            pstmt.setString(4, tamagotchi.getShape().toString());
            pstmt.setString(5, tamagotchi.getCurrent().toString());
            pstmt.setInt(6,slot);
            pstmt.setInt(7,tamagotchi.getLevel().getValue());
            pstmt.executeUpdate();

            ResultSet generatedKeys = pstmt.getGeneratedKeys();
            generatedKeys.next();
            int id = generatedKeys.getInt(1);
            for(Attribute attr : tamagotchi.getAttributes()){
                    sql = "INSERT INTO attribute VALUES(null,?,?,?,?)";
                    try (PreparedStatement pstmt2 = DBConnection.getConnection().prepareStatement(sql)) {
                        pstmt2.setString(1, attr.getName());
                        pstmt2.setInt(2, attr.getValue());
                        pstmt2.setInt(3, id);
                        pstmt2.setInt(4,attr.getCoolDown());
                        pstmt2.executeUpdate();
                    } catch (SQLException e) {
                        System.out.println(e.getMessage());
                    }

                }

            sql = "UPDATE profile SET name = ?,type=?,sex=? WHERE slot = ?";
            pstmt = DBConnection.getConnection().prepareStatement(sql);
            String name = tamagotchi.getClass().getName();
            String[] tab = name.split("\\.");
            pstmt.setString(1,this.getTamagotchi().getName());
            pstmt.setString(2,tab[tab.length-1]);
            pstmt.setBoolean(3,tamagotchi.isSex());
            pstmt.setInt(4,this.slot);
            pstmt.executeUpdate();
        }catch(SQLException e){
                e.printStackTrace();
        }
    }

    /**
     * Delete the corresponding save from the database.Once deleted, the save cannot be saved again.
     */
    public void delete(){
        this.deleted = true;
        try{
            String sql = "DELETE from profile WHERE slot =?";
            PreparedStatement pstmt = DBConnection.getConnection().prepareStatement(sql);
            pstmt.setInt(1,this.slot);
            pstmt.executeUpdate();
        }catch(SQLException e){
            e.printStackTrace();
        }
    }

    /**
     * Load a save from the database and return the corresponding object
     * @param slot the slot to load
     * @return an object that represent the save in the database
     */
    public static GameSave loadSave(int slot){
        Tamagotchi tamagotchi = null;
        Date creationDate;
        Date lastSeen= new Date();
        Location location = null;

        try{
            String request1 = "SELECT * FROM profile WHERE slot =?";
            String request2 = "SELECT * FROM save WHERE profile = ? ORDER BY saveID DESC";
            PreparedStatement ignored = DBConnection.getConnection().prepareStatement(request1);
            ignored.setInt(1,slot);
            ResultSet rs = ignored.executeQuery();
                if(rs.next()){
                    String name = rs.getString("name");
                    boolean sex = rs.getBoolean("sex");
                    int difficulty = rs.getInt("difficulty");
                    creationDate = new Date(rs.getLong("creationDate"));
                    String type = rs.getString("type");

                            ignored = DBConnection.getConnection().prepareStatement(request2);
                            ignored.setInt(1,slot);
                            ResultSet rs2 = ignored.executeQuery();
                    Level level = Level.getLevel(rs2.getInt("level"));
                        if(rs2.next()){
                            lastSeen = new Date(rs2.getLong("date"));
                             switch (type) {
                                case "Chien" :
                                    tamagotchi = new Chien(
                                        statusFromString(rs2.getString("mood")),
                                        statusFromString(rs2.getString("shape")),
                                        currentFromString(rs2.getString("current")),
                                        sex,
                                        name, level,difficulty);
                                    break;
                                case "Chat" :
                                    tamagotchi = new Chat(
                                        statusFromString(rs2.getString("mood")),
                                        statusFromString(rs2.getString("shape")),
                                        currentFromString(rs2.getString("current")),
                                        sex,
                                        name,level,difficulty);
                                    break;
                                 case "Lapin" :
                                     tamagotchi = new Lapin(
                                        statusFromString(rs2.getString("mood")),
                                        statusFromString(rs2.getString("shape")),
                                        currentFromString(rs2.getString("current")),
                                        sex,
                                        name,level,difficulty);
                                     break;
                                 case "Robot" :
                                     tamagotchi = new Robot(
                                        statusFromString(rs2.getString("mood")),
                                        statusFromString(rs2.getString("shape")),
                                        currentFromString(rs2.getString("current")),
                                        sex,
                                        name,level,difficulty);
                                     break;
                                 default :
                                     return null;
                            }
                            try{
                                location = Location.getLocation(rs2.getString("location"));
                            }catch(AttributeNotFoundException e){
                                return null;
                            }

                            String sql = "SELECT * FROM attribute WHERE save =?";
                            ignored = DBConnection.getConnection().prepareStatement(sql);
                            ignored.setInt(1,rs2.getInt("saveID"));
                            ResultSet rs3 = ignored.executeQuery();
                            while(rs3.next()){
                                Attribute attrib = tamagotchi.getAttribute(rs3.getString("name"));
                                attrib.setValue(rs3.getInt("value"));
                                attrib.setCoolDown(rs3.getInt("cooldown"));
                            }
                        }
                }else return null;
        }catch(SQLException e){
            e.printStackTrace();
            return null;
        }
        return new GameSave(creationDate,lastSeen,tamagotchi,slot,location);
    }

    /**
     * Create a new save in the database and replace an older one if override is needed.
     * @param slot the slot where to create the save
     * @param tamagotchi the tamagotchi that is saved
     * @param defaultLoc the location where the tamagotchi start
     * @return an object that represent the save in the database
     */
    public static GameSave createSave(int slot,Tamagotchi tamagotchi,Location defaultLoc){
        Date date = new Date();

        try{

        String sql = "INSERT INTO profile VALUES(?,?,?,?,?,?)";
        PreparedStatement pstmt = DBConnection.getConnection().prepareStatement(sql);
            pstmt.setInt(1, slot);
            String name = tamagotchi.getClass().getName();
            String[] tab = name.split("\\.");
            pstmt.setString(2, tab[tab.length-1]);
            pstmt.setBoolean(3,tamagotchi.isSex());
            pstmt.setString(4, tamagotchi.getName());
            pstmt.setLong(5, date.getTime());
            pstmt.setInt(6,tamagotchi.getDifficulty());
            pstmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        GameSave save = new GameSave(date,date,tamagotchi,slot,defaultLoc);
        save.save();
        return save;
    }

    /**
     * Return the date when the save have been created
     * @return a LocalDateTime that represent the date of the save creation
     */
    public Date getCreationDate() {
        return creationDate;
    }

    public Date getLastSeen() {
        return lastSeen;
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

    public void setLocation(Location location){
        this.location=location;
    }

    private static Status statusFromString(String status){
        switch (status) {
            case "VERY_BAD":
                return Status.VERY_BAD;
            case "BAD" :
                return Status.BAD;
            case "VERY_GOOD" :
                return Status.VERY_GOOD;
            default :
                return Status.GOOD;
        }
    }

    private static Current currentFromString(String current){
        switch (current) {
            case "AWAKE" :
                return Current.AWAKE;
            case "ASLEEP" :
                return Current.ASLEEP;
            default :
                return Current.DEAD;
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        GameSave save = (GameSave) o;
        return deleted == save.deleted && slot == save.slot && creationDate.equals(save.creationDate) && tamagotchi.equals(save.tamagotchi) && location.equals(save.location) && lastSeen.equals(save.lastSeen);
    }

    @Override
    public String toString() {
        return "GameSave{" +
                "deleted=" + deleted +
                ", creationDate=" + creationDate +
                ", lastSeen=" + lastSeen +
                ", slot=" + slot +
                ", tamagotchi=" + tamagotchi +
                ", location=" + location +
                '}';
    }

}
