package fr.tama.view;

import fr.tama.controller.LangFile;

import javax.swing.*;
import java.awt.*;

public class MenuPanel extends JPanel {

    private final JPanel buttonPanel;
    private final MenuButton buttonPlay;
    private final MenuButton buttonOption;
    private final MenuButton buttonQuit;

    private final LangFile lang;

    public MenuPanel() {

        super(new FlowLayout(FlowLayout.CENTER,4,4));

        this.lang = LangFile.getLangFile();
        this.buttonPanel = new JPanel();
        this.buttonPanel.setLayout(new GridLayout(3,1, 12, 12));
        this.add(buttonPanel);

        this.buttonPlay = new MenuButton(lang.getString("menu.play"));
        this.buttonPanel.add(this.buttonPlay);

        this.buttonOption = new MenuButton(lang.getString("menu.options"));
        this.buttonPanel.add(this.buttonOption);

        this.buttonQuit = new MenuButton(lang.getString("menu.quit"));
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
