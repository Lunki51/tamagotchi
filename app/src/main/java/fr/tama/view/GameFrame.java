package fr.tama.view;

import fr.tama.controller.GameInstance;
import fr.tama.controller.LangFile;

import javax.swing.*;
import java.awt.*;

/**
 * The Main Window / Java Frame
 */
public class GameFrame extends JFrame {
    private final MenuPanel menuPanel;
    private final GamePanel gamePanel;
    private final OptionsPanel optionsPanel;
    private JPanel next;
    private final GameInstance gameInstance;

    public GameFrame(GameInstance gameInstance) throws HeadlessException {
        super(LangFile.getLangFile().getString("title"));
        this.setSize(1280,720);
        this.gameInstance = gameInstance;
        this.menuPanel = new MenuPanel();
        this.optionsPanel = new OptionsPanel();
        this.gamePanel = new GamePanel(gameInstance);
        this.getContentPane().add(this.menuPanel);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLocationRelativeTo(null);
        this.setVisible(true);
    }

    public MenuPanel getMenuPanel() {
        return this.menuPanel;
    }

    public GamePanel getGamePanel() {
        return this.gamePanel;
    }

    public OptionsPanel getOptionsPanel() {
        return optionsPanel;
    }

    public void switchPanel(int panel) {
        this.getContentPane().removeAll();
        this.getContentPane().invalidate();
        switch(panel) {
            case 1:
                this.getContentPane().add(this.menuPanel);
                this.next = this.gamePanel;
                break;
            case 2:
                this.getContentPane().add(this.gamePanel);
                this.gamePanel.updatePanel();
                break;
            case 3:
                this.getContentPane().add(this.optionsPanel);
                break;
        }
        this.getContentPane().revalidate();
    }


}
