package fr.tama.controller;

import fr.tama.model.GameSave;
import fr.tama.model.Location;
import fr.tama.model.Tamagotchi;
import fr.tama.view.GameFrame;

import java.util.Date;

/**
*   Class handling logical steps to run the game
*/
public class GameInstance implements Runnable{

    private static final int INTERVAL = 1000;
    //private static final int INTERVAL = 300000;

    GameSave save;
    boolean alive = true;
    Thread thisThread=null;
    Date lastSeen;
    GameFrame gameFrame;


    void setInstance(GameSave save, GameFrame gameFrame){
        if(this.thisThread!= null)this.thisThread.interrupt();
        this.save =save;
        this.thisThread = new Thread(this,new Date().toString());
        this.lastSeen = save.getLastSeen();
        this.gameFrame = gameFrame;
    }

    /**
     * Return the tamagotchi stored in save
     * @return Tamagotchi stored in save
     */
    public Tamagotchi getTamagotchi(){
        return save.getTamagotchi();
    }

    /**
     * Return in which room is located the tamagotchi in save
     * @return Location stored in save
     */
    public Location getLocation(){
        return save.getLocation();
    }

    /**
     * Return save itself
     * @return GameSave instance
     */
    public GameSave getSave() {
        return save;
    }

    void start(){
        if(this.thisThread.isAlive())this.thisThread.interrupt();
        this.alive=true;
        this.thisThread.start();
    }

    /**
    *   Calculate time elapsed from last use and update tamagotchi appropriately
    */
    long updateSince(Date date){
        Date now = new Date();
        long elapsed = now.getTime() - date.getTime();
        long nbUpdate = (elapsed ) / INTERVAL;
        if(nbUpdate > 2016) //If the tamagotchi has been abandoned for more than a week => dead
            save.getTamagotchi().getAttribute("health").setValue(0);
        else
            for(long i=0;i<nbUpdate;i++)
                save.getTamagotchi().update();
        save.setLastSeen(date.getTime()+(nbUpdate*INTERVAL));
        save.save();
        return elapsed%INTERVAL;
    }

    @Override
    public void run() {
        long skipped = updateSince(this.lastSeen);

        while(alive){
            try{
                Thread.sleep(INTERVAL-skipped);
                skipped=0;
                this.getTamagotchi().update();
                this.gameFrame.updatePanel();
                this.save.updateLastSeen();
                this.save.save();
                if(this.getTamagotchi().isDead()){
                    this.alive=false;
                    this.gameFrame.switchPanel(GameFrame.DEATH);
                    this.save.delete();
                }
            }catch (InterruptedException e){
                this.alive=false;
                thisThread.interrupt();
            }
        }
    }

    public void setLocation(Location location) {
        this.save.setLocation(location);
    }
}
