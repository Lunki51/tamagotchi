package fr.tama.view;

import fr.tama.controller.LangFile;

import javax.swing.*;

public class OptionsPanel extends JPanel {

    private final JSlider musicSlider;
    private final JButton returnButton;
    private JCheckBox musicSwitch;
    private final LangFile lang;

    public OptionsPanel(){
        this.lang = LangFile.getLangFile();
        this.musicSwitch = new JCheckBox(lang.getString("menu.mute"));
        this.musicSlider = new JSlider(-40,6);
        this.returnButton = new JButton(lang.getString("menu.back"));
        this.add(returnButton);
        this.add(musicSwitch);
        this.add(musicSlider);
    }

    public JCheckBox getMusicSwitch() {
        return musicSwitch;
    }

    public JSlider getMusicSlider(){
        return this.musicSlider;
    }

    public JButton getReturnButton() {
        return returnButton;
    }
}
