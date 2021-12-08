package fr.tama.controller;

import javax.swing.*;

public class MainController {
    public static void main() {
        SwingUtilities.invokeLater(()-> {
        GameController controller = new GameController();
        controller.startGame();
        });
    }
}
