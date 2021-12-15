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
        LangFile file = LangFile.getLangFile();
        LangFile.setLang("en");

        this.gameView.setLangFile(file);
        this.gameView.start();
        
        this.gameView.getGameFrame().getMenuPanel().getButtonPlay().addActionListener(e -> {
            GameSave save = GameSave.loadSave(0);
            if(save==null)save=GameSave.createSave(0,new Chat(Status.GOOD,Status.GOOD,Current.AWAKE,true,"Ouai",Level.ADULT
            ),Location.getDefaultLocation());
            INSTANCE.setInstance(save.getTamagotchi(),save.getDate(), save.getLocation(),this.gameView.getGameFrame());
            INSTANCE.start();
            this.gameView.getGameFrame().switchPanel(2);
        });
        this.gameView.getGameFrame().getMenuPanel().getButtonOption().addActionListener(e -> {
            this.gameView.getGameFrame().switchPanel(3);
        });
        this.gameView.getGameFrame().getMenuPanel().getButtonQuit().addActionListener(e -> System.exit(0));

        this.gameView.getGameFrame().getGamePanel().getMoveLeftButton().addActionListener(e->{
            if(INSTANCE.getLocation().getNext()!=null)INSTANCE.setLocation(INSTANCE.getLocation().getNext());
            this.gameView.getGameFrame().getGamePanel().updatePanel();
            this.gameView.getGameFrame().repaint();
        });

        this.gameView.getGameFrame().getGamePanel().getMoveRightButton().addActionListener(e->{
            if(INSTANCE.getLocation().getPrevious()!=null)INSTANCE.setLocation(INSTANCE.getLocation().getPrevious());
            this.gameView.getGameFrame().getGamePanel().updatePanel();
            this.gameView.getGameFrame().repaint();
        });

        this.gameView.getGameFrame().getGamePanel().getActionButton().addActionListener(e->{
            switch(INSTANCE.getLocation().getAction()){
                case "hunger":
                    INSTANCE.getTamagotchi().eat();
                    break;
                case "tiredness":
                    INSTANCE.getTamagotchi().sleep();
                    break;
                case "cleanliness":
                    INSTANCE.getTamagotchi().wash();
                    break;
                case "toilet":
                    INSTANCE.getTamagotchi().toilet();
                    break;
                case "happiness":
                    INSTANCE.getTamagotchi().play();
                    break;
            }
            this.gameView.getGameFrame().getGamePanel().updatePanel();
        });

        this.gameView.getGameFrame().getOptionsPanel().getMusicSwitch().addActionListener(e -> this.gameView.getMusic().mute());

        this.gameView.getGameFrame().getOptionsPanel().getMusicSlider().addChangeListener(e -> this.gameView.getMusic().setVolume(this.gameView.getGameFrame().getOptionsPanel().getMusicSlider().getValue()));
        this.gameView.getGameFrame().getOptionsPanel().getReturnButton().addActionListener(e -> {
            this.gameView.getGameFrame().switchPanel(1);
            this.gameView.getGameFrame().getMenuPanel().repaint();
        });

    }
}
