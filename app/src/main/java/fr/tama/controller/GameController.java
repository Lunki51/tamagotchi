package fr.tama.controller;

import fr.tama.controller.DBConnection;
import fr.tama.controller.LangFile;
import fr.tama.model.*;
import fr.tama.view.GameView;

import java.util.Objects;

/**
*   Class ensuring View's initialization
*/
public class GameController {
    private final GameView gameView;
    private final static GameInstance INSTANCE = new GameInstance();

    public GameController() {
        this.gameView = new GameView(INSTANCE);
    }

    /**
    *   Method that initializes controls event
    */
    public void startGame() {
        //Language initialization before initializating controls
        LangFile file = LangFile.getLangFile();
        LangFile.setLang("fr"); //TODO: Requête SQL pour obtenir la langue dernièrement utilisée SINON langue par défaut qui devrait être directement en dur dans le code de la BDD
        this.gameView.setLangFile(file);
        this.gameView.start();
        
        //Menu control events
        this.gameView.getGameFrame().getMenuPanel().getButtonPlay().addActionListener(e -> this.gameView.getGameFrame().switchPanel(2));
        this.gameView.getGameFrame().getSavesPanel().getTmpButton().addActionListener(e -> {    //TODO: Drogue dure, ah non c'est un event codé à la dur
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

        //In-game control events
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
                    if(INSTANCE.getTamagotchi().getCurrent() == Current.ASLEEP)this.gameView.getMusic().initSleepMusic();
                    else this.gameView.getMusic().initGameMusic();
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

        //Settings control events
        this.gameView.getGameFrame().getOptionsPanel().getMusicSwitch().addActionListener(e -> {
            if(this.gameView.getGameFrame().getOptionsPanel().getMusicSwitch().isSelected())
            {
                this.gameView.getGameFrame().getOptionsPanel().getMusicSlider().setValue(-40); //Beurk
                this.gameView.getMusic().mute();
            }
            else
                this.gameView.getGameFrame().getOptionsPanel().getMusicSlider().setValue((int)this.gameView.getMusic().getVolume());

        });

        this.gameView.getGameFrame().getOptionsPanel().getMusicSlider().addChangeListener(e -> {
            if(this.gameView.getGameFrame().getOptionsPanel().getMusicSlider().getValue() == -40) //Beurk
            {
                this.gameView.getGameFrame().getOptionsPanel().getMusicSwitch().setSelected(true);
                this.gameView.getMusic().mute();
            }   
            else
            {
                this.gameView.getMusic().setVolume(this.gameView.getGameFrame().getOptionsPanel().getMusicSlider().getValue());
                if(this.gameView.getGameFrame().getOptionsPanel().getMusicSwitch().isSelected())
                    this.gameView.getGameFrame().getOptionsPanel().getMusicSwitch().setSelected(false);
            }
        });
        this.gameView.getGameFrame().getOptionsPanel().getReturnButton().addActionListener(e -> {
            this.gameView.getGameFrame().switchPanel(1);
            this.gameView.getGameFrame().getMenuPanel().repaint();
        });

        this.gameView.getGameFrame().getOptionsPanel().getLangFRBtn().addItemListener(e -> LangFile.switchLang());

    }
}
