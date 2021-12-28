package fr.tama.view;

import javax.swing.*;
import java.awt.*;

public class TamaCheckBox extends JCheckBox{

    private final Color font;
    private final Color buttonFont;
    private final Color button;
    private final JLabel label;

    public TamaCheckBox(String text,Color font,Color buttonFont,Color button) {
        this.font=font;
        this.button=button;
        this.buttonFont=buttonFont;
        this.setBackground(font);
        this.label = new JLabel(text);
        this.setLayout(null);
        this.label.setBounds(this.getHeight(),0,this.getHeight(),this.getHeight());
        this.add(label);
    }

    @Override
    public void setFont(Font font) {
        super.setFont(font);
        if(this.label!=null)this.label.setFont(font);
    }

    @Override
    protected void paintComponent(Graphics g) {
        g.setColor(this.font);
        g.fillRect(0,0,g.getClipBounds().width,g.getClipBounds().height);

        g.setColor(this.buttonFont);
        g.setFont(this.getFont());
        Point start = new Point((int)(this.getWidth()*this.getAlignmentX())- (int)(((this.getHeight()*2)+10)*this.getAlignmentX()),0);
        g.fillRect(start.x ,start.y,this.getHeight(),this.getHeight());

        this.label.setBounds(start.x+this.getHeight()+10,0,this.getHeight(),this.getHeight());

        g.setColor(this.button);
        if(this.isSelected()){
            g.fillRect(start.x+this.getHeight()/6,this.getHeight()/6,this.getHeight() - 2*(this.getHeight()/6),
                    this.getHeight() - 2*(this.getHeight()/6));
        }
    }
}
