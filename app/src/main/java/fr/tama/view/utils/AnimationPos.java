package fr.tama.view.utils;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.Semaphore;

public class AnimationPos extends Animation{

    float[] initial;
    float[] movement;
    float[] current;
    Thread thisThread;
    private AnimationPos child;
    private CountDownLatch semaphore;

    /**
     * Round-trip animation between two locations
     * @param pos Initial location of the animation
     * @param movement Break location where ends the animation before going back to its initial location
     * @param delta ?? //TODO
     * @param pause Time staying at the middle of the round-trip
     * @param nbLoop Number of loop that animation have to do
     */
    public AnimationPos(float[] pos,float[] movement, long delta, int nbLoop){
        super(delta,nbLoop);
        this.movement = movement;
        this.initial=pos;
        this.child = null;
        this.current = new float[]{0,0};

    }

    /**
     * Start thread
     */
    public void start(){
        this.current = new float[]{0,0};
        semaphore = new CountDownLatch(1);
        thisThread = new Thread(this);
        thisThread.start();
    }

    /**
     * Set where the animation starts
     * @param initial Initial location
     */
    public void setInitial(float[] initial) {
        this.initial = initial;
    }

    /**
     * Set where the animation stops before going back to initial location
     * @param movement Break location
     */
    public void setMovement(float[] movement){
        float ratioW = (float)this.movement[0] / (float)movement[0];
        float ratioH = (float)this.movement[1] / (float)movement[1];
        this.movement = movement;
        if(this.current[0] !=0 && this.current[1]!=0){
            this.current[0] = this.current[0]*ratioW;
            this.current[1] = this.current[1]*ratioH;
        }

    }

    /**
     * Run the animation
     */
    @Override
    public void run() {
        int loop=nbLoop;
        while(loop!=0){
            //POS - POS + MOVEMENT en DELTA
            int count = 0;
            while(count!=delta){
                try{
                    current[0] += (movement[0]/delta)*10;
                    current[1] += (movement[1]/delta)*10;
                    count+=10;
                    for(ActionListener l : this.listeners)
                        l.actionPerformed(new ActionEvent(this,0,"refresh"));
                    Thread.sleep(10);
                }catch (InterruptedException e){
                    e.printStackTrace();
                }
            }

            if(this.child !=null){
                try{
                    this.child.start();
                    this.child.semaphore.await();
                }catch (InterruptedException e){
                    e.printStackTrace();
                }
            }

            while(count!=0){
                try{
                    current[0] -= (movement[0]/delta)*10;
                    current[1] -= (movement[1]/delta)*10;
                    count-=10;
                    for(ActionListener l : this.listeners)
                        l.actionPerformed(new ActionEvent(this,0,"refresh"));
                    Thread.sleep(10);
                }catch (InterruptedException e){
                    e.printStackTrace();
                }
            }
            if(loop>0)loop--;
        }
        this.semaphore.countDown();
        thisThread.interrupt();

    }

    /**
     * Set the child animation of this one
     * @param child the child animation is an animation that will be triggered
     *              at the middle of this animation. This animation will wait for
     *              the second one to finish to resume
     */
    public void setChild(AnimationPos child){
        this.child = child;
    }

    /**
     * Return true if the animation is running false if not
     * @return true if the animation is running false if not
     */
    public boolean isRunning(){
        if(thisThread==null)return false;
        return thisThread.isAlive();
    }


    /**
     * Return the current location of the animation
     * @return current location
     */
    public float[] getPos(){
        return new float[]{this.initial[0]+this.current[0],this.initial[1]+this.current[1]};
    }
}
