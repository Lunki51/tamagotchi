package fr.tama.view;

import javax.swing.*;
import java.awt.*;

public class MenuButton extends JButton {
    public MenuButton(String text) {
        super(text);
        this.setHorizontalTextPosition(JButton.CENTER);
        this.setVerticalTextPosition(JButton.CENTER);
        this.setFocusable(false);
        this.setFont(new Font(Font.SANS_SERIF,  Font.BOLD, 20));
        this.setForeground(Color.white);
        this.setBackground(new Color(130,3,120));
        //this.setBorderPainted(true);
        //this.setFocusPainted(false);
        //this.setContentAreaFilled(false);
        //this.setIcon(new ImageIcon("data/button.png"));
        this.setBorder(BorderFactory.createLineBorder(Color.blue));
        this.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(new Color(255,255,255), 0),
                BorderFactory.createLineBorder(new Color(130,3,120), 20)));

    }
}
