package fr.tama.view.components;

import javax.swing.*;
import java.awt.*;
import java.awt.geom.Rectangle2D;

/**
 * A custom check box
 */
public class TamaCheckBox extends JCheckBox{

    private final Color buttonFontColor;
    private final Color buttonColor;
    private String text;

    /**
     * Customized JCheckBox
     * @param text Label
     * @param buttonBack Button background Color
     * @param buttonFore Text color
     * @param font Font
     */
    public TamaCheckBox(String text,Color buttonBack,Color buttonFore,Font font) {
        this.buttonColor=buttonFore;
        this.buttonFontColor=buttonBack;
        this.text = text;
        this.setFont(font);
        Rectangle2D strBounds = this.getFont().getStringBounds(this.text,this.getFontMetrics(this.getFont()).getFontRenderContext());
        this.setPreferredSize(new Dimension((int)(strBounds.getWidth()+strBounds.getHeight())+20,(int)strBounds.getHeight()));
    }
    
    @Override
    public void setText(String text) {
        this.text = text;
    }

    @Override
    protected void paintComponent(Graphics g) {

        g.setColor(this.getBackground());
        g.fillRect(0,0,g.getClipBounds().width,g.getClipBounds().height);

        g.setColor(this.buttonFontColor);
        int tWidth = this.getFontMetrics(this.getFont()).stringWidth(this.text);
        int tHeight = (int)this.getFontMetrics(this.getFont()).getStringBounds(this.text,g).getHeight();
        g.fillRect(tWidth+20 ,this.getHeight()/2 - tHeight/2,tHeight,tHeight);

        g.setColor(Color.WHITE);
        g.setFont(getFont());
        g.drawString(this.text,0,this.getHeight()/2 + tHeight/3);

        //this.label.setBounds(start.x+this.getHeight()+10,0,this.getHeight(),this.getHeight());

        g.setColor(this.buttonColor);
        if(this.isSelected()){
            g.fillRect(tWidth+20+tHeight/6,this.getHeight()/2 - (tHeight)/2+(tHeight)/6,tHeight- (tHeight)/2+(tHeight)/6,
                    tHeight- (tHeight)/2+(tHeight)/6);
        }

    }
}
