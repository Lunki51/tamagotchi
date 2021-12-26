package fr.tama.view;

import fr.tama.model.Constants;

import javax.swing.*;
import java.awt.*;
import java.util.Objects;

public class SaveButton extends JPanel {

    private JLabel egg;

    public SaveButton(){
        super(new BorderLayout());
        setBackground(Constants.PURPLE);
        this.egg = new JLabel();
        this.egg.setIcon(new ImageIcon(Objects.requireNonNull(this.getClass().getClassLoader().getResource("sprites/tamagotchi/egg_lapin.png"))));
        this.add(egg, BorderLayout.CENTER);
        this.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createLineBorder(Constants.BLUE, 0), BorderFactory.createLineBorder(Constants.BLUE, 20)));
    }
}
