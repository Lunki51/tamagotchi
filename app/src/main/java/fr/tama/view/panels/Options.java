package fr.tama.view.panels;

import fr.tama.controller.LangFile;
import fr.tama.controller.DBConfig;
import fr.tama.model.Constants;
import fr.tama.view.components.TamaBigButton;
import fr.tama.view.utils.Updatable;
import fr.tama.view.components.TamaCheckBox;
import fr.tama.view.components.TamaRadioButton;

import javax.swing.*;
import java.awt.*;
import java.util.Enumeration;

public class Options extends JPanel implements Updatable {

    private final JSlider musicSlider;
    private final JButton cancelButton;
    private final JButton saveButton;
    private final ButtonGroup langButtons;
    private final JLabel title;
    private final TamaCheckBox musicSwitch;
    private final JLabel musicTitle;
    private final JLabel langTitle;
    private final JPanel aboutPanel;

    /**
     * Initialize Options Panel and its components
     */
    public Options(){
        super(new GridLayout(4,3));
        super.setBackground(Constants.BLUE);
        this.musicSwitch = new TamaCheckBox(LangFile.getLangFile().getString("option.mute"),Constants.PURPLE,Constants.DARK_PURPLE, Constants.BASIC_FONT);

        this.musicSlider = new JSlider(-40,6);
        musicSwitch.setAlignmentX(CENTER_ALIGNMENT);
        musicSwitch.setBackground(Constants.BLUE);
        musicSwitch.setFont(Constants.BASIC_FONT);
        musicSlider.setBackground(Constants.BLUE);

        this.cancelButton = new TamaBigButton(LangFile.getLangFile().getString("option.cancel"));
        this.saveButton = new TamaBigButton(LangFile.getLangFile().getString("option.save"));

        JPanel titlePanel = new JPanel(new BorderLayout());

        title = new JLabel();
        title.setHorizontalAlignment(JLabel.CENTER);
        title.setVerticalTextPosition(JLabel.CENTER);
        title.setFont(Constants.TITLE_FONT);
        title.setForeground(Color.white);
        titlePanel.setBackground(Constants.BLUE);
        titlePanel.add(title,BorderLayout.CENTER);
        JPanel musicPanel = new JPanel(new GridBagLayout());
        musicPanel.setBackground(Constants.BLUE);
        musicTitle = new JLabel();
        musicTitle.setHorizontalAlignment(JLabel.CENTER);
        musicTitle.setFont(Constants.BASIC_FONT);
        musicTitle.setForeground(Color.white);
        GridBagConstraints c2 = new GridBagConstraints();
        c2.weightx=1;
        c2.fill = GridBagConstraints.BOTH;
        c2.weighty=1;
        musicPanel.add(musicTitle,c2);
        c2.gridx=2;
        musicPanel.add(musicSlider,c2);
        c2.gridy=0;
        c2.gridx=1;
        musicPanel.add(musicSwitch,c2);

        //Language panels
        JPanel langPanel = new JPanel(new BorderLayout());
        JPanel radioLangPanel = new JPanel(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();
        c.weightx=1;

        langPanel.setBackground(Constants.BLUE);

        langTitle = new JLabel();
        langTitle.setFont(Constants.BASIC_FONT);
        langTitle.setHorizontalAlignment(JLabel.CENTER);
        langTitle.setForeground(Color.WHITE);
        langPanel.add(langTitle, BorderLayout.WEST);

        radioLangPanel.setBackground(Constants.BLUE);
        langPanel.add(radioLangPanel, BorderLayout.CENTER);

        musicSwitch.setSelected(DBConfig.getBoolean("mute"));
        musicSlider.setValue(musicSwitch.isSelected() ? musicSlider.getMinimum() : DBConfig.getInt("volume"));

        langButtons = new ButtonGroup();
        for(String s : LangFile.getLangs().keySet()) {
            TamaRadioButton b = new TamaRadioButton(LangFile.getLangs().get(s).getName(), Constants.PURPLE, Constants.DARK_PURPLE, Color.BLACK, Constants.BLUE);
            if(s.equals(LangFile.lang))
                b.setSelected(true);
            b.setBackground(Constants.BLUE);
            b.setForeground(Color.white);

            langButtons.add(b);
            radioLangPanel.add(b,c);
        }


        this.aboutPanel = new JPanel();
        this.aboutPanel.setBackground(Constants.BLUE);
        this.aboutPanel.add(new JLabel("Tamagotchi version " + Constants.version));
        this.aboutPanel.add(new JLabel(LangFile.getLangFile().getString("option.evan")));
        this.aboutPanel.add(new JLabel(LangFile.getLangFile().getString("option.quentin")));
        this.aboutPanel.add(new JLabel(LangFile.getLangFile().getString("option.alix")));
        this.aboutPanel.add(new JLabel(LangFile.getLangFile().getString("option.kilian")));
        this.aboutPanel.add(new JLabel(LangFile.getLangFile().getString("option.copyrights")));
        this.add(new JLabel()); // 0 0
        this.add(titlePanel);   // 0 1
        this.add(new JLabel()); // 0 2
        this.add(new JLabel()); // 1 0
        this.add(musicPanel);   // 1 1
        this.add(new JLabel()); // 1 2
        this.add(new JLabel()); // 2 0
        this.add(langPanel);    // 2 1
        this.add(new JLabel()); // 2 2
        this.add(cancelButton); // 3 0
        this.add(aboutPanel);   // 3 1
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
    
    /**
     * Return an array of JRadioButton used to switch language
     * @return an array of JRadioButton
     */
    public TamaRadioButton[] getRadioButtons()
    {
        TamaRadioButton[] b = new TamaRadioButton[langButtons.getButtonCount()];
        Enumeration<AbstractButton> e = langButtons.getElements();
        
        int i = 0;
        while(e.hasMoreElements())
            b[i++] = (TamaRadioButton)e.nextElement();
        return b;
    }

    /**
     * Method handling repainting of the panel
     */
    @Override
    public void updatePanel() {
        this.musicSwitch.setText(LangFile.getLangFile().getString("option.mute"));
        this.cancelButton.setText(LangFile.getLangFile().getString("option.cancel"));
        this.saveButton.setText(LangFile.getLangFile().getString("option.save"));
        this.title.setText(LangFile.getLangFile().getString("menu.options"));
        this.musicTitle.setText(LangFile.getLangFile().getString("option.volume") + " : ");
        this.langTitle.setText(LangFile.getLangFile().getString("menu.lang") + " : ");

        String[] s = { "option.evan", "option.quentin", "option.alix", "option.kilian", "option.copyrights" };
        
        for(int i = 1; i < aboutPanel.getComponentCount(); i++)
            ((JLabel)aboutPanel.getComponent(i)).setText(LangFile.getLangFile().getString(s[i-1]));

        this.repaint();
    }
}