package fr.tama.view.components;

import fr.tama.model.Constants;

import javax.swing.*;

public class TamaBigButton extends TamaButton {
    /**
     * Customized JButton
     * @param text Text in button
     */
    public TamaBigButton(String text){
        super(text);
        this.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createLineBorder(Constants.BLUE, 10), BorderFactory.createLineBorder(Constants.PURPLE, 10)));
    }
}
