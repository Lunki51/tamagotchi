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
        this.setLayout(new GridLayout());
        GridBagConstraints c = new GridBagConstraints();
        c.weightx=0.5;
        this.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        //this.label.setBounds(this.getHeight(),0,this.getHeight(),this.getHeight());
        this.label.setForeground(textColor);
        this.add(label,c);
        c.gridx=1;
        this.add(new JLabel(),c);
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

        this.setPreferredSize(new Dimension(this.label.getWidth() + this.getHeight() + 10, this.getHeight()));
        g.setColor(this.backgroundColor);
        g.fillRect(0,0,g.getClipBounds().width,g.getClipBounds().height);

        g.setColor(this.buttonFontColor);
        g.setFont(this.getFont());
        g.fillRect(this.label.getWidth() ,0,this.getHeight(),this.getHeight());

        //this.label.setBounds(start.x+this.getHeight()+10,0,this.getHeight(),this.getHeight());

        g.setColor(this.buttonColor);
        if(this.isSelected()){
            g.fillRect(this.label.getWidth()+this.getHeight()/6,this.getHeight()/6,this.getHeight() - 2*(this.getHeight()/6),
                    this.getHeight() - 2*(this.getHeight()/6));
        }

    }
}
