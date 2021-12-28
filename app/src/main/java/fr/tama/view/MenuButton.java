package fr.tama.view;

import fr.tama.model.Constants;

import javax.swing.*;
import java.awt.*;

public class MenuButton extends JButton {
    public MenuButton(String text) {
        super(text);
        this.setHorizontalTextPosition(JButton.CENTER);
        this.setVerticalTextPosition(JButton.CENTER);
        this.setFont(new Font(Font.SANS_SERIF,  Font.BOLD, 20));
        this.setForeground(Color.white);
        this.setBackground(Constants.PURPLE);
        this.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createLineBorder(Constants.BLUE, 10), BorderFactory.createLineBorder(Constants.PURPLE, 10)));
        this.setCursor(new Cursor(Cursor.HAND_CURSOR));
        this.setBorderPainted(false);
        this.setFocusPainted(false);
    }

    @Override
    protected void paintComponent(Graphics g) 
    {
        if(getModel().isPressed() || getModel().isRollover())
        {
            g.setColor(Constants.DARK_PURPLE);
            g.fillRect(0, 0, getWidth(), getHeight());
            g.setColor(getForeground());

            FontMetrics fm = g.getFontMetrics();
            int x = (getWidth() - fm.stringWidth(getText())) / 2;
            int y = (getHeight() - fm.getHeight()) / 2 + fm.getAscent();
            g.drawString(getText(), x, y);
        }
        else
            super.paintComponent(g);
    }
}
