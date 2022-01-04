package fr.tama.view.components;

import fr.tama.model.Constants;

import javax.swing.*;
import java.awt.*;

public class TamaButton extends JButton {
    /**
     * Customized JButton
     * @param text Text in button
     */
    public TamaButton(String text) {
        super(text);
        this.setHorizontalTextPosition(JButton.CENTER);
        this.setVerticalTextPosition(JButton.CENTER);
        this.setFocusable(false);
        this.setFont(new Font(Font.SANS_SERIF,  Font.BOLD, 20));
        this.setForeground(Color.white);
        this.setBackground(Constants.PURPLE);
        this.setFont(new Font("Arial", Font.BOLD, 25));
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
