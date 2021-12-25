package fr.tama.controller;

import fr.tama.controller.DBConnection;
import fr.tama.controller.LangFile;
import fr.tama.model.*;
import fr.tama.view.GameView;

import java.util.Objects;

public class GameController {
    private final GameView gameView;
    private final static GameInstance INSTANCE = new GameInstance();

    public GameController() {
        this.gameView = new GameView(INSTANCE);
    }

    public void startGame() {
        LangFile file = LangFile.getLangFile();
        LangFile.setLang("fr");

        this.gameView.setLangFile(file);
        this.gameView.start();
        
        this.gameView.getGameFrame().getMenuPanel().getButtonPlay().addActionListener(e -> this.gameView.getGameFrame().switchPanel(2));
        this.gameView.getGameFrame().getSavesPanel().getTmpButton().addActionListener(e -> {
            GameSave save = GameSave.loadSave(0);
            if(save==null)save=GameSave.createSave(0,new Chien(Status.GOOD,Status.GOOD,Current.AWAKE,true,"Default",Level.CHILD),Location.getDefaultLocation());
            INSTANCE.setInstance(save,this.gameView.getGameFrame());
            INSTANCE.start();
            this.gameView.getGameFrame().switchPanel(3);
        });
        this.gameView.getGameFrame().getMenuPanel().getButtonOption().addActionListener(e -> {
            this.gameView.getGameFrame().switchPanel(4);
            this.gameView.getGameFrame().repaint();
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
            if(INSTANCE.getTamagotchi().getLevel()==Level.EGG)return;
            if(INSTANCE.getTamagotchi().getCurrent()==Current.ASLEEP && !Objects.equals(INSTANCE.getLocation().getAction(), "tiredness"))return;
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

            INSTANCE.getSave().save();
            this.gameView.getGameFrame().getGamePanel().updatePanel();
        });

        this.gameView.getGameFrame().getOptionsPanel().getMusicSwitch().addActionListener(e -> this.gameView.getMusic().mute());

        this.gameView.getGameFrame().getOptionsPanel().getMusicSlider().addChangeListener(e -> this.gameView.getMusic().setVolume(this.gameView.getGameFrame().getOptionsPanel().getMusicSlider().getValue()));
        this.gameView.getGameFrame().getOptionsPanel().getReturnButton().addActionListener(e -> {
            this.gameView.getGameFrame().switchPanel(1);
            this.gameView.getGameFrame().getMenuPanel().repaint();
        });

        this.gameView.getGameFrame().getOptionsPanel().getLangFRBtn().addItemListener(e -> LangFile.switchLang());

    }
}
