package fr.tama.view.panels;

import fr.tama.model.Constants;
import fr.tama.model.GameSave;
import fr.tama.view.components.TamaButton;
import fr.tama.view.components.TamaSaveCard;
import fr.tama.view.utils.Updatable;

import javax.swing.*;
import java.awt.*;

public class Saves extends JPanel implements Updatable {


    private final TamaSaveCard saveCardPanel1;
    private final TamaSaveCard saveCardPanel2;
    private final TamaSaveCard saveCardPanel3;
    private final TamaButton returnButton;

    public Saves() {
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
            this.saveCardPanel1 = new TamaSaveCard();
        }else{
            this.saveCardPanel1 = new TamaSaveCard(save.getTamagotchi().getName(), save.getTamagotchi().toString(),save.getTamagotchi().getLevel().name());
        }

        c.gridx=0;
        c.anchor=GridBagConstraints.PAGE_START;
        middle.add(this.saveCardPanel1 ,c);

        save = GameSave.loadSave(1);
        if(save==null){
            this.saveCardPanel2 = new TamaSaveCard();
        }else{
            this.saveCardPanel2 = new TamaSaveCard(save.getTamagotchi().getName(), save.getTamagotchi().toString(),save.getTamagotchi().getLevel().name());
        }

        c.gridx=1;
        c.anchor=GridBagConstraints.CENTER;
        c.insets = new Insets(0,150,0,150);
        middle.add(this.saveCardPanel2,c);
        c.insets = new Insets(0,0,0,0);

        save = GameSave.loadSave(2);
        if(save==null){
            this.saveCardPanel3 = new TamaSaveCard();
        }else{
            this.saveCardPanel3 = new TamaSaveCard(save.getTamagotchi().getName(), save.getTamagotchi().toString(),save.getTamagotchi().getLevel().name());
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
        this.returnButton = new TamaButton("Retour");
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

        this.saveCardPanel1.updatePanel();
        this.saveCardPanel2.updatePanel();
        this.saveCardPanel3.updatePanel();
        this.repaint();
    }

    public TamaSaveCard getSaveCardPanel1() {
        return saveCardPanel1;
    }

    public TamaSaveCard getSaveCardPanel2() {
        return saveCardPanel2;
    }

    public TamaSaveCard getSaveCardPanel3() {
        return saveCardPanel3;
    }

}