package fr.tama.model;

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
        Location cuisine=null,chambre=null,toilette=null,salledb=null,jardin=null;
        cuisine = new Location("Cuisine","Manger",jardin,chambre);
        chambre = new Location("Chambre","Dormir",cuisine,salledb);
        salledb = new Location("Salle de Bain","Laver",chambre,toilette);
        toilette = new Location("Toilette","Besoins",salledb,null);
        jardin = new Location("Jardin","Jouer",null,cuisine);
        locations = new Location[]{cuisine,chambre,toilette,salledb,jardin};
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
