package fr.tama.view;
import fr.tama.controller.DBConfig;

import javax.sound.sampled.*;
import java.io.IOException;

public class Music {
    private Clip clip;
    private FloatControl fc;
    private boolean isSleepingmusic;

    public Music() {
        initGameMusic();
    }

    public void initGameMusic()
    {

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
        } catch (UnsupportedAudioFileException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            this.clip.open(ais);
        }catch (LineUnavailableException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        this.fc = (FloatControl) clip.getControl(FloatControl.Type.MASTER_GAIN);

        this.setVolume(DBConfig.getInt("volume"));
        if(!DBConfig.getBoolean("mute"))
            start();
    }

    public void initSleepMusic() {
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
            } catch (UnsupportedAudioFileException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
            try {
                this.clip.open(ais);
            } catch (LineUnavailableException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
            this.fc = (FloatControl) clip.getControl(FloatControl.Type.MASTER_GAIN);
            start();
            isSleepingmusic = true;
        }
    }

    public float getVolume() {
        return fc.getValue();
    }

    public void setVolume(float volume) {
        fc.setValue(volume);
    }

    public void saveVolume()
    {
        DBConfig.setInt("volume",(int)fc.getValue());
    }

    public void saveMute()
    {   
        DBConfig.setBoolean("mute", !this.clip.isActive());
    }

    public void start()
    {
        if(!this.clip.isActive())
            this.clip.loop(Clip.LOOP_CONTINUOUSLY);
    }

    public void stop(){
        this.clip.stop();
    }

    public boolean isStopped()
    {
        return !this.clip.isActive();
    }
}
