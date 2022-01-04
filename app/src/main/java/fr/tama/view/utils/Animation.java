package fr.tama.view.utils;

import java.awt.event.ActionListener;
import java.util.ArrayList;

/**
 * Abstract class that represent an animation in the game
 */
public abstract class Animation implements Runnable {

    long delta;
    protected int nbLoop;
    protected Thread myThread;
    protected final ArrayList<ActionListener> listeners = new ArrayList<>();

    /**
     * Create a new animation
     * @param delta the delta time between the beginning and the end of the animation
     * @param nbLoop the number of loop for the animation
     */
    public Animation(long delta, int nbLoop){
        this.delta = delta;
        this.nbLoop=nbLoop;
    }

    /**
     * Start the animation
     */
    abstract public void start();

    /**
     * Add a listener to the animation
     * @param l the listener to add
     */
    public void addUpdateListener(ActionListener l){
        this.listeners.add(l);
    }

}
