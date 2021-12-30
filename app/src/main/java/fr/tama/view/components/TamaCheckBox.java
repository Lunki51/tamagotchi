package fr.tama.view.components;

import javax.swing.*;
import java.awt.*;

public class TamaCheckBox extends JCheckBox{

    private final Color backgroundColor;
    private final Color buttonFontColor;
    private final Color buttonColor;
    private final JLabel label;

    public TamaCheckBox(String text,Color textColor, Color background,Color buttonFont,Color button) {
        this.backgroundColor=background;
        this.buttonColor=button;
        this.buttonFontColor=buttonFont;
        this.setBackground(backgroundColor);
        this.label = new JLabel(text);
        this.setLayout(null);
        this.label.setBounds(this.getHeight(),0,this.getHeight(),this.getHeight());
        this.label.setForeground(textColor);
        this.add(label);
    }

    @Override
    public void setFont(Font font) {
        super.setFont(font);
        if(this.label!=null)this.label.setFont(font);
    }

    @Override
    public void setText(String text) {
        this.label.setText(text);
    }

    @Override
    protected void paintComponent(Graphics g) {
        g.setColor(this.backgroundColor);
        g.fillRect(0,0,g.getClipBounds().width,g.getClipBounds().height);

        g.setColor(this.buttonFontColor);
        g.setFont(this.getFont());
        Point start = new Point((int)(this.getWidth()*this.getAlignmentX())- (int)(((this.getHeight()*2)+10)*this.getAlignmentX()),0);
        g.fillRect(start.x ,start.y,this.getHeight(),this.getHeight());

        this.label.setBounds(start.x+this.getHeight()+10,0,this.getHeight(),this.getHeight());

        g.setColor(this.buttonColor);
        if(this.isSelected()){
            g.fillRect(start.x+this.getHeight()/6,this.getHeight()/6,this.getHeight() - 2*(this.getHeight()/6),
                    this.getHeight() - 2*(this.getHeight()/6));
        }
    }
}
