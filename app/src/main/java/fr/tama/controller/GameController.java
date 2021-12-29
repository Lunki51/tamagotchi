package fr.tama.controller;

import fr.tama.model.*;
import fr.tama.view.GameView;
import fr.tama.view.SaveCardPanel;

import java.util.Enumeration;
import java.util.Objects;

import javax.swing.AbstractButton;
import javax.swing.JRadioButton;

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
        this.gameView.setLangFile(file);
        this.gameView.start();
        this.applyListeners();
    }

    public void applyListeners()
    {
        //Menu control events
        this.gameView.getGameFrame().getMenuPanel().getButtonPlay().addActionListener(e -> this.gameView.getGameFrame().switchPanel(2));

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
                this.gameView.getGameFrame().getOptionsPanel().getMusicSlider().setValue(this.gameView.getGameFrame().getOptionsPanel().getMusicSlider().getMinimum());
                this.gameView.getMusic().stop();
            }
            else
                this.gameView.getGameFrame().getOptionsPanel().getMusicSlider().setValue((int)this.gameView.getMusic().getVolume());

        });

        this.gameView.getGameFrame().getOptionsPanel().getMusicSlider().addChangeListener(e -> {
            if(this.gameView.getGameFrame().getOptionsPanel().getMusicSlider().getValue() == this.gameView.getGameFrame().getOptionsPanel().getMusicSlider().getMinimum())
            {
                this.gameView.getGameFrame().getOptionsPanel().getMusicSwitch().setSelected(true);
                this.gameView.getMusic().stop();
            }   
            else
            {
                this.gameView.getMusic().setVolume(this.gameView.getGameFrame().getOptionsPanel().getMusicSlider().getValue());
                this.gameView.getMusic().start();
                if(this.gameView.getGameFrame().getOptionsPanel().getMusicSwitch().isSelected())
                    this.gameView.getGameFrame().getOptionsPanel().getMusicSwitch().setSelected(false);
            }
        });

        this.gameView.getGameFrame().getOptionsPanel().getSaveButton().addActionListener(e -> {
            this.gameView.getGameFrame().switchPanel(1);
            this.gameView.getGameFrame().getMenuPanel().repaint();
            this.gameView.getMusic().saveVolume();
            this.gameView.getMusic().saveMute();
            LangFile.saveLang();
        });

        this.gameView.getGameFrame().getOptionsPanel().getCancelButton().addActionListener(e -> {
            boolean b = DBConfig.getBoolean("mute");
            this.gameView.getGameFrame().getOptionsPanel().getMusicSwitch().setSelected(b);
            this.gameView.getGameFrame().getOptionsPanel().getMusicSlider().setValue(b ? this.gameView.getGameFrame().getOptionsPanel().getMusicSlider().getMinimum() : DBConfig.getInt("volume"));
        
            if(DBConfig.getString("lang").equals(LangFile.lang))
            {
                this.gameView.getGameFrame().switchPanel(1);
                this.gameView.getGameFrame().getMenuPanel().repaint();
            }
            else
            {
                LangFile.switchLang(DBConfig.getString("lang"));
                this.gameView.getGameFrame().refreshPanels(1);
                this.applyListeners();
            }

            this.gameView.getMusic().setVolume(DBConfig.getInt("volume"));

            if(DBConfig.getBoolean("mute"))
                this.gameView.getMusic().stop();
            else if(this.gameView.getMusic().isStopped())
                this.gameView.getMusic().start();
        });

        this.gameView.getGameFrame().getSavesPanel().getReturnButton().addActionListener(e -> {
            this.gameView.getGameFrame().switchPanel(1);
            this.gameView.getGameFrame().getMenuPanel().repaint();
        });

        this.gameView.getGameFrame().getGamePanel().getReturnButton().addActionListener(e -> {
            this.gameView.getGameFrame().switchPanel(1);
            INSTANCE.alive=false;
            this.gameView.getGameFrame().getMenuPanel().repaint();
        });

        this.gameView.getGameFrame().getSavesPanel().getSaveCardPanel1().addCreateSaveListener(e->{
            GameSave save=GameSave.createSave(0,getCorrespondingTama(this.gameView.getGameFrame().getSavesPanel().getSaveCardPanel1()),Location.getDefaultLocation());
            INSTANCE.setInstance(save,this.gameView.getGameFrame());
            INSTANCE.start();
            this.gameView.getGameFrame().switchPanel(3);
        });

        this.gameView.getGameFrame().getSavesPanel().getSaveCardPanel2().addCreateSaveListener(e->{
            GameSave save=GameSave.createSave(1,getCorrespondingTama(this.gameView.getGameFrame().getSavesPanel().getSaveCardPanel2()),Location.getDefaultLocation());
            INSTANCE.setInstance(save,this.gameView.getGameFrame());
            INSTANCE.start();
            this.gameView.getGameFrame().switchPanel(3);
        });

        this.gameView.getGameFrame().getSavesPanel().getSaveCardPanel3().addCreateSaveListener(e->{
            GameSave save=GameSave.createSave(2,getCorrespondingTama(this.gameView.getGameFrame().getSavesPanel().getSaveCardPanel3()),Location.getDefaultLocation());
            INSTANCE.setInstance(save,this.gameView.getGameFrame());
            INSTANCE.start();
            this.gameView.getGameFrame().switchPanel(3);
        });

        this.gameView.getGameFrame().getSavesPanel().getSaveCardPanel1().addLoadSaveListener(e->{
            GameSave save = GameSave.loadSave(0);
            INSTANCE.setInstance(save,this.gameView.getGameFrame());
            INSTANCE.start();
            this.gameView.getGameFrame().switchPanel(3);
        });

        this.gameView.getGameFrame().getSavesPanel().getSaveCardPanel2().addLoadSaveListener(e->{
            GameSave save = GameSave.loadSave(1);
            INSTANCE.setInstance(save,this.gameView.getGameFrame());
            INSTANCE.start();
            this.gameView.getGameFrame().switchPanel(3);
        });

        this.gameView.getGameFrame().getSavesPanel().getSaveCardPanel3().addLoadSaveListener(e->{
            GameSave save = GameSave.loadSave(2);
            INSTANCE.setInstance(save,this.gameView.getGameFrame());
            INSTANCE.start();
            this.gameView.getGameFrame().switchPanel(3);
        });

        this.gameView.getGameFrame().getSavesPanel().getSaveCardPanel1().addDeleteSaveListener(e->{
            GameSave save = GameSave.loadSave(0);
            save.delete();
        });

        this.gameView.getGameFrame().getSavesPanel().getSaveCardPanel2().addDeleteSaveListener(e->{
            GameSave save = GameSave.loadSave(1);
            save.delete();
        });

        this.gameView.getGameFrame().getSavesPanel().getSaveCardPanel3().addDeleteSaveListener(e->{
            GameSave save = GameSave.loadSave(2);
            save.delete();
        });

        Enumeration<AbstractButton> buttons = this.gameView.getGameFrame().getOptionsPanel().getRadioButtons();
        while(buttons.hasMoreElements())
        {
            JRadioButton b = (JRadioButton)buttons.nextElement();
            b.addItemListener(e -> {
                LangFile.switchLang(b.getText());
                this.gameView.getGameFrame().refreshPanels(4);
                this.applyListeners();
            });
        }
    }

    private Tamagotchi getCorrespondingTama(SaveCardPanel panel){
        Tamagotchi tamagotchi;
        switch (panel.getTamagotchi()){
            case "Chien":
                tamagotchi = new Chien(Status.GOOD,Status.GOOD,Current.AWAKE,Math.random()>0.5,
                        panel.getName(),Level.EGG);
                break;
            case "Chat":
                tamagotchi = new Chat(Status.GOOD,Status.GOOD,Current.AWAKE,Math.random()>0.5,
                        panel.getName(),Level.EGG);
                break;
            case "Lapin":
                tamagotchi = new Lapin(Status.GOOD,Status.GOOD,Current.AWAKE,Math.random()>0.5,
                        panel.getName(),Level.EGG);
                break;
            default:
                tamagotchi = new Robot(Status.GOOD,Status.GOOD,Current.AWAKE,Math.random()>0.5,
                        panel.getName(),Level.EGG);
                break;
        }
        return tamagotchi;
    }
}
