package fr.tama.view;

import fr.tama.controller.GameInstance;

import javax.swing.*;
import java.awt.*;

public class Animation implements Runnable{

    ImageIcon[] frames;
    int currentFrame;
    long delta;

    public Animation(String[] files,long delta){
        frames = new ImageIcon[files.length];
        this.delta = delta;
        for(int i=0;i<files.length;i++){
            frames[i] = new ImageIcon(this.getClass().getClassLoader().getResource(files[i]));
        }
        new Thread(this).start();
    }

    @Override
    public void run() {
        while (true){
            try{
                currentFrame++;
                if(currentFrame==frames.length)currentFrame=0;
                Thread.sleep(this.delta);
            }catch (InterruptedException e){
                e.printStackTrace();
            }
        }

    }

    public ImageIcon getCurrentImage(){
        return this.frames[currentFrame];
    }
}
