package fr.tama.view.utils;

import java.awt.event.ActionListener;
import java.util.ArrayList;

public abstract class Animation implements Runnable {

    long delta;
    protected int nbLoop;
    protected Thread myThread;
    protected final ArrayList<ActionListener> listeners = new ArrayList<>();

    public Animation(long delta, int nbLoop){
        this.delta = delta;
        this.nbLoop=nbLoop;
    }

    abstract public void start();

    public void addUpdateListener(ActionListener l){
        this.listeners.add(l);
    }

}
