package fr.tama.view.utils;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class Animation implements Runnable{

    ImageIcon[] frames;
    int currentFrame;
    long delta;
    private final boolean doesEnd;
    private final ArrayList<ActionListener> listeners = new ArrayList<>();

    public Animation(String[] files,long delta,boolean doesEnd){
        frames = new ImageIcon[files.length];
        this.delta = delta;
        this.doesEnd = doesEnd;
        for(int i=0;i<files.length;i++){
            frames[i] = new ImageIcon(this.getClass().getClassLoader().getResource(files[i]));
        }
        new Thread(this).start();
    }

    @Override
    public void run() {
        int i=0;
        while (i!=frames.length){
            try{
                Thread.sleep(this.delta);
                currentFrame++;
                if(currentFrame==frames.length)currentFrame=0;
                for(ActionListener l : this.listeners){
                    l.actionPerformed(new ActionEvent(this,0,"update"));
                }
                if(doesEnd){
                    i++;
                }

            }catch (InterruptedException e){
                e.printStackTrace();
            }
        }

    }

    public void addUpdateListener(ActionListener l){
        this.listeners.add(l);
    }

    public ImageIcon getCurrentImage(){
        return this.frames[currentFrame];
    }
}
