package fr.tama.view;

import javax.swing.JButton;
import javax.swing.ImageIcon;
import javax.swing.BorderFactory;
import java.awt.Color;

public class MenuButton extends JButton {
    public MenuButton(String text) {
        super(text);
        this.setHorizontalTextPosition(JButton.CENTER);
        this.setVerticalTextPosition(JButton.CENTER);
        this.setBorderPainted(true);
        this.setFocusPainted(false);
        this.setContentAreaFilled(false);
        this.setIcon(new ImageIcon("data/button.png"));
        this.setBorder(BorderFactory.createLineBorder(Color.blue));
        this.setBounds(800,800,800,80);
    }
}
