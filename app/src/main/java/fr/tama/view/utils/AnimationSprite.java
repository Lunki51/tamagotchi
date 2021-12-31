package fr.tama.view.utils;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class AnimationSprite implements Runnable{

    ImageIcon[] frames;
    int currentFrame;
    long delta;
    private int nbLoop;
    private final ArrayList<ActionListener> listeners = new ArrayList<>();

    public AnimationSprite(String[] files, long delta, int nbLoop){
        frames = new ImageIcon[files.length];
        this.delta = delta;
        this.nbLoop = nbLoop;
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

    public void addUpdateListener(ActionListener l){
        this.listeners.add(l);
    }

    public ImageIcon getCurrentImage(){
        return this.frames[currentFrame];
    }
}
