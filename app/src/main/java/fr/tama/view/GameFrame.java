package fr.tama.view;

import fr.tama.controller.GameInstance;
import fr.tama.controller.LangFile;
import fr.tama.view.panels.*;
import fr.tama.view.panels.Menu;
import fr.tama.view.utils.Updatable;

import javax.swing.*;
import java.awt.*;

public class GameFrame extends JFrame implements Updatable {

    ImageIcon icon = new ImageIcon(this.getClass().getClassLoader().getResource("sprites/tamagotchi/baby_chat.png"));

    private final Menu menu;
    private final Saves savesPanel;
    private final Game gamePanel;
    private final Options options;
    private final Death death;
    private int currentPanel;

    public static final int MENU = 1;
    public static final int SAVES = 2;
    public static final int GAME = 3;
    public static final int OPTIONS = 4;
    public static final int DEATH = 5;

    /**
     * Initializes a JFrame instance which handles multiple JPanels used in-game
     * @param gameInstance
     * @throws HeadlessException
     */
    public GameFrame(GameInstance gameInstance) throws HeadlessException {
        super(LangFile.getLangFile().getString("title"));
        this.menu = new Menu();
        this.savesPanel = new Saves();
        this.options = new Options();
        this.death = new Death();
        this.gamePanel = new Game(gameInstance);
        this.currentPanel=MENU;

        this.setSize(1280,720);
        this.setIconImage(icon.getImage());
        this.getContentPane().add(this.menu);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLocationRelativeTo(null);
        this.setVisible(true);

        switchPanel(this.currentPanel);
    }

    /**
     * Return current panel shown
     * @return index of panel shown <ul><li>1 = Menu</li><li>2 = Saves</li><li>3 = Game</li><li>4 = Options</li><li>5 = Death screen</li></ul>
     */
    public int getCurrentPanel() {
        return currentPanel;
    }

    /**
     * Return the JPanel instance handling menu
     * @return Return the JPanel instance handling menu
     */
    public Menu getMenuPanel() {
        return this.menu;
    }

    /**
     * Return the JPanel instance handling game
     * @return Return the JPanel instance handling game
     */
    public Game getGamePanel() {
        return this.gamePanel;
    }

    /**
     * Return the JPanel instance handling options
     * @return Return the JPanel instance handling options
     */
    public Options getOptionsPanel() {
        return options;
    }

    /**
     * Return the JPanel instance handling saves
     * @return Return the JPanel instance handling saves
     */
    public Saves getSavesPanel() { return savesPanel; }

    public Death getDeathPanel() {
        return death;
    }

    /**
     * Ask GameFrame for show a specified panel
     * @param panel <ul><li>1 = Menu</li><li>2 = Saves</li><li>3 = Game</li><li>4 = Options</li><li>5 = Death screen</li></ul>
     */
    public void switchPanel(int panel) {
        this.currentPanel=panel;
        this.getContentPane().removeAll();
        this.getContentPane().invalidate();
        switch(this.currentPanel) {
            case 1:
                this.getContentPane().add(this.menu);
                this.menu.updatePanel();
                break;
            case 2:
                this.getContentPane().add(this.savesPanel);
                this.savesPanel.updatePanel();
                break;
            case 3:
                this.getContentPane().add(this.gamePanel);
                this.gamePanel.updatePanel();
                break;
            case 4:
                this.getContentPane().add(this.options);
                this.options.updatePanel();
                break;
            case 5:
                this.getContentPane().add(this.death);
                this.death.updatePanel();
                break;
        }
        this.getContentPane().revalidate();
        this.repaint();
    }

    /**
     * Call updatePanel() method from current panel shown
     */
    @Override
    public void updatePanel() {
        switch(this.currentPanel) {
            case 1:
                this.menu.updatePanel();
                break;
            case 2:
                this.savesPanel.updatePanel();
                break;
            case 3:
                this.gamePanel.updatePanel();
                break;
            case 4:
                this.options.updatePanel();
                break;
            case 5:
                this.death.updatePanel();
                break;
        }
        this.repaint();
    }
}
