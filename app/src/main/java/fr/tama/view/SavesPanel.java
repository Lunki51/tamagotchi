package fr.tama.view;

import fr.tama.controller.LangFile;

import javax.swing.*;
import java.awt.*;

public class SavesPanel extends JPanel {

    private final JButton tmpButton;

    public SavesPanel() {

        super(new GridLayout(1, 1));

        this.tmpButton = new JButton("oui oui, choisir sauvegarde numero 1");
        this.add(tmpButton);
    }

    public JButton getTmpButton() {
        return tmpButton;
    }
}