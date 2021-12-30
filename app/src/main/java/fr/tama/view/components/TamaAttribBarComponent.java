package fr.tama.view.components;

import javax.swing.*;
import java.awt.*;

public class TamaAttribBarComponent extends JComponent {

    private int minValue;
    private int maxValue;
    private int currentValue;
    private Color color;

    public TamaAttribBarComponent(Color color){
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
        g.fillRoundRect(0,0,(int)(this.getWidth()*(((float)currentValue)/((float)maxValue-Math.abs((float)minValue)+1.0))),this.getHeight(), 10, 10);
        if(this.currentValue!=this.maxValue &&(this.getWidth()*(((float)currentValue)/((float)maxValue-Math.abs((float)minValue)+1.0)))>12&&(this.getWidth()*(((float)currentValue)/((float)maxValue-Math.abs((float)minValue)+1.0)))<(this.getWidth()*(((float)maxValue)/((float)maxValue-Math.abs((float)minValue)+1.0)))-12){
            g.fillRect((int)(this.getWidth()*(((float)currentValue)/((float)maxValue-Math.abs((float)minValue)+1.0)))-10,0,10,this.getHeight());
        }


        g.setColor(Color.BLACK);
        g.drawRoundRect(0,0,this.getWidth()-1,this.getHeight()-1, 10, 10);
    }
}
