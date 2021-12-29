package fr.tama.view;

import fr.tama.controller.GameInstance;
import fr.tama.controller.LangFile;
import fr.tama.view.panels.Game;
import fr.tama.view.panels.Menu;
import fr.tama.view.panels.Options;
import fr.tama.view.panels.Saves;
import fr.tama.view.utils.Updatable;

import javax.swing.*;
import java.awt.*;

public class GameFrame extends JFrame implements Updatable {

    ImageIcon icon = new ImageIcon(this.getClass().getClassLoader().getResource("sprites/tamagotchi/baby_chat.png"));

    private final Menu menu;
    private final Saves savesPanel;
    private final Game gamePanel;
    private Options options;
    private int currentPanel;

    public GameFrame(GameInstance gameInstance) throws HeadlessException {
        super(LangFile.getLangFile().getString("title"));
        this.menu = new Menu();
        this.savesPanel = new Saves();
        this.options = new Options(this.options);
        this.gamePanel = new Game(gameInstance);
        this.currentPanel=1;

        this.setSize(1280,720);
        this.setIconImage(icon.getImage());
        this.getContentPane().add(this.menu);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLocationRelativeTo(null);
        this.setVisible(true);

        switchPanel(this.currentPanel);
    }

    public int getCurrentPanel() {
        return currentPanel;
    }

    public Menu getMenuPanel() {
        return this.menu;
    }

    public Game getGamePanel() {
        return this.gamePanel;
    }

    public Options getOptionsPanel() {
        return options;
    }

    public Saves getSavesPanel() {
        return savesPanel;
    }

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
        }
        this.getContentPane().revalidate();
        this.repaint();
    }


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
        }
        this.repaint();
    }
}
