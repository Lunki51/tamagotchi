package fr.tama.model;

public class Location {

    private String name;
    private String action;
    private Location next;
    private Location previous;

    public Location(String name, String action, Location next, Location previous) {
        this.name = name;
        this.action = action;
        this.next = next;
        this.previous = previous;
    }
}
