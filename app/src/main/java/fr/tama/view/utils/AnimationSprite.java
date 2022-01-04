package fr.tama.view.utils;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class AnimationSprite extends Animation{

    ImageIcon[] frames;
    int currentFrame;


    public AnimationSprite(String[] files, long delta, int nbLoop){
        super(delta, nbLoop);
        frames = new ImageIcon[files.length];
        for(int i=0;i<files.length;i++){
            frames[i] = new ImageIcon(this.getClass().getClassLoader().getResource(files[i]));
        }
    }

    public void start(){
        new Thread(this).start();
    }

    @Override
    public void run() {
        while(nbLoop!=0){
            int i=0;
            while (i!=frames.length){
                try{
                    Thread.sleep(this.delta);
                    currentFrame++;
                    if(currentFrame==frames.length)currentFrame=0;
                    for(ActionListener l : this.listeners){
                        l.actionPerformed(new ActionEvent(this,0,"update"));
                    }
                    i++;

                }catch (InterruptedException e){
                    e.printStackTrace();
                }
            }
            if(nbLoop>0)nbLoop--;
        }


    }



    public ImageIcon getCurrentImage(){
        return this.frames[currentFrame];
    }
}
