package fr.tama;

import fr.tama.controller.DBConnection;
import fr.tama.model.GameSave;
import fr.tama.controller.LangFile;
import fr.tama.model.*;
import fr.tama.view.GameView;

public class Main {

    public static void main(String[] args){

        Tamagotchi tamagotchi = new Chien(Status.GOOD,Status.VERY_BAD, Current.AWAKE,true,"michel");

        GameSave newSave = GameSave.createSave(0,tamagotchi, Location.getLocation("kitchen"));
        newSave.getTamagotchi().setAttribute("faim",100);
        newSave.save();

        newSave = GameSave.loadSave(0);

        newSave.delete();

        LangFile file = LangFile.getLangFile();


        DBConnection.closeConnection();

        GameView view = new GameView();
    }
}
