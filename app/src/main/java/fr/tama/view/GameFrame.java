package fr.tama.view;

import fr.tama.controller.GameInstance;
import fr.tama.controller.LangFile;

import javax.swing.*;
import java.awt.*;

public class GameFrame extends JFrame {
    private MenuPanel menuPanel;
    private SavesPanel savesPanel;
    private GamePanel gamePanel;
    private OptionsPanel optionsPanel;
    private JPanel next;
    private GameInstance gameInstance;

    public GameFrame(GameInstance gameInstance) throws HeadlessException {
        super(LangFile.getLangFile().getString("title"));
        this.setSize(1280,720);
        this.gameInstance = gameInstance;
        refreshPanels(1);
        this.getContentPane().add(this.menuPanel);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLocationRelativeTo(null);
        this.setVisible(true);
    }

    //panel -> panel to show after update
    public void refreshPanels(int panel){
        this.menuPanel = new MenuPanel();
        this.savesPanel = new SavesPanel();
        this.optionsPanel = new OptionsPanel(this.optionsPanel);
        this.gamePanel = new GamePanel(gameInstance);
        switchPanel(panel);
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

    public SavesPanel getSavesPanel() {
        return savesPanel;
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
                this.getContentPane().add(this.savesPanel);
                break;
            case 3:
                this.getContentPane().add(this.gamePanel);
                this.gamePanel.updatePanel();
                break;
            case 4:
                this.getContentPane().add(this.optionsPanel);
                break;
        }
        this.getContentPane().revalidate();
    }


}
