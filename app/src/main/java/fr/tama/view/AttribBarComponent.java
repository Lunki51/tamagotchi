package fr.tama.view;

import javax.swing.*;
import java.awt.*;

public class AttribBarComponent extends JComponent {

    private int minValue;
    private int maxValue;
    private int currentValue;
    private Color color;

    AttribBarComponent(Color color){
        this.minValue=0;
        this.maxValue=0;
        this.currentValue=0;
        this.color=color;
    }

    public void updateDisplay(int minValue,int maxValue,int currentValue){
        this.minValue=minValue;
        this.maxValue=maxValue;
        this.currentValue=currentValue;
        repaint();
    }
    public void updateDisplay(int maxValue,int currentValue){
        this.updateDisplay(0,maxValue,currentValue);
    }

    public void setColor(Color color) {
        this.color = color;
    }

    @Override
    protected void paintComponent(Graphics g) {
        g.setColor(this.color);
        g.fillRect(0,0,(int)(this.getWidth()*(((float)currentValue)/((float)maxValue-Math.abs((float)minValue)+1.0))),this.getHeight());
        g.setColor(Color.BLACK);
        g.drawRect(0,0,this.getWidth()-1,this.getHeight()-1);
    }
}
