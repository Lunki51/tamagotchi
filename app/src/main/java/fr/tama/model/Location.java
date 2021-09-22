package fr.tama.model;

import fr.tama.controller.LangFile;

/**
 * Represent a location in the tamagotchi game
 */
public class Location {

    private String name;
    private String action;
    private Location next;
    private Location previous;
    private static Location[] locations=null;

    /**
     * Create a new location
     * @param name the name of the location
     * @param action the name of the action the location can make
     * @param next the next location where you can go
     * @param previous the previous location where you can go
     */
    public Location(String name, String action, Location next, Location previous) {
        this.name = name;
        this.action = action;
        this.next = next;
        this.previous = previous;
    }

    /**
     * Return the name of the location
     * @return the name of the location
     */
    public String getName() {
        return name;
    }

    /**
     * Return the possible action of the location
     * @return the possible action of the location
     */
    public String getAction() {
        return action;
    }

    /**
     * Return the next location possible
     * @return the next location possible
     */
    public Location getNext() {
        return next;
    }

    /**
     * Return the previous location possible
     * @return the previous location possible
     */
    public Location getPrevious() {
        return previous;
    }

    /**
     * Setup the default locations of the game
     */
    private static void setupLocations(){
        Location kitchen,bedroom=null,toilet=null,bathroom=null,garden=null;
        kitchen = new Location(LangFile.getLangFile().getString("location_kitchen"), LangFile.getLangFile().getString("attribute_action_hunger"),garden,bedroom);
        bedroom = new Location(LangFile.getLangFile().getString("location_bedroom"),LangFile.getLangFile().getString("attribute_action_tiredness"),kitchen,bathroom);
        bathroom = new Location(LangFile.getLangFile().getString("location_bathroom"),LangFile.getLangFile().getString("attribute_action_cleanliness"),bedroom,toilet);
        toilet = new Location(LangFile.getLangFile().getString("location_toilet"),LangFile.getLangFile().getString("attribute_action_toilet"),bathroom,null);
        garden = new Location(LangFile.getLangFile().getString("location_garden"),LangFile.getLangFile().getString("attribute_action_happiness"),null,kitchen);
        locations = new Location[]{kitchen,bedroom,toilet,bathroom,garden};
    }

    /**
     * Search for a location by it's name if the default locations are not defined define them
     * @param name the name of the location to search
     * @return the location if found
     */
    public static Location getLocation(String name){
        if(locations==null){
            setupLocations();
        }
        for(Location loc : locations){
            if(loc.getName().equals(name))return loc;
        }
        return null;
    }
}
