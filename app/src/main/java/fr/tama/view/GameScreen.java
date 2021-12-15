package fr.tama.view;

import fr.tama.controller.GameInstance;
import fr.tama.model.Chat;
import fr.tama.model.Chien;
import fr.tama.model.Lapin;
import fr.tama.model.Robot;

import javax.swing.*;
import java.awt.*;
import java.util.Objects;

public class GameScreen extends JPanel {

    ImageIcon bathroom = new ImageIcon(Objects.requireNonNull(this.getClass().getClassLoader().getResource("salle_de_be.png")));
    ImageIcon living = new ImageIcon(Objects.requireNonNull(this.getClass().getClassLoader().getResource("living_room.png")));
    ImageIcon bedroom = new ImageIcon(Objects.requireNonNull(this.getClass().getClassLoader().getResource("dodo.png")));

    private final GameInstance gameInstance;

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
        }else if(this.gameInstance.getLocation().getName().equals("bedroom")){
            g.drawImage(bedroom.getImage(),0,0,this.getWidth(),this.getHeight(),null);
        }
        String fileName = "";
        switch(this.gameInstance.getTamagotchi().getLevel()){
            case EGG:
                fileName+="egg";
                break;
            case BLOB:
                fileName+="blob";
                break;
            case HEAD:
                fileName+="head";
                break;
            case CHILD:
                fileName+="child";
                break;
            case ADULT:
                fileName+="adult";
                break;
        }
        fileName+="_";
        if(this.gameInstance.getTamagotchi() instanceof Chien){
            fileName+="chien";
        }else if(this.gameInstance.getTamagotchi() instanceof Chat){
            fileName+="chat";
        }else if(this.gameInstance.getTamagotchi() instanceof Robot){
            fileName+="robot";
        }else if(this.gameInstance.getTamagotchi() instanceof Lapin){
            fileName+="lapin";
        }
        fileName+=".png";
        ImageIcon icon = new ImageIcon(Objects.requireNonNull(this.getClass().getClassLoader().getResource(fileName)));
        g.drawImage(icon.getImage(),this.getHeight()/3,this.getWidth()/3,this.getHeight()/2,this.getWidth()/2,null);

    }
}
