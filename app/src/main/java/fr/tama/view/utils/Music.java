package fr.tama.view.utils;
import fr.tama.controller.DBConfig;

import javax.sound.sampled.*;
import java.io.IOException;

public class Music {
    private Clip clip;
    private FloatControl fc;
    private boolean isSleepingmusic;

    public Music(boolean b)
    {
        this.isSleepingmusic = b;
        if(isSleepingmusic) initSleepMusic();
        else initGameMusic();
    }

    /**
     * Track main music
     */
    public void initGameMusic() {
        if(!isStopped() && !isSleepingmusic) return;
        try{
            if(isSleepingmusic) {
                this.clip.stop();
            }
        } catch (Exception e){

        } finally {
            isSleepingmusic = false;
        }

        try {
            this.clip = AudioSystem.getClip();
        } catch (Exception e) {
            System.err.println("Impossible d'ouvrir ce fichier, verifier le chemin d'accès");
        }
        AudioInputStream ais = null;
        try {
            ais = AudioSystem.getAudioInputStream( this.getClass().getClassLoader().getResource("music/main_theme.wav") );
        } catch (UnsupportedAudioFileException | IOException e) {
            e.printStackTrace();
        }
        try {
            this.clip.open(ais);
        }catch (LineUnavailableException | IOException e) {
            e.printStackTrace();
        }
        this.fc = (FloatControl) clip.getControl(FloatControl.Type.MASTER_GAIN);

        this.setVolume(DBConfig.getInt("volume"));
        if(!DBConfig.getBoolean("mute"))
            start();
    }

    /**
     * Track sleeping music
     */
    public void initSleepMusic() {
        float oldVolume = getVolume();
        boolean isstopped = isStopped();
        if (!isSleepingmusic) {
            this.clip.stop();
            try {
                this.clip = AudioSystem.getClip();
            } catch (Exception e) {
                System.err.println("Impossible d'ouvrir ce fichier, verifier le chemin d'accès");
            }
            AudioInputStream ais = null;
            try {
                ais = AudioSystem.getAudioInputStream(this.getClass().getClassLoader().getResource("music/main_theme_sleeping.wav"));
            } catch (UnsupportedAudioFileException | IOException e) {
                e.printStackTrace();
            }
            try {
                this.clip.open(ais);
            } catch (LineUnavailableException | IOException e) {
                e.printStackTrace();
            }
            this.fc = (FloatControl) clip.getControl(FloatControl.Type.MASTER_GAIN);
            start();
            isSleepingmusic = true;
            setVolume(oldVolume);
            if(isstopped) stop();

        }
    }

    /**
     * Return music volume
     * @return music volume
     */
    public float getVolume() {
        return fc.getValue();
    }

    /**
     * Set music volume to a new volume value
     * @param volume value to set
     */
    public void setVolume(float volume) {
        fc.setValue(volume);
    }

    /**
     * Save current volume value into the database
     */
    public void saveVolume()
    {
        DBConfig.setInt("volume",(int)fc.getValue());
    }

    /**
     * Save current state of music into the database
     */
    public void saveMute()
    {   
        DBConfig.setBoolean("mute", this.isStopped());
    }

    /**
     * Start playing music
     */
    public void start()  {
        if(!this.clip.isActive())
            this.clip.loop(Clip.LOOP_CONTINUOUSLY);
    }

    /**
     * Stop music
     */
    public void stop(){
        this.clip.stop();
    }

    /**
     * Check if music is stopped or played
     * @return true if stopped else false
     */
    public boolean isStopped()
    {
        return this.clip == null ? true : !this.clip.isActive();
    }
}
