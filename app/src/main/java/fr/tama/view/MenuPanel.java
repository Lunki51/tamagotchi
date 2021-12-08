package fr.tama.view;

import javax.swing.*;
import java.awt.*;

public class MenuPanel extends JPanel {

    private JPanel buttonPanel;
    private MenuButton buttonPlay;
    private MenuButton buttonOption;
    private MenuButton buttonQuit;

    public MenuPanel() {
        this.setLayout(new BorderLayout());
        this.buttonPanel = new JPanel();
        this.buttonPanel.setLayout(new GridLayout(3,1));
        this.add(buttonPanel, BorderLayout.SOUTH);

        this.buttonPlay = new MenuButton("Jouer");
        this.buttonPanel.add(this.buttonPlay);

        this.buttonOption = new MenuButton("Option");
        this.buttonPanel.add(this.buttonOption);

        this.buttonQuit = new MenuButton("Quitter");
        this.buttonPanel.add(this.buttonQuit);
    }

    public MenuButton getButtonPlay() {
        return this.buttonPlay;
    }
    public MenuButton getButtonOption() {
        return this.buttonOption;
    }
    public MenuButton getButtonQuit() {
        return this.buttonQuit;
    }
}
