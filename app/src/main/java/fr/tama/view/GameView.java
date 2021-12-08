package fr.tama.view;


import fr.tama.controller.LangFile;

/**
 * Main View class
 * with all JFrames
 */
public class GameView {

    private GameFrame gameFrame;
    private LangFile lang;

    public GameView(){
    }

    /**
     * Allows to init the JFrames
     */
    public void start(){

        if(lang == null) {
            throw new RuntimeException("Impossible de lancer la vue car l'objet de gestiond es langue n'est pas set");
        }
        this.gameFrame = new GameFrame(lang.getString("title"));
    }

    public GameFrame getGameFrame() {
        return this.gameFrame;
    }

    public void setLangFile(LangFile file) {
        this.lang = file;
    }
}
