package fr.tama.view;

import fr.tama.model.Constants;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.geom.Rectangle2D;

public class TamaCheckBox extends JCheckBox{

    private String text;
    private Color font;
    private Color buttonFont;
    private Color button;
    private JLabel label;

    public TamaCheckBox(String text,Color font,Color buttonFont,Color button) {
        super(text);
        this.text = text;
        this.font=font;
        this.button=button;

        this.buttonFont=buttonFont;
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
        g.fillRect(0,0,this.getWidth(),this.getHeight());

        /*
        g.setColor(Color.BLACK);
        g.setFont(this.getFont());
        Rectangle2D strbds = this.getFont().getStringBounds(this.text,g.getFontMetrics().getFontRenderContext());
        g.setFont(this.getFont());
        g.drawString(this.text,this.getHeight(),this.getHeight()/2 + (int)(strbds.getHeight()/2));
        g.drawString("HAMLLOOC?OAV",0,this.getHeight());

         */


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
