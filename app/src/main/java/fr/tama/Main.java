package fr.tama;

import fr.tama.controller.DBConnection;
import fr.tama.model.GameSave;
import fr.tama.controller.LangFile;
import fr.tama.model.*;
import fr.tama.view.GameView;

public class Main {

    public static void main(String[] args){

        Tamagotchi tamagotchi = new Chien(Status.GOOD,Status.VERY_BAD, Current.AWAKE,true,"michel");
        Location location;
        try{
            location = Location.getLocation("kitchen");
        }catch(AttributeNotFoundException e){
            location=Location.getDefaultLocation();
        }
        GameSave newSave = GameSave.createSave(0,tamagotchi, location);
        newSave.getTamagotchi().setAttribute("faim",100);
        newSave.save();

        newSave = GameSave.loadSave(0);

        newSave.delete();

        LangFile file = LangFile.getLangFile();

        LangFile.setLang("en");


        DBConnection.closeConnection();

        GameView view = new GameView();
    }
}
