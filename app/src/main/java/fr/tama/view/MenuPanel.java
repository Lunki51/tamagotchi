package fr.tama.view;

import fr.tama.controller.LangFile;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.util.Objects;

public class MenuPanel extends JPanel {

    private final JPanel buttonPanel;
    private final MenuButton buttonPlay;
    private final MenuButton buttonOption;
    private final MenuButton buttonQuit;

    private final LangFile lang;

    public MenuPanel() {

        super(new BorderLayout());
        super.setBackground(new Color(0,56,92));
        this.lang = LangFile.getLangFile();
        ImageIcon background = new ImageIcon(Objects.requireNonNull(this.getClass().getClassLoader().getResource("sprites/background/background_menu.png")));
        JLabel backgroundPanel = new JLabel(background);
        this.add(backgroundPanel, BorderLayout.CENTER);
        this.buttonPanel = new JPanel();
        this.buttonPanel.setBackground(new Color(0,56,92));
        this.buttonPanel.setLayout(new GridLayout(3,3, 12, 12));
        this.add(buttonPanel, BorderLayout.SOUTH);

        this.buttonPlay = new MenuButton(lang.getString("menu.play"));
        this.buttonPanel.add(new JLabel());
        this.buttonPanel.add(this.buttonPlay);
        this.buttonPanel.add(new JLabel());
        this.buttonPanel.add(new JLabel());
        this.buttonOption = new MenuButton(lang.getString("menu.options"));
        this.buttonPanel.add(this.buttonOption);
        this.buttonPanel.add(new JLabel());
        this.buttonPanel.add(new JLabel());
        this.buttonQuit = new MenuButton(lang.getString("menu.quit"));
        this.buttonPanel.add(this.buttonQuit);
        this.buttonPanel.add(new JLabel());

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
