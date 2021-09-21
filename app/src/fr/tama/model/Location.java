package fr.tama.model;

public class Location {

    private String name;
    private String action;
    private Location next;
    private Location previous;
    private static Location[] locations=null;

    public Location(String name, String action, Location next, Location previous) {
        this.name = name;
        this.action = action;
        this.next = next;
        this.previous = previous;
    }

    public String getName() {
        return name;
    }

    public String getAction() {
        return action;
    }

    public Location getNext() {
        return next;
    }

    public Location getPrevious() {
        return previous;
    }

    private static void setupLocations(){
        Location cuisine=null,chambre=null,toilette=null,salledb=null,jardin=null;
        cuisine = new Location("Cuisine","Manger",jardin,chambre);
        chambre = new Location("Chambre","Dormir",cuisine,salledb);
        salledb = new Location("Salle de Bain","Laver",chambre,toilette);
        toilette = new Location("Toilette","Besoins",salledb,null);
        jardin = new Location("Jardin","Jouer",null,cuisine);
        locations = new Location[]{cuisine,chambre,toilette,salledb,jardin};
    }

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
