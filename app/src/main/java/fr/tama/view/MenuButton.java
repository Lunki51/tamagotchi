package fr.tama.view;

import fr.tama.model.Constants;

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
        this.setBackground(Constants.PURPLE);
        this.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createLineBorder(Constants.BLUE, 10), BorderFactory.createLineBorder(Constants.PURPLE, 10)));
        this.setCursor(new Cursor(Cursor.HAND_CURSOR));
    }
}
