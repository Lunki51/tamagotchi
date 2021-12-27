package fr.tama.view;

import fr.tama.model.Constants;

import javax.swing.*;
import java.awt.*;
import java.util.Objects;

public class SaveButton extends JButton {

    private JLabel egg;

    public SaveButton(String text){
        super(text);
        setBackground(Constants.PURPLE);
        this.egg = new JLabel();
        this.egg.setIcon(new ImageIcon(Objects.requireNonNull(this.getClass().getClassLoader().getResource("sprites/tamagotchi/egg_lapin.png"))));
        this.add(egg);
        this.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createLineBorder(Constants.BLUE, 0), BorderFactory.createLineBorder(Constants.BLUE, 20)));
        this.setCursor(new Cursor(Cursor.HAND_CURSOR));
    }
}
