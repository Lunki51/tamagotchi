package fr.tama.view;

import javax.swing.*;
import java.awt.*;

public class GamePanel extends JPanel {

    public GamePanel() {
        this.setLayout(new GridLayout(2,1));
        this.setBackground(Color.PINK);
        JPanel top = new JPanel();
        JPanel bottom = new JPanel();

        top.setLayout(new GridLayout(1,1));
        top.add(new BackgroundPanel());
        bottom.setLayout(new BorderLayout());
        JPanel menu = new JPanel();
        menu.add(new JButton("Quit"));
        menu.add(new JButton("Previous"));
        menu.add(new JButton("Feed"));
        menu.add(new JButton("Next")) ;

        JPanel center = new JPanel();

        bottom.add(menu,BorderLayout.SOUTH);
        bottom.add(center,BorderLayout.CENTER);
        this.add(top);
        this.add(bottom);
    }
}
