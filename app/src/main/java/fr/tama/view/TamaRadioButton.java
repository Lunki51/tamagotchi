package fr.tama.view;

import javax.swing.*;
import java.awt.*;

public class TamaRadioButton extends JRadioButton {

    private final Color buttonFont;
    private final Color button;
    private final JLabel label;

    public TamaRadioButton(String text,Color buttonFont,Color button) {
        super(text);
        this.button=button;
        this.buttonFont=buttonFont;
        this.label = new JLabel("          "+text);
        this.add(label);
    }

    @Override
    public void setFont(Font font) {
        super.setFont(font);
        if(this.label!=null)this.label.setFont(font);
    }

    @Override
    protected void paintComponent(Graphics g) {
        g.setColor(this.buttonFont);
        g.setFont(this.getFont());
        Point start = new Point((int)(this.getWidth()*this.getAlignmentX())- (int)(((this.getHeight()*2))*this.getAlignmentX()),0);
        g.fillRect(start.x ,start.y,this.getHeight(),this.getHeight());

        //this.label.setBounds(start.x+this.getHeight(),0,this.getHeight(),this.getHeight());

        g.setColor(this.button);
        if(this.isSelected()){
            g.fillRect(start.x+this.getHeight()/6,this.getHeight()/6,this.getHeight() - 2*(this.getHeight()/6),
                    this.getHeight() - 2*(this.getHeight()/6));
        }
    }

}
