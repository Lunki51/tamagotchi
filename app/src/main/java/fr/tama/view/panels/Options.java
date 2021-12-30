package fr.tama.view.panels;

import fr.tama.controller.LangFile;
import fr.tama.controller.DBConfig;
import fr.tama.model.Constants;
import fr.tama.view.components.TamaBigButton;
import fr.tama.view.components.TamaButton;
import fr.tama.view.utils.Updatable;
import fr.tama.view.components.TamaCheckBox;
import fr.tama.view.components.TamaRadioButton;

import javax.swing.*;
import java.awt.*;
import java.util.Enumeration;

public class Options extends JPanel implements Updatable {

    private JSlider musicSlider;
    private JButton cancelButton;
    private JButton saveButton;
    private ButtonGroup langButtons;
    private JLabel title;
    private TamaCheckBox musicSwitch;
    private JLabel musicTitle;
    private JLabel langTitle;

    public Options()
    {
        this(null);
    }

    public Options(Options from){
        super(new GridLayout(4,3));
        super.setBackground(Constants.BLUE);
        initComponents(from);
    }

    public void initComponents(Options from)
    {
        this.musicSwitch = new TamaCheckBox(LangFile.getLangFile().getString("menu.mute"),Color.WHITE,Constants.BLUE,Constants.PURPLE,Constants.DARK_PURPLE);

        this.musicSlider = new JSlider(-40,6);
        musicSwitch.setAlignmentX(CENTER_ALIGNMENT);
        musicSwitch.setBackground(Constants.PURPLE);
        musicSwitch.setFont(Constants.BASIC_FONT);
        musicSlider.setBackground(Constants.BLUE);
    
        this.cancelButton = new TamaBigButton(LangFile.getLangFile().getString("option.cancel"));
        this.saveButton = new TamaBigButton(LangFile.getLangFile().getString("option.save"));



        JPanel titlePanel = new JPanel(new BorderLayout());

        title = new JLabel(LangFile.getLangFile().getString("menu.options"));
        title.setHorizontalAlignment(JLabel.CENTER);
        title.setVerticalTextPosition(JLabel.CENTER);
        title.setFont(Constants.TITLE_FONT);
        title.setForeground(Color.white);
        titlePanel.setBackground(Constants.BLUE);
        titlePanel.add(title,BorderLayout.CENTER);
        JPanel musicPanel = new JPanel(new BorderLayout());
        musicPanel.setBackground(Constants.BLUE);
        musicTitle = new JLabel("Volume : ");
        musicTitle.setHorizontalAlignment(JLabel.CENTER);
        musicTitle.setFont(Constants.BASIC_FONT);
        musicTitle.setForeground(Color.white);
        musicPanel.add(musicTitle, BorderLayout.WEST);
        musicPanel.add(musicSlider,BorderLayout.CENTER);
        musicPanel.add(musicSwitch, BorderLayout.EAST);
        
        //Language panels
        JPanel langPanel = new JPanel(new BorderLayout());
        JPanel radioLangPanel = new JPanel(new FlowLayout());
        langPanel.setBackground(Constants.BLUE);

        langTitle = new JLabel(LangFile.getLangFile().getString("menu.lang"));
        langTitle.setFont(Constants.BASIC_FONT);
        langTitle.setHorizontalAlignment(JLabel.CENTER);
        langTitle.setForeground(Color.WHITE);
        langPanel.add(langTitle, BorderLayout.WEST);

        radioLangPanel.setBackground(Constants.BLUE);
        langPanel.add(radioLangPanel, BorderLayout.CENTER);
        
        //RadioButtons aren't initialized if they were already initialized in a former OptionsPanel instance
        if(from == null) {
            musicSwitch.setSelected(DBConfig.getBoolean("mute"));
            musicSlider.setValue(musicSwitch.isSelected() ? musicSlider.getMinimum() : DBConfig.getInt("volume"));
        } else {
            musicSwitch.setSelected(from.musicSwitch.isSelected());
            musicSlider.setValue(from.musicSlider.getValue());
        }

        langButtons = new ButtonGroup();
        for(String s : LangFile.getLangs().keySet()) {
            TamaRadioButton b = new TamaRadioButton(LangFile.getLangs().get(s).getName(), Constants.PURPLE, Constants.DARK_PURPLE, Color.BLACK, Constants.BLUE);
            if(s.equals(LangFile.lang))
                b.setSelected(true);
            b.setBackground(Constants.BLUE);
            b.setForeground(Color.white);
            langButtons.add(b);
        }

        Enumeration<AbstractButton> e = langButtons.getElements();
        while(e.hasMoreElements()){
            JPanel panel = new JPanel();
            panel.setBackground(Constants.BLUE);

            radioLangPanel.add(e.nextElement());
            if(e.hasMoreElements())radioLangPanel.add(panel);
        }


        this.add(new JLabel()); // 0 0
        this.add(title);        // 0 1
        this.add(new JLabel()); // 0 2
        this.add(new JLabel()); // 1 0;
        this.add(musicPanel);   // 1 1
        this.add(new JLabel()); // 1 2
        this.add(new JLabel()); // 2 0
        this.add(langPanel);    // 2 1
        this.add(new JLabel()); // 2 2
        this.add(cancelButton); // 3 0
        this.add(new JLabel()); // 3 1
        this.add(saveButton);   // 3 2
    }

    public TamaCheckBox getMusicSwitch() {
        return musicSwitch;
    }

    public JSlider getMusicSlider(){
        return this.musicSlider;
    }

    public JButton getCancelButton() {
        return cancelButton;
    }

    public JButton getSaveButton() {
        return saveButton;
    }
    
    public Enumeration<AbstractButton> getRadioButtons()
    {
        return langButtons.getElements();
    }

    @Override
    public void updatePanel() {
        this.musicSwitch.setText(LangFile.getLangFile().getString("menu.mute"));
        this.cancelButton.setText(LangFile.getLangFile().getString("option.cancel"));
        this.saveButton.setText(LangFile.getLangFile().getString("option.save"));
        this.title.setText(LangFile.getLangFile().getString("menu.options"));
        this.musicTitle.setText(LangFile.getLangFile().getString("option.volume") + " : ");
        this.langTitle.setText(LangFile.getLangFile().getString("menu.lang") + " : ");
        this.repaint();
    }
}
