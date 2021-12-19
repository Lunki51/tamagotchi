package fr.tama.view;


import fr.tama.controller.GameInstance;
import fr.tama.controller.LangFile;

import javax.sound.sampled.*;
import java.io.File;
import java.io.IOException;

/**
 * Main View class
 */
public class GameView {

    private final Music music;
    private GameFrame gameFrame;
    private LangFile lang;
    private final GameInstance gameInstance;

    public GameView(GameInstance gameInstance){
        this.gameInstance = gameInstance;
        this.music = new Music();
    }


    /**
     * Allows to init the GameFrame
     */
    public void start(){

        if(lang == null) {
            throw new RuntimeException("Impossible de lancer la vue car l'objet de gestiond es langue n'est pas set");
        }
        this.gameFrame = new GameFrame(gameInstance);
    }
    public Music getMusic(){
        return this.music;
    }

    public GameFrame getGameFrame() {
        return this.gameFrame;
    }

    public void setLangFile(LangFile file) {
        this.lang = file;
    }
}
