package fr.tama.view.components;

import javax.swing.*;
import java.awt.*;

public class TamaRadioButton extends JRadioButton {

    private final Color buttonSelectedColor;
    private final Color buttonColor;

    public TamaRadioButton(String text, Color buttonColor, Color buttonSelectedColor, Color fontColor, Color bgColor) {
        super(text);
        setForeground(fontColor);
        setBackground(bgColor);
        this.buttonColor=buttonColor;
        this.buttonSelectedColor=buttonSelectedColor;
    }

    @Override
    public void setSize(int width, int height) {
        super.setSize(this.getFontMetrics(this.getFont()).stringWidth(getText()) + height + 10, height);
    }

    @Override
    public void setSize(Dimension d) {
        this.setSize(this.getFontMetrics(this.getFont()).stringWidth(getText()) + d.height + 10, d.height);
    }

    @Override
    protected void paintComponent(Graphics g) {
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
