package fr.tama.view.utils;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class AnimationPos implements Runnable{

    float[] initial;
    float[] movement;
    float[] current;
    int currentFrame;
    long delta;
    private int nbLoop;
    Thread thisThread;
    private long pause;
    private final ArrayList<ActionListener> endListeners = new ArrayList<>();
    private final ArrayList<ActionListener> listeners = new ArrayList<>();

    public AnimationPos(float[] pos,float[] movement, long delta,long pause, int nbLoop){
        this.delta = delta;
        this.nbLoop = nbLoop;
        this.movement = movement;
        this.pause=pause;
        this.initial=pos;
        this.current = new float[]{0,0};

    }

    public void start(){
        this.current = new float[]{0,0};
        thisThread = new Thread(this);
        thisThread.start();
    }

    public void setInitial(float[] initial) {
        this.initial = initial;
    }

    public void setMovement(float[] movement){
        float ratioW = (float)this.movement[0] / (float)movement[0];
        float ratioH = (float)this.movement[1] / (float)movement[1];
        this.movement = movement;
        if(this.current[0] !=0 && this.current[1]!=0){
            this.current[0] = this.current[0]*ratioW;
            this.current[1] = this.current[1]*ratioH;
        }

    }

    @Override
    public void run() {
        int loop=nbLoop;
        while(loop!=0){
            //POS - POS + MOVEMENT en DELTA
            int count = 0;
            while(count!=delta){
                try{
                    current[0] += (movement[0]/delta)*100;
                    current[1] += (movement[1]/delta)*100;
                    count+=100;
                    for(ActionListener l : this.listeners){
                        l.actionPerformed(new ActionEvent(this,0,"update"));
                    }
                    Thread.sleep(100);
                }catch (InterruptedException e){
                    e.printStackTrace();
                }
            }
            try{
                for(ActionListener l : this.endListeners){
                    l.actionPerformed(new ActionEvent(this,0,"update"));
                }
                Thread.sleep(pause);
            }catch (InterruptedException e){
                e.printStackTrace();
            }

            while(count!=0){
                try{
                    current[0] -= (movement[0]/delta)*100;
                    current[1] -= (movement[1]/delta)*100;
                    count-=100;
                    for(ActionListener l : this.listeners){
                        l.actionPerformed(new ActionEvent(this,0,"update"));
                    }
                    Thread.sleep(100);
                }catch (InterruptedException e){
                    e.printStackTrace();
                }
            }
            if(loop>0)loop--;
        }
        thisThread.interrupt();

    }

    public void addUpdateListener(ActionListener l){
        this.listeners.add(l);
    }

    public void addMiddleListener(ActionListener l){
        this.endListeners.add(l);
    }

    public float[] getPos(){
        return new float[]{this.initial[0]+this.current[0],this.initial[1]+this.current[1]};
    }
}
