package fr.tama.controller;

import fr.tama.model.*;
import fr.tama.view.GameFrame;
import fr.tama.view.utils.Animation;
import fr.tama.view.utils.AnimationSprite;
import fr.tama.view.GameView;
import fr.tama.view.components.TamaRadioButton;
import fr.tama.view.components.TamaSaveCard;

import java.util.Objects;


/**
*   Class ensuring link between Controller and View
*/
public class GameController {
    private final GameView gameView;
    private final static GameInstance INSTANCE = new GameInstance();

    public GameController() {
        this.gameView = new GameView(INSTANCE);
    }

    /**
    *   Method that initializes components event
    */
    public void startGame() {
        //Language initialization before initializating controls
        LangFile file = LangFile.getLangFile();
        this.gameView.setLangFile(file);
        this.gameView.start();
        this.applyListeners();
    }

    /**
     * Apply an ActionListener to components of View
     */
    private void applyListeners()
    {
        //Menu control events
        this.gameView.getGameFrame().getMenuPanel().getButtonPlay().addActionListener(e -> this.gameView.getGameFrame().switchPanel(GameFrame.SAVES));

        this.gameView.getGameFrame().getMenuPanel().getButtonOption().addActionListener(e -> this.gameView.getGameFrame().switchPanel(GameFrame.OPTIONS));

        this.gameView.getGameFrame().getMenuPanel().getButtonQuit().addActionListener(e -> System.exit(0));

        //In-game control events
        this.gameView.getGameFrame().getGamePanel().getMoveLeftButton().addActionListener(e->{
            if(INSTANCE.getLocation().getNext()!=null)INSTANCE.setLocation(INSTANCE.getLocation().getNext());
            this.gameView.getGameFrame().getGamePanel().updatePanel();
        });

        this.gameView.getGameFrame().getGamePanel().getMoveRightButton().addActionListener(e->{
            if(INSTANCE.getLocation().getPrevious()!=null)INSTANCE.setLocation(INSTANCE.getLocation().getPrevious());
            this.gameView.getGameFrame().getGamePanel().updatePanel();
        });

        this.gameView.getGameFrame().getGamePanel().getGameScreen().getTamaBath().addUpdateListener(e->this.gameView.getGameFrame().updatePanel());
        this.gameView.getGameFrame().getGamePanel().getGameScreen().getTamaJump().addUpdateListener(e->this.gameView.getGameFrame().updatePanel());
        this.gameView.getGameFrame().getGamePanel().getActionButton().addActionListener(e->{
            if(INSTANCE.getTamagotchi().getLevel()==Level.EGG)return;
            if(INSTANCE.getTamagotchi().getCurrent()==Current.ASLEEP && !Objects.equals(INSTANCE.getLocation().getAction(), "tiredness"))return;
            switch(INSTANCE.getLocation().getAction()){
                case "hunger":
                    if(INSTANCE.getTamagotchi().getAttribute("hungry").getCoolDown()==0){
                        this.gameView.getGameFrame().getGamePanel().getGameScreen().getBonbon().start();
                        INSTANCE.getTamagotchi().eat();
                    }
                    break;
                case "tiredness":
                    INSTANCE.getTamagotchi().sleep();
                    if(INSTANCE.getTamagotchi().getCurrent() == Current.ASLEEP)this.gameView.getMusic().initSleepMusic();
                    else this.gameView.getMusic().initGameMusic();
                    break;
                case "cleanliness":
                    if(INSTANCE.getTamagotchi().getAttribute("cleanliness").getCoolDown()==0){
                        this.gameView.getGameFrame().getGamePanel().getGameScreen().getTamaBath().start();
                        INSTANCE.getTamagotchi().wash();
                    }
                    break;
                case "toilet":
                    if(INSTANCE.getTamagotchi().getAttribute("toilet").getCoolDown()==0){
                        this.gameView.getGameFrame().getGamePanel().getGameScreen().getTamaPoop().start();
                        INSTANCE.getTamagotchi().toilet();
                    }
                    break;
                case "happiness":
                    if(INSTANCE.getTamagotchi().getAttribute("happiness").getCoolDown()==0){
                        this.gameView.getGameFrame().getGamePanel().getGameScreen().getTamaJump().start();
                        INSTANCE.getTamagotchi().play();
                    }
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
            LangFile.saveLang();
            this.gameView.getGameFrame().switchPanel(GameFrame.MENU);
            this.gameView.getMusic().saveVolume();
            this.gameView.getMusic().saveMute();
        });

        this.gameView.getGameFrame().getOptionsPanel().getCancelButton().addActionListener(e -> {
            if(!DBConfig.getString("lang").equals(LangFile.lang))
                LangFile.switchLang(DBConfig.getString("lang"));
            this.gameView.getGameFrame().switchPanel(GameFrame.MENU);
            String name = LangFile.getName(LangFile.lang);

            for(TamaRadioButton t : this.gameView.getGameFrame().getOptionsPanel().getRadioButtons())
                t.setSelected(t.getText().equals(name));

            this.gameView.getGameFrame().getOptionsPanel().getMusicSwitch().setSelected(DBConfig.getBoolean("mute"));
            this.gameView.getGameFrame().getOptionsPanel().getMusicSlider().setValue(this.gameView.getGameFrame().getOptionsPanel().getMusicSwitch().isSelected() ? this.gameView.getGameFrame().getOptionsPanel().getMusicSlider().getMinimum() : DBConfig.getInt("volume"));
        
            this.gameView.getMusic().setVolume(DBConfig.getInt("volume"));

            if(this.gameView.getGameFrame().getOptionsPanel().getMusicSwitch().isSelected())
                this.gameView.getMusic().stop();
            else if(this.gameView.getMusic().isStopped())
                this.gameView.getMusic().start();
        });

        for(TamaRadioButton t : this.gameView.getGameFrame().getOptionsPanel().getRadioButtons())
        {
            t.addItemListener(e -> {
                LangFile.switchLang(t.getText());
                this.gameView.updatePanel();
            });
        }

        //Save control events
        this.gameView.getGameFrame().getSavesPanel().getReturnButton().addActionListener(e -> this.gameView.getGameFrame().switchPanel(GameFrame.MENU));

        this.gameView.getGameFrame().getGamePanel().getReturnButton().addActionListener(e -> {
            this.gameView.getMusic().initGameMusic();
            this.gameView.getGameFrame().switchPanel(GameFrame.MENU);
            INSTANCE.alive=false;
        });

        this.gameView.getGameFrame().getSavesPanel().getSaveCardPanel1().addCreateSaveListener(e->{
            GameSave save=GameSave.createSave(0,getCorrespondingTama(this.gameView.getGameFrame().getSavesPanel().getSaveCardPanel1()),Location.getDefaultLocation());
            INSTANCE.setInstance(save,this.gameView.getGameFrame());
            INSTANCE.start();
            this.gameView.getGameFrame().switchPanel(GameFrame.GAME);
        });

        this.gameView.getGameFrame().getSavesPanel().getSaveCardPanel2().addCreateSaveListener(e->{
            GameSave save=GameSave.createSave(1,getCorrespondingTama(this.gameView.getGameFrame().getSavesPanel().getSaveCardPanel2()),Location.getDefaultLocation());
            INSTANCE.setInstance(save,this.gameView.getGameFrame());
            INSTANCE.start();
            this.gameView.getGameFrame().switchPanel(GameFrame.GAME);
        });

        this.gameView.getGameFrame().getSavesPanel().getSaveCardPanel3().addCreateSaveListener(e->{
            GameSave save=GameSave.createSave(2,getCorrespondingTama(this.gameView.getGameFrame().getSavesPanel().getSaveCardPanel3()),Location.getDefaultLocation());
            INSTANCE.setInstance(save,this.gameView.getGameFrame());
            INSTANCE.start();
            this.gameView.getGameFrame().switchPanel(GameFrame.GAME);
        });

        this.gameView.getGameFrame().getSavesPanel().getSaveCardPanel1().addLoadSaveListener(e->{
            GameSave save = GameSave.loadSave(0);
            INSTANCE.setInstance(save,this.gameView.getGameFrame());
            INSTANCE.start();
            if(INSTANCE.getTamagotchi().getCurrent() == Current.ASLEEP){
                this.gameView.getMusic().initSleepMusic();
            }
            this.gameView.getGameFrame().switchPanel(GameFrame.GAME);
        });

        this.gameView.getGameFrame().getSavesPanel().getSaveCardPanel2().addLoadSaveListener(e->{
            GameSave save = GameSave.loadSave(1);
            INSTANCE.setInstance(save,this.gameView.getGameFrame());
            INSTANCE.start();
            if(INSTANCE.getTamagotchi().getCurrent() == Current.ASLEEP){
                this.gameView.getMusic().initSleepMusic();
            }
            this.gameView.getGameFrame().switchPanel(GameFrame.GAME);
        });

        this.gameView.getGameFrame().getSavesPanel().getSaveCardPanel3().addLoadSaveListener(e->{
            GameSave save = GameSave.loadSave(2);
            INSTANCE.setInstance(save,this.gameView.getGameFrame());
            INSTANCE.start();
            if(INSTANCE.getTamagotchi().getCurrent() == Current.ASLEEP){
                this.gameView.getMusic().initSleepMusic();
            }
            this.gameView.getGameFrame().switchPanel(GameFrame.GAME);
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

        Animation[] anims = this.gameView.getGameFrame().getGamePanel().getGameScreen().getAnimations();
        for(Animation anim : anims){
            anim.addUpdateListener(e->{
                if(this.gameView.getGameFrame().getCurrentPanel()==3)this.gameView.updatePanel();
            });
        }

        //Death menu control events
        this.gameView.getGameFrame().getDeathPanel().getReturnButton().addActionListener(e -> this.gameView.getGameFrame().switchPanel(GameFrame.MENU));

        this.gameView.getGameFrame().getDeathPanel().getButtonQuit().addActionListener(e -> System.exit(0));
    }

    private Tamagotchi getCorrespondingTama(TamaSaveCard panel){
        Tamagotchi tamagotchi;
        switch (panel.getTamagotchi()){
            case "Chien":
                tamagotchi = new Chien(Status.GOOD,Status.GOOD,Current.AWAKE,Math.random()>0.5,
                        panel.getName(),Level.EGG,panel.getDifficulty());
                break;
            case "Chat":
                tamagotchi = new Chat(Status.GOOD,Status.GOOD,Current.AWAKE,Math.random()>0.5,
                        panel.getName(),Level.EGG,panel.getDifficulty());
                break;
            case "Lapin":
                tamagotchi = new Lapin(Status.GOOD,Status.GOOD,Current.AWAKE,Math.random()>0.5,
                        panel.getName(),Level.EGG,panel.getDifficulty());
                break;
            default:
                tamagotchi = new Robot(Status.GOOD,Status.GOOD,Current.AWAKE,Math.random()>0.5,
                        panel.getName(),Level.EGG,panel.getDifficulty());
                break;
        }
        return tamagotchi;
    }
}
