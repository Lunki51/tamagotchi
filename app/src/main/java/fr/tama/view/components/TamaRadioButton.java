package fr.tama.view.components;

import javax.swing.*;
import java.awt.*;

public class TamaRadioButton extends JRadioButton {

    private final Color buttonSelectedColor;
    private final Color buttonColor;

    /**
     * Customized JRadioButton
     * @param text Label
     * @param buttonColor Button color
     * @param buttonSelectedColor Button selected color
     * @param fontColor Font color
     * @param bgColor Background color
     */
    public TamaRadioButton(String text, Color buttonColor, Color buttonSelectedColor, Color fontColor, Color bgColor) {
        super(text);
        setForeground(fontColor);
        setBackground(bgColor);
        this.buttonColor=buttonColor;
        this.buttonSelectedColor=buttonSelectedColor;
    }

    @Override
    protected void paintComponent(Graphics g) {
        this.setPreferredSize(new Dimension(this.getFontMetrics(this.getFont()).stringWidth(getText()) + this.getHeight() + 10, this.getHeight()));
        //Reset surface
        g.setColor(getBackground());
        g.fillRect(0, 0, getWidth(), getHeight()+5);
        
        //Label
        g.setFont(getFont());
        int x = (getWidth() - g.getFontMetrics().stringWidth(getText())) / 2;
        int y = (getHeight() - g.getFontMetrics().getHeight()) / 2 + g.getFontMetrics().getAscent();
        g.setColor(getForeground());
        g.drawString(getText(), x + 15, y);
        
        //Radio Button
        g.setColor(buttonColor);
        g.fillOval(0, 0, getHeight(), getHeight());

        if(this.isSelected())
        {
            g.setColor(buttonSelectedColor);
            g.fillOval(getHeight()/6, getHeight()/6, 2*getHeight()/3, 2*getHeight()/3);
        }

    }
}
