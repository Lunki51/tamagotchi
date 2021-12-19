package fr.tama.view;

import javax.sound.sampled.*;
import java.io.File;
import java.io.IOException;

public class Music {
    private Clip clip;
    private FloatControl fc;

    public Music(){
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
        this.clip.loop(Clip.LOOP_CONTINUOUSLY);
        this.fc = (FloatControl) clip.getControl(FloatControl.Type.MASTER_GAIN);
        this.setVolume(-14);

    }

    public float getVolume() {
        return fc.getValue();
    }

    public void setVolume(float volume) {
        fc.setValue(volume);
    }

    /**
     * Allows to start or stop the music
     */
    public void mute(){
        if(this.clip.isActive())this.clip.stop();
        else this.clip.start();
    }
}
