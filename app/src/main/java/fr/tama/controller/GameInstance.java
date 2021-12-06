package fr.tama.controller;

import fr.tama.model.Location;
import fr.tama.model.Tamagotchi;

import java.util.Date;

public class GameInstance implements Runnable{

    Tamagotchi tamagotchi;
    Location location;
    Date started;
    boolean alive = true;

    GameInstance(Tamagotchi tama, Date lastSeen, Location currentLoc){
        this.tamagotchi = tama;
        this.location = currentLoc;
        updateSince(lastSeen);
        Thread thread = new Thread(this);
        thread.start();
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
        while(alive){
            Date date = new Date();
            if((date.getTime()-started.getTime())%300==0){
                this.tamagotchi.update();
            }
        }
    }
}
