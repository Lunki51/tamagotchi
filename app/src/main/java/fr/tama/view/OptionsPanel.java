package fr.tama.view;

import fr.tama.controller.LangFile;
import fr.tama.controller.DBConfig;
import fr.tama.model.Constants;
import javax.swing.*;
import java.awt.*;
import java.util.Enumeration;
import javax.swing.AbstractButton;
import javax.swing.JRadioButton;

public class OptionsPanel extends JPanel {

    private JSlider musicSlider;
    private JButton returnButton;
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
        this.musicSwitch = new TamaCheckBox(lang.getString("menu.mute"),Constants.BLUE,Constants.PURPLE,Constants.BLACK_PURPLE);
        this.musicSwitch.setFont(new Font(Font.SANS_SERIF,  Font.BOLD, 20));

        this.musicSlider = new JSlider(-40,6);
        musicSwitch.setAlignmentX(CENTER_ALIGNMENT);
        musicSwitch.setBackground(Constants.PURPLE);
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
        JPanel innerMusic = new JPanel(new GridLayout(2,1));
       
        innerMusic.add(musicSlider);
        innerMusic.add(musicSwitch);
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
            musicSlider.setValue(musicSwitch.isSelected() ? -40 : DBConfig.getInt("volume"));

            langButtons = new ButtonGroup();
            for(String s : LangFile.getLangs().keySet())
            {
                JRadioButton b = new JRadioButton(LangFile.getLangs().get(s).getName());
                if(s.equals(LangFile.lang))
                    b.setSelected(true);
                b.setBackground(Constants.BLUE);
                langButtons.add(b);
            }
        }
        else
        {
            musicSwitch.setSelected(from.musicSwitch.isSelected());
            musicSlider.setValue(from.musicSlider.getValue());
            langButtons = from.langButtons;
        }

        Enumeration<AbstractButton> e = langButtons.getElements();
        while(e.hasMoreElements())
            radioLangPanel.add(e.nextElement());

        JLabel langTitle = new JLabel(lang.getString("menu.lang"));
        langTitle.setFont(Constants.BASIC_FONT);
        langTitle.setHorizontalAlignment(JLabel.CENTER);
        langPanel.add(langTitle, BorderLayout.NORTH);

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

    public TamaCheckBox getMusicSwitch() {
        return musicSwitch;
    }

    public JSlider getMusicSlider(){
        return this.musicSlider;
    }

    public JButton getReturnButton() {
        return returnButton;
    }

    public Enumeration<AbstractButton> getRadioButtons()
    {
        return langButtons.getElements();
    }
}
