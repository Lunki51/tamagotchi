package fr.tama.view;

import fr.tama.controller.GameInstance;
import fr.tama.controller.LangFile;
import fr.tama.view.utils.Music;
import fr.tama.view.utils.Updatable;

/**
 * Main View class
 */
public class GameView implements Updatable {

    private final Music music;
    private GameFrame gameFrame;
    private LangFile lang;
    private final GameInstance gameInstance;

    public GameView(GameInstance gameInstance){
        this.gameInstance = gameInstance;
        this.music = new Music();
    }

    /**
     * Init the View and create
     */
    public void start(){

        if(lang == null) {
            throw new RuntimeException("Impossible de lancer la vue car l'objet de gestion des langues n'est pas défini");
        }
        this.gameFrame = new GameFrame(gameInstance);
    }

    /**
     * Return the Music instance which is handling music
     * @return the Music instance
     */
    public Music getMusic(){
        return this.music;
    }

    /**
     * Return current GameFrame
     * @return current GameFrame
     */
    public GameFrame getGameFrame() {
        return this.gameFrame;
    }

    /**
     * Link GameView and a specified LangFile instance
     * @param file LangFile instance
     */
    public void setLangFile(LangFile file) {
        this.lang = file;
    }

    /**
     * Call updatePanel() method from GameFrame (c.f. GameFrame implementation)
     */
    @Override
    public void updatePanel() {
        if(gameFrame != null)
            this.gameFrame.updatePanel();
    }
}
