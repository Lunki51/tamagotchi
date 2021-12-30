package fr.tama.view.panels;

import fr.tama.controller.LangFile;
import fr.tama.model.Constants;
import fr.tama.view.components.TamaBigButton;
import fr.tama.view.components.TamaButton;
import fr.tama.view.utils.Updatable;

import javax.swing.*;
import java.awt.*;
import java.util.Objects;

public class Menu extends JPanel implements Updatable {

    private final TamaButton buttonPlay;
    private final TamaButton buttonOption;
    private final TamaButton buttonQuit;

    public Menu() {

        super(new BorderLayout());
        super.setBackground(Constants.BLUE);
        ImageIcon background = new ImageIcon(Objects.requireNonNull(this.getClass().getClassLoader().getResource("sprites/background/background_menu.png")));
        JLabel backgroundPanel = new JLabel(background);
        this.add(backgroundPanel, BorderLayout.CENTER);
        JPanel buttonPanel = new JPanel();
        buttonPanel.setBackground(Constants.BLUE);
        buttonPanel.setLayout(new GridLayout(3,3, 12, 12));
        this.add(buttonPanel, BorderLayout.SOUTH);

        this.buttonPlay = new TamaBigButton(LangFile.getLangFile().getString("menu.play"));
        buttonPanel.add(new JLabel());
        buttonPanel.add(this.buttonPlay);
        buttonPanel.add(new JLabel());
        buttonPanel.add(new JLabel());
        this.buttonOption = new TamaBigButton(LangFile.getLangFile().getString("menu.options"));
        buttonPanel.add(this.buttonOption);
        buttonPanel.add(new JLabel());
        buttonPanel.add(new JLabel());
        this.buttonQuit = new TamaBigButton(LangFile.getLangFile().getString("menu.quit"));
        buttonPanel.add(this.buttonQuit);
        buttonPanel.add(new JLabel());
    }

    public TamaButton getButtonPlay() {
        return this.buttonPlay;
    }
    public TamaButton getButtonOption() {
        return this.buttonOption;
    }
    public TamaButton getButtonQuit() {
        return this.buttonQuit;
    }

    @Override
    public void updatePanel() {
        this.buttonPlay.setText(LangFile.getLangFile().getString("menu.play"));
        this.buttonOption.setText(LangFile.getLangFile().getString("menu.options"));
        this.buttonQuit.setText(LangFile.getLangFile().getString("menu.quit"));
        this.repaint();
    }
}
