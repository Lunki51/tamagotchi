package fr.tama.view;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class BackgroundPanel extends JPanel {

    BufferedImage bck;


    public BackgroundPanel() {
        try{
            bck = ImageIO.read(this.getClass().getResourceAsStream("/bck.jpg"));
        }catch (IOException e){
            e.printStackTrace();
        }
        this.setVisible(true);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(bck,0,0,this.getWidth(),this.getHeight(),null);
    }
}
