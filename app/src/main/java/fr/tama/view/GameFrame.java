package fr.tama.view;

import javax.swing.*;
import java.awt.*;

public class GameFrame extends JFrame {
    private final MenuPanel menuPanel;
    private final GamePanel gamePanel;
    private JPanel next;

    public GameFrame(String title) throws HeadlessException {
        super(title);
        this.setSize(1280,720);
        this.menuPanel = new MenuPanel();
        this.gamePanel = new GamePanel();
        this.getContentPane().add(this.menuPanel);
        this.next = gamePanel;
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

    public void switchPanel() {
        this.getContentPane().removeAll();
        this.getContentPane().invalidate();
        if (this.next == this.menuPanel) {
            this.getContentPane().add(this.menuPanel);
            this.next = this.gamePanel;
        }
        else {
            this.getContentPane().add(this.gamePanel);
            this.next = this.menuPanel;
        }
        this.getContentPane().revalidate();
    }


}
