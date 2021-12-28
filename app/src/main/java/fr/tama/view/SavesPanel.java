package fr.tama.view;

import fr.tama.model.Constants;
import fr.tama.model.GameSave;
import fr.tama.model.Tamagotchi;

import javax.swing.*;
import java.awt.*;

public class SavesPanel extends JPanel implements UpdatablePanel {


    private final SaveCardPanel saveCardPanel1;
    private final SaveCardPanel saveCardPanel2;
    private final SaveCardPanel saveCardPanel3;
    private final MenuButton returnButton;

    public SavesPanel() {
        super(new GridBagLayout());
        this.setBackground(Constants.BLUE);
        GridBagConstraints mainC = new GridBagConstraints();
        mainC.fill = GridBagConstraints.BOTH;
        mainC.gridwidth=1;
        mainC.anchor=GridBagConstraints.CENTER;
        mainC.weighty=1;
        mainC.weightx=1;
        mainC.gridx=0;

        JPanel titlePanel = new JPanel(new BorderLayout());
        JLabel label = new JLabel("SAUVEGARDES");
        label.setForeground(Color.white);
        label.setFont(Constants.TITLE_FONT);
        label.setHorizontalAlignment(SwingConstants.CENTER);
        titlePanel.setBackground(Constants.BLUE);
        titlePanel.add(label,BorderLayout.CENTER);
        mainC.gridheight=1;
        mainC.weighty=0.75;
        mainC.gridy=0;
        this.add(titlePanel,mainC);
        JPanel middle = new JPanel(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();
        c.fill = GridBagConstraints.BOTH;
        c.gridwidth=1;
        c.gridheight=1;
        c.weighty=1;
        c.weightx=1;

        GameSave save = GameSave.loadSave(0);
        if(save==null){
            this.saveCardPanel1 = new SaveCardPanel();
        }else{
            this.saveCardPanel1 = new SaveCardPanel(save.getTamagotchi().getName(), save.getTamagotchi().toString());
        }

        c.gridx=0;
        c.anchor=GridBagConstraints.PAGE_START;
        middle.add(this.saveCardPanel1 ,c);

        save = GameSave.loadSave(1);
        if(save==null){
            this.saveCardPanel2 = new SaveCardPanel();
        }else{
            this.saveCardPanel2 = new SaveCardPanel(save.getTamagotchi().getName(), save.getTamagotchi().toString());
        }

        c.gridx=1;
        c.anchor=GridBagConstraints.CENTER;
        c.insets = new Insets(0,150,0,150);
        middle.add(this.saveCardPanel2,c);
        c.insets = new Insets(0,0,0,0);

        save = GameSave.loadSave(2);
        if(save==null){
            this.saveCardPanel3 = new SaveCardPanel();
        }else{
            this.saveCardPanel3 = new SaveCardPanel(save.getTamagotchi().getName(), save.getTamagotchi().toString());
        }

        c.anchor=GridBagConstraints.PAGE_END;
        c.gridx=2;
        middle.add(this.saveCardPanel3,c);

        mainC.gridheight=2;
        mainC.gridy=1;
        mainC.weighty=1;
        mainC.insets = new Insets(0,50,0,50);
        middle.setBackground(Constants.BLUE);
        this.add(middle,mainC);
        JPanel buttonsPanel = new JPanel(new GridBagLayout());
        buttonsPanel.setBackground(Constants.BLUE);
        mainC.gridheight=1;
        mainC.gridy=3;
        mainC.weighty=0.75;
        mainC.insets = new Insets(0,0,0,0);

        GridBagConstraints c3 = new GridBagConstraints();
        c3.fill = GridBagConstraints.BOTH;
        c3.gridheight=1;
        c3.anchor=GridBagConstraints.CENTER;
        c3.weighty=1;
        c3.weightx=0.2;
        c3.gridx=0;
        c3.gridy=0;
        JPanel fill = new JPanel();
        fill.setBackground(Constants.BLUE);
        buttonsPanel.add(fill,c3);
        c3.gridy=1;
        this.returnButton = new MenuButton("Retour");
        buttonsPanel.add(this.returnButton,c3);
        c3.gridx=1;
        c3.weightx=1;
        fill = new JPanel();
        fill.setBackground(Constants.BLUE);
        buttonsPanel.add(fill,c3);

        this.add(buttonsPanel,mainC);
    }

    public JButton getReturnButton() { return returnButton; }

    @Override
    public void updatePanel() {
        this.repaint();
        this.saveCardPanel1.updatePanel();
        this.saveCardPanel2.updatePanel();
        this.saveCardPanel3.updatePanel();
    }

    public SaveCardPanel getSaveCardPanel1() {
        return saveCardPanel1;
    }

    public SaveCardPanel getSaveCardPanel2() {
        return saveCardPanel2;
    }

    public SaveCardPanel getSaveCardPanel3() {
        return saveCardPanel3;
    }

}