package fr.tama.view.panels;

import fr.tama.controller.GameInstance;
import fr.tama.model.*;
import fr.tama.model.Robot;
import fr.tama.view.utils.Animation;
import fr.tama.view.utils.AnimationPos;
import fr.tama.view.utils.AnimationSprite;

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
    ImageIcon bonbonSprite = new ImageIcon(Objects.requireNonNull(this.getClass().getClassLoader().getResource("sprites/background/bonbon.png")));
    AnimationSprite bedroomSleep= new AnimationSprite(new String[]{"sprites/background/dodo_anim1.png","sprites/background/dodo_anim2.png"},700,-1);
    AnimationPos tamaBath;
    AnimationPos tamaJump;
    AnimationPos bonbon;
    AnimationPos tamaPoop;

    private final GameInstance gameInstance;

    /**
     * Handle backgrounds and animations
     */
    public GameScreen(GameInstance gameInstance) {
        this.gameInstance = gameInstance;
        bedroomSleep.start();
        tamaBath = new AnimationPos(new float[]{0,0},new float[]{0,0},500,1);
        tamaJump = new AnimationPos(new float[]{0,0},new float[]{0,0},100,3);
        tamaPoop = new AnimationPos(new float[]{0,0},new float[]{0,0},500,1);
        bonbon = new AnimationPos(new float[]{0,0},new float[]{0,0},100,3);

        tamaBath.setMovement(new float[]{-100,-200});
        tamaBath.setChild(tamaJump);
        tamaPoop.setChild(tamaJump);
        tamaJump.setMovement(new float[]{0,-50});
    }

    public Animation[] getAnimations() {
        return new Animation[]{bedroomSleep,tamaBath,tamaJump,tamaPoop,bonbon};
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

            tamaJump.setMovement(new float[]{0,-this.getHeight()/8});
            bonbon.setMovement(new float[]{0,-this.getHeight()/8});

            if(this.gameInstance.getLocation().getName().equals("bathroom")){
                    tamaBath.setInitial(new float[]{this.getWidth()/2 - (((this.getHeight()/2*icon.getIconWidth())/icon.getIconHeight())/2),this.getHeight()/2});
                    tamaBath.setMovement(new float[]{-this.getWidth()/8,-this.getHeight()/3});

                tamaJump.setInitial(tamaBath.getPos());
                g.drawImage(icon.getImage(),(int)tamaJump.getPos()[0],(int)tamaJump.getPos()[1],(this.getHeight()/2*icon.getIconWidth())/icon.getIconHeight(),this.getHeight()/2,null);
            }else if(this.gameInstance.getLocation().getName().equals("toilet")){
                    tamaPoop.setInitial(new float[]{this.getWidth()/2 - (((this.getHeight()/2*icon.getIconWidth())/icon.getIconHeight())/2),this.getHeight()/2});
                    tamaPoop.setMovement(new float[]{this.getWidth()/4,0});

                tamaJump.setInitial(tamaPoop.getPos());
                g.drawImage(icon.getImage(),(int)tamaJump.getPos()[0],(int)tamaJump.getPos()[1],(this.getHeight()/2*icon.getIconWidth())/icon.getIconHeight(),this.getHeight()/2,null);
            }else if(this.gameInstance.getLocation().getName().equals("kitchen")) {
                    bonbon.setInitial(new float[]{this.getWidth() / 2 - ( (((this.getHeight()/2*icon.getIconWidth())/icon.getIconHeight())/2)/4),3* this.getHeight() / 4});
                    bonbon.setMovement(new float[]{0, -this.getHeight() / 32});
                g.drawImage(icon.getImage(), this.getWidth() / 2 - (((this.getHeight() / 2 * icon.getIconWidth()) / icon.getIconHeight()) / 2), this.getHeight() / 2, (this.getHeight() / 2 * icon.getIconWidth()) / icon.getIconHeight(), this.getHeight() / 2, null);
                if(bonbon.isRunning()){
                    g.drawImage(bonbonSprite.getImage(), (int) bonbon.getPos()[0], (int) bonbon.getPos()[1], (this.getHeight() / 2 * bonbonSprite.getIconWidth()) / bonbonSprite.getIconHeight() / 4, this.getHeight() / 2 / 4, null);
                }


            }else if(this.gameInstance.getLocation().getName().equals("living")){
                tamaJump.setInitial(new float[]{this.getWidth()/2 - (((this.getHeight()/2*icon.getIconWidth())/icon.getIconHeight())/2),this.getHeight()/2});
                g.drawImage(icon.getImage(),(int)tamaJump.getPos()[0],(int)tamaJump.getPos()[1],(this.getHeight()/2*icon.getIconWidth())/icon.getIconHeight(),this.getHeight()/2,null);
            }else{
                g.drawImage(icon.getImage(),this.getWidth()/2 - (((this.getHeight()/2*icon.getIconWidth())/icon.getIconHeight())/2),this.getHeight()/2,(this.getHeight()/2*icon.getIconWidth())/icon.getIconHeight(),this.getHeight()/2,null);
            }



            //g.drawImage(icon.getImage(),this.getWidth()/2 - (((this.getHeight()/2*icon.getIconWidth())/icon.getIconHeight())/2),this.getHeight()/2,(this.getHeight()/2*icon.getIconWidth())/icon.getIconHeight(),this.getHeight()/2,null);
        }

    }


    public AnimationPos getTamaPoop() {
        return tamaPoop;
    }

    public AnimationPos getTamaJump() {
        return tamaJump;
    }

    public AnimationPos getTamaBath() {
        return tamaBath;
    }

    public AnimationPos getBonbon(){
        return bonbon;
    }
}
