package fr.tama.controller;

import fr.tama.controller.DBConnection;
import fr.tama.controller.LangFile;
import fr.tama.model.*;
import fr.tama.view.GameView;

public class GameController {
    private final GameView gameView;
    public static GameInstance INSTANCE = new GameInstance();

    public GameController() {
        this.gameView = new GameView(INSTANCE);
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
            GameSave save = GameSave.loadSave(0);
            if(save==null)save=GameSave.createSave(0,new Chien(Status.GOOD,Status.GOOD,Current.AWAKE,true,"Ouai",Level.EGG),Location.getDefaultLocation());
            INSTANCE.setInstance(save.getTamagotchi(),save.getDate(), save.getLocation());
            INSTANCE.start();
            this.gameView.getGameFrame().switchPanel();
        });
        this.gameView.getGameFrame().getMenuPanel().getButtonOption().addActionListener(e -> {
            System.out.println("Les Options :\n");
            System.out.println("1) .");
        });
        this.gameView.getGameFrame().getMenuPanel().getButtonQuit().addActionListener(e -> System.exit(0));

        this.gameView.getGameFrame().getGamePanel().getMoveLeftButton().addActionListener(e->{
            if(INSTANCE.getLocation().getNext()!=null)INSTANCE.setLocation(INSTANCE.getLocation().getNext());
            this.gameView.getGameFrame().getGamePanel().updatePanel();
        });

        this.gameView.getGameFrame().getGamePanel().getMoveRightButton().addActionListener(e->{
            if(INSTANCE.getLocation().getPrevious()!=null)INSTANCE.setLocation(INSTANCE.getLocation().getPrevious());
            this.gameView.getGameFrame().getGamePanel().updatePanel();
        });
    }
}
