package fr.tama.view.panels;

import fr.tama.controller.GameInstance;
import fr.tama.model.*;
import fr.tama.model.Robot;
import fr.tama.view.utils.Animation;

import javax.swing.*;
import java.awt.*;
import java.util.Objects;


/**
 * The Graphic Game Screen with background and the sprite of the tamagotchi
 */
public class GameScreen extends JPanel {

    ImageIcon bathroom = new ImageIcon(Objects.requireNonNull(this.getClass().getClassLoader().getResource("sprites/background/salle_de_be.png")));
    ImageIcon kitchen = new ImageIcon(Objects.requireNonNull(this.getClass().getClassLoader().getResource("sprites/background/kitchen.png")));
    ImageIcon toilets = new ImageIcon(Objects.requireNonNull(this.getClass().getClassLoader().getResource("sprites/background/toilets.png")));
    ImageIcon living = new ImageIcon(Objects.requireNonNull(this.getClass().getClassLoader().getResource("sprites/background/living_room.png")));
    ImageIcon bedroom1 = new ImageIcon(Objects.requireNonNull(this.getClass().getClassLoader().getResource("sprites/background/dodo.png")));
    Animation bedroomSleep= new Animation(new String[]{"sprites/background/dodo_anim1.png","sprites/background/dodo_anim2.png"},1000,false);

    private final GameInstance gameInstance;

    public GameScreen(GameInstance gameInstance) {
        this.gameInstance = gameInstance;
    }

    public Animation[] getAnimations() {
        return new Animation[]{bedroomSleep};
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        switch (this.gameInstance.getLocation().getName()) {
            case "bathroom":
                g.drawImage(bathroom.getImage(), 0, 0, this.getWidth(), this.getHeight(), null);
                break;
            case "kitchen":
                g.drawImage(kitchen.getImage(), 0, 0, this.getWidth(), this.getHeight(), null);
                break;
            case "toilet":
                g.drawImage(toilets.getImage(), 0, 0, this.getWidth(), this.getHeight(), null);
                break;
            case "living":
                g.drawImage(living.getImage(), 0, 0, this.getWidth(), this.getHeight(), null);
                break;
            case "bedroom":
                if(this.gameInstance.getTamagotchi().getCurrent()== Current.AWAKE){
                    g.drawImage(bedroom1.getImage(), 0, 0, this.getWidth(), this.getHeight(), null);
                }else{
                    g.drawImage(bedroomSleep.getCurrentImage().getImage(), 0, 0, this.getWidth(), this.getHeight(), null);
                }
                return;
        }
        String fileName = "sprites/tamagotchi/";
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
        if(gameInstance.getTamagotchi().getCurrent()==Current.AWAKE){
            ImageIcon icon = new ImageIcon(Objects.requireNonNull(this.getClass().getClassLoader().getResource(fileName)));
            g.drawImage(icon.getImage(),this.getWidth()/2 - (((this.getHeight()/2*icon.getIconWidth())/icon.getIconHeight())/2),this.getHeight()/2,(this.getHeight()/2*icon.getIconWidth())/icon.getIconHeight(),this.getHeight()/2,null);

        }

    }
}
