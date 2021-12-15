package fr.tama.controller;

import fr.tama.model.Location;
import fr.tama.model.Tamagotchi;
import fr.tama.view.GameFrame;
import fr.tama.view.GamePanel;

import javax.swing.*;
import java.util.Date;

public class GameInstance implements Runnable{

    private static final int INTERVAL = 500;

    Tamagotchi tamagotchi;
    Location location;
    Date started;
    boolean alive = true;
    Thread thisThread=null;
    Date lastSeen;
    GamePanel gamePanel;


    void setInstance(Tamagotchi tama, Date lastSeen, Location currentLoc,GameFrame gamePanel){
        if(this.thisThread!= null)this.thisThread.interrupt();
        this.tamagotchi = tama;
        this.location = currentLoc;
        this.thisThread = new Thread(this);
        this.lastSeen = lastSeen;
        this.gamePanel = gamePanel.getGamePanel();
    }

    public Tamagotchi getTamagotchi() {
        return tamagotchi;
    }

    public Location getLocation() {
        return location;
    }

    void start(){
        this.thisThread.start();
    }

    void updateSince(Date date){
        Date now = new Date();
        long elapsed = now.getTime() - date.getTime();
        long nbUpdate = elapsed / INTERVAL;
        System.out.println(nbUpdate);
        for(int i=0;i<nbUpdate;i++){
            tamagotchi.update();
        }
        started = now;

    }

    @Override
    public void run() {
        updateSince(this.lastSeen);
        while(alive){
            try{
                this.tamagotchi.update();
                this.gamePanel.updatePanel();
                this.gamePanel.repaint();
                Thread.sleep(INTERVAL);
            }catch (InterruptedException e){
                e.printStackTrace();
            }
        }
    }

    public void setLocation(Location location) {
        this.location = location;
    }
}
