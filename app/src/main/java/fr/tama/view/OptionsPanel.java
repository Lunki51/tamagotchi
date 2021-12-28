package fr.tama.view;

import fr.tama.controller.LangFile;
import fr.tama.controller.DBConfig;
import fr.tama.model.Constants;
import javax.swing.*;
import java.awt.*;
import java.util.Enumeration;

public class OptionsPanel extends JPanel {

    private JSlider musicSlider;
    private JButton cancelButton;
    private JButton saveButton;
    private ButtonGroup langButtons;
    private TamaCheckBox musicSwitch;
    private final LangFile lang;
    
    public OptionsPanel()
    {
        this(null);
    }

    public OptionsPanel(OptionsPanel from){
        super(new GridLayout(4,3));
        super.setBackground(Constants.BLUE);
        this.lang = LangFile.getLangFile();
        initComponents(from);
    }

    public void initComponents(OptionsPanel from)
    {
        this.musicSwitch = new TamaCheckBox(lang.getString("menu.mute"),Constants.BLUE,Constants.PURPLE,Constants.DARK_PURPLE);
        this.musicSwitch.setFont(new Font(Font.SANS_SERIF,  Font.BOLD, 20));

        this.musicSlider = new JSlider(-40,6);
        musicSwitch.setAlignmentX(CENTER_ALIGNMENT);
        musicSwitch.setBackground(Constants.PURPLE);
        musicSwitch.setFont(Constants.BASIC_FONT);
        musicSlider.setBackground(Constants.BLUE);
    
        this.cancelButton = new MenuButton(lang.getString("option.cancel"));
        this.saveButton = new MenuButton(lang.getString("option.save"));
        JLabel title = new JLabel(lang.getString("menu.options"));
        title.setHorizontalAlignment(JLabel.CENTER);
        title.setVerticalTextPosition(JLabel.CENTER);
        title.setFont(Constants.TITLE_FONT);
        JPanel musicPanel = new JPanel(new BorderLayout());
        musicPanel.setBackground(Constants.BLUE);
        JLabel musicTitle = new JLabel("Volume");
        musicTitle.setHorizontalAlignment(JLabel.CENTER);
        musicTitle.setFont(Constants.BASIC_FONT);
        musicPanel.add(musicTitle, BorderLayout.NORTH);
        JPanel innerMusic = new JPanel(new GridLayout(2,1));
       
        innerMusic.add(musicSlider);
        innerMusic.add(musicSwitch);
        innerMusic.setBackground(Constants.BLUE);
        musicPanel.add(innerMusic,BorderLayout.CENTER);
        
        //Language panels
        JPanel langPanel = new JPanel(new BorderLayout());
        JPanel radioLangPanel = new JPanel(new FlowLayout());
        langPanel.setBackground(Constants.BLUE);
        radioLangPanel.setBackground(Constants.BLUE);
        langPanel.add(radioLangPanel, BorderLayout.CENTER);
        
        //RadioButtons aren't initialized if they were already initialized in a former OptionsPanel instance
        if(from == null)
        {
            musicSwitch.setSelected(DBConfig.getBoolean("mute"));
            musicSlider.setValue(musicSwitch.isSelected() ? musicSlider.getMinimum() : DBConfig.getInt("volume"));
        }
        else
        {
            musicSwitch.setSelected(from.musicSwitch.isSelected());
            musicSlider.setValue(from.musicSlider.getValue());
        }

        langButtons = new ButtonGroup();
        for(String s : LangFile.getLangs().keySet())
        {
            TamaRadioButton b = new TamaRadioButton(LangFile.getLangs().get(s).getName(), Constants.PURPLE, Constants.DARK_PURPLE, Color.BLACK, Constants.BLUE);
            if(s.equals(LangFile.lang))
                b.setSelected(true);
            b.setBackground(Constants.BLUE);
            langButtons.add(b);
        }

        Enumeration<AbstractButton> e = langButtons.getElements();
        while(e.hasMoreElements())
            radioLangPanel.add(e.nextElement());

        JLabel langTitle = new JLabel(lang.getString("menu.lang"));
        langTitle.setFont(Constants.BASIC_FONT);
        langTitle.setHorizontalAlignment(JLabel.CENTER);
        langPanel.add(langTitle, BorderLayout.NORTH);

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
}
