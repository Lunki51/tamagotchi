package fr.tama.view.panels;

import fr.tama.controller.LangFile;
import fr.tama.model.Constants;
import fr.tama.view.components.TamaBigButton;
import fr.tama.view.components.TamaButton;
import fr.tama.view.utils.Updatable;

import javax.swing.*;
import java.awt.*;

public class Death extends JPanel implements Updatable {

    private final TamaButton returnButton;
    private final TamaButton buttonQuit;

    /**
     * Initialization of Death screen panel
     */
    public Death() {

        super(new BorderLayout());
        super.setBackground(Constants.BLUE);
        JLabel deathLabel = new JLabel(LangFile.getLangFile().getString("death"));
        deathLabel.setHorizontalAlignment(JLabel.CENTER);
        deathLabel.setForeground(Color.WHITE);
        deathLabel.setFont(new Font("Arial", Font.BOLD, 35));
        this.add(deathLabel, BorderLayout.CENTER);
        JPanel buttonPanel = new JPanel();
        buttonPanel.setBackground(Constants.BLUE);
        buttonPanel.setLayout(new GridLayout(3,3, 12, 12));
        this.add(buttonPanel, BorderLayout.SOUTH);

        this.returnButton = new TamaBigButton(LangFile.getLangFile().getString("menu.back"));
        buttonPanel.add(new JLabel());
        buttonPanel.add(this.returnButton);
        buttonPanel.add(new JLabel());
        buttonPanel.add(new JLabel());
        this.buttonQuit = new TamaBigButton(LangFile.getLangFile().getString("menu.quit"));
        buttonPanel.add(this.buttonQuit);
        buttonPanel.add(new JLabel());
        buttonPanel.add(new JLabel());
        buttonPanel.add(new JLabel());
    }
    

    public TamaButton getReturnButton() { return this.returnButton; }
    public TamaButton getButtonQuit() {
        return this.buttonQuit;
    }

    /**
     * Method handling repainting of the panel
     */
    @Override
    public void updatePanel() {
        this.returnButton.setText(LangFile.getLangFile().getString("menu.back"));
        this.buttonQuit.setText(LangFile.getLangFile().getString("menu.quit"));
        this.repaint();
    }
}
