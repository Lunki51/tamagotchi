package fr.tama.view;

import javax.swing.*;
import java.awt.*;

public class GameView extends JFrame {

    CardLayout layout = new CardLayout();

    public GameView() throws HeadlessException {
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        this.setLayout(layout);
        this.setSize(500,500);
        this.add(new GamePanel());
        this.setVisible(true);
    }
}
