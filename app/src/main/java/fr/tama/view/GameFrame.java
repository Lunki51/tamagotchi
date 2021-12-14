package fr.tama.view;

import fr.tama.controller.GameInstance;
import fr.tama.controller.LangFile;

import javax.swing.*;
import java.awt.*;

public class GameFrame extends JFrame {
    private final MenuPanel menuPanel;
    private final GamePanel gamePanel;
    private final OptionsPanel optionsPanel;
    private JPanel next;
    private final GameInstance gameInstance;
    private LangFile lang;

    public GameFrame(LangFile lang, GameInstance gameInstance) throws HeadlessException {
        super(lang.getString("title"));
        this.lang = lang;
        this.setSize(1280,720);
        this.gameInstance = gameInstance;
        this.menuPanel = new MenuPanel(lang);
        this.optionsPanel = new OptionsPanel(lang);
        this.gamePanel = new GamePanel(lang,gameInstance);
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
