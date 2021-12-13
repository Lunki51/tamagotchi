package fr.tama.view;


import fr.tama.controller.GameInstance;
import fr.tama.controller.LangFile;

/**
 * Main View class
 * with all JFrames
 */
public class GameView {

    private GameFrame gameFrame;
    private LangFile lang;
    private final GameInstance gameInstance;

    public GameView(GameInstance gameInstance){
        this.gameInstance = gameInstance;
    }

    /**
     * Allows to init the JFrames
     */
    public void start(){

        if(lang == null) {
            throw new RuntimeException("Impossible de lancer la vue car l'objet de gestiond es langue n'est pas set");
        }
        this.gameFrame = new GameFrame(lang.getString("title"),gameInstance);
    }

    public GameFrame getGameFrame() {
        return this.gameFrame;
    }

    public void setLangFile(LangFile file) {
        this.lang = file;
    }
}
