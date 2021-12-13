package fr.tama.controller;

import fr.tama.model.Location;
import fr.tama.model.Tamagotchi;

import java.util.Date;

public class GameInstance implements Runnable{

    Tamagotchi tamagotchi;
    Location location;
    Date started;
    long delta;
    Date lastTime;
    boolean alive = true;
    Thread thisThread=null;
    Date lastSeen;

    void setInstance(Tamagotchi tama, Date lastSeen, Location currentLoc){
        if(this.thisThread!= null)this.thisThread.interrupt();
        this.tamagotchi = tama;
        this.location = currentLoc;
        this.delta = 0;
        this.lastTime = new Date();
        this.thisThread = new Thread(this);
        this.lastSeen = lastSeen;
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
        long nbUpdate = elapsed / 300000;
        for(int i=0;i<nbUpdate;i++){
            tamagotchi.update();
        }
        started = now;

    }

    @Override
    public void run() {
        updateSince(this.lastSeen);
        while(alive){
            Date date = new Date();
            if((delta)%300000==0){
                this.tamagotchi.update();
                delta-=300000;
            }
            delta+= date.getTime()-lastTime.getTime();
            lastTime = new Date();
        }
    }

    public void setLocation(Location location) {
        this.location = location;
    }
}
