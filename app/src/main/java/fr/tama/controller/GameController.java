package fr.tama.controller;

import fr.tama.controller.DBConnection;
import fr.tama.controller.LangFile;
import fr.tama.model.*;
import fr.tama.view.GameView;

public class GameController {
    private GameView gameView;

    public GameController() {
        this.gameView = new GameView();
    }

    public void startGame() {


        Tamagotchi tamagotchi = new Chien(Status.GOOD,Status.VERY_BAD, Current.AWAKE,true,"michel", Level.EGG);
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

        this.gameView.setLangFile(file);

        this.gameView.start();

        DBConnection.closeConnection();
        
        this.gameView.getGameFrame().getMenuPanel().getButtonPlay().addActionListener(e -> {
            this.gameView.getGameFrame().switchPanel();
        });
        this.gameView.getGameFrame().getMenuPanel().getButtonOption().addActionListener(e -> {
            System.out.println("Les Options :\n");
            System.out.println("1) .");
        });
        this.gameView.getGameFrame().getMenuPanel().getButtonQuit().addActionListener(e -> {
            System.exit(0);
        });
    }
}
