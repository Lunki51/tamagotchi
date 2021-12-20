package fr.tama.controller;

import fr.tama.model.GameSave;
import fr.tama.model.Location;
import fr.tama.model.Tamagotchi;
import fr.tama.view.GameFrame;
import fr.tama.view.GamePanel;

import javax.swing.*;
import java.util.Date;

public class GameInstance implements Runnable{

    private static final int INTERVAL = 500;

    GameSave save;
    boolean alive = true;
    Thread thisThread=null;
    Date lastSeen;
    GamePanel gamePanel;


    void setInstance(GameSave save, GameFrame gamePanel){
        if(this.thisThread!= null)this.thisThread.interrupt();
        this.save =save;
        this.thisThread = new Thread(this);
        this.lastSeen = save.getLastSeen();
        this.gamePanel = gamePanel.getGamePanel();
    }

    public Tamagotchi getTamagotchi(){
        return save.getTamagotchi();
    }

    public Location getLocation(){
        return  save.getLocation();
    }

    public GameSave getSave() {
        return save;
    }

    void start(){
        this.thisThread.start();
    }

    void updateSince(Date date){
        Date now = new Date();
        long elapsed = now.getTime() - date.getTime();
        long nbUpdate = elapsed / INTERVAL;
        for(int i=0;i<nbUpdate;i++){
            save.getTamagotchi().update();
        }
    }

    @Override
    public void run() {
        updateSince(this.lastSeen);
        while(alive){
            try{
                this.getTamagotchi().update();
                this.gamePanel.updatePanel();
                this.gamePanel.repaint();
                this.save.save();
                //noinspection BusyWait
                Thread.sleep(INTERVAL);
            }catch (InterruptedException e){
                e.printStackTrace();
            }
        }
    }

    public void setLocation(Location location) {
        this.save.setLocation(location);
    }
}
