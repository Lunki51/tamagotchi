package fr.tama.view;

import fr.tama.controller.GameInstance;
import fr.tama.model.Chat;
import fr.tama.model.Chien;
import fr.tama.model.Lapin;
import fr.tama.model.Robot;

import javax.swing.*;
import java.awt.*;

public class GameScreen extends JPanel {

    ImageIcon bathroom = new ImageIcon(this.getClass().getClassLoader().getResource("salle_de_be.png"));
    ImageIcon living = new ImageIcon(this.getClass().getClassLoader().getResource("living_room.png"));

    private GameInstance gameInstance;

    public GameScreen(GameInstance gameInstance) {
        this.gameInstance = gameInstance;
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        if(this.gameInstance.getLocation().getName().equals("bathroom")){
            g.drawImage(bathroom.getImage(),0,0,this.getWidth(),this.getHeight(),null);
        }else if(this.gameInstance.getLocation().getName().equals("living")){
            g.drawImage(living.getImage(),0,0,this.getWidth(),this.getHeight(),null);
        }
        if(this.gameInstance.getTamagotchi() instanceof Chien){

        }else if(this.gameInstance.getTamagotchi() instanceof Chat){

        }else if(this.gameInstance.getTamagotchi() instanceof Robot){

        }else if(this.gameInstance.getTamagotchi() instanceof Lapin){

        }

    }
}
