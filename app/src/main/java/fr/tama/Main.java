package fr.tama;

import fr.tama.controller.DBConnection;
import fr.tama.controller.GameSave;
import fr.tama.model.*;
import fr.tama.view.GameView;

public class Main {

    public static void main(String[] args){

        Tamagotchi tamagotchi = new Chien(Status.GOOD,Status.VERY_BAD, Current.AWAKE,true,"michel");

        GameSave newSave = GameSave.createSave(0,tamagotchi, Location.getLocation("kitchen"));
        newSave.getTamagotchi().setAttribute("hunger",100);
        newSave.save();

        newSave = GameSave.loadSave(0);

        newSave.delete();

        DBConnection.closeConnection();


        Location[] locations = Location.getLocations();

        for(Location loc : locations){
            System.out.println(loc.getName());
            System.out.println(loc.getAction());
            System.out.println(loc.getPrevious());
            System.out.println(loc.getNext());
        }

        new GameView();
    }
}
