package fr.tama.view;

import fr.tama.controller.LangFile;
import fr.tama.model.Constants;

import javax.swing.*;
import java.awt.*;

public class OptionsPanel extends JPanel {

    private final JSlider musicSlider;
    private final JButton returnButton;
    private JCheckBox musicSwitch;
    private final LangFile lang;
    private JRadioButton langFRBtn;

    public OptionsPanel(){
        super(new GridLayout(4,3));
        super.setBackground(Constants.BLUE);
        this.lang = LangFile.getLangFile();
        this.musicSwitch = new JCheckBox(lang.getString("menu.mute"));
        this.musicSwitch.setFont(new Font(Font.SANS_SERIF,  Font.BOLD, 20));


        this.musicSlider = new JSlider(-40,6);
        musicSwitch.setBackground(Constants.BLUE);
        musicSwitch.setFont(Constants.BASIC_FONT);
        musicSlider.setBackground(Constants.BLUE);
        this.returnButton = new MenuButton("<-     " + lang.getString("menu.back"));
        JLabel title = new JLabel(lang.getString("menu.options"));
        title.setHorizontalAlignment(JLabel.CENTER);
        title.setVerticalTextPosition(JLabel.CENTER);
        title.setFont(Constants.TITLE_FONT);
        JLabel placeholder2 = new JLabel();
        JLabel placeholder3 = new JLabel();
        JLabel placeholder4 = new JLabel();
        JLabel placeholder5 = new JLabel();
        JLabel placeholder6 = new JLabel();
        JLabel placeholder7 = new JLabel();
        JLabel placeholder8 = new JLabel();
        JPanel musicPanel = new JPanel(new BorderLayout());
        musicPanel.setBackground(Constants.BLUE);
        JLabel musicTitle = new JLabel("Volume");
        musicTitle.setHorizontalAlignment(JLabel.CENTER);
        musicTitle.setFont(Constants.BASIC_FONT);
        musicPanel.add(musicTitle, BorderLayout.NORTH);
        musicPanel.add(musicSwitch, BorderLayout.CENTER);
        musicPanel.add(musicSlider, BorderLayout.EAST);

        JPanel langPanel = new JPanel(new BorderLayout());
        langPanel.setBackground(Constants.BLUE);
        ButtonGroup langSwitch = new ButtonGroup();
        this.langFRBtn = new JRadioButton(lang.getString("menu.langFR"));
        JRadioButton englishRadio = new JRadioButton(lang.getString("menu.langEN"));
        langFRBtn.setBackground(Constants.BLUE);
        englishRadio.setBackground(Constants.BLUE);
        langSwitch.add(langFRBtn);
        langSwitch.add(englishRadio);
        JLabel langTitle = new JLabel(lang.getString("menu.lang"));
        langTitle.setFont(Constants.BASIC_FONT);
        langTitle.setHorizontalAlignment(JLabel.CENTER);
        langPanel.add(langTitle, BorderLayout.NORTH);
        langPanel.add(langFRBtn, BorderLayout.CENTER);
        langPanel.add(englishRadio, BorderLayout.EAST);


        this.add(returnButton);
        this.add(title);
        this.add(placeholder2);
        this.add(placeholder3);
        this.add(musicPanel);
        this.add(placeholder5);
        this.add(placeholder6);
        this.add(langPanel);
        this.add(placeholder7);
        this.add(placeholder8);
        this.add(placeholder4);

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

    public JRadioButton getLangFRBtn() {
        return this.langFRBtn;
    }
}
