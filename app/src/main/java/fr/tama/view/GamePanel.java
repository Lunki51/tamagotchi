package fr.tama.view;

import fr.tama.controller.GameInstance;
import fr.tama.controller.LangFile;

import javax.swing.*;
import java.awt.*;

public class GamePanel extends JPanel {

    private final JPanel graphicPanel;
    private final JPanel controlPanel;

    private final JLabel tamaName;
    private final JLabel mStateLabel;
    private final JLabel tamaMState;
    private final JLabel pStateLabel;
    private final JLabel tamaPState;
    private final JLabel hungerGauge;
    private final JLabel hungerLabel;
    private final JLabel hygeneGauge;
    private final JLabel hygeneLabel;
    private final JLabel happinessGauge;
    private final JLabel happinessLabel;
    private final JLabel toiletGauge;
    private final JLabel toiletLabel;
    private final JLabel energyGauge;
    private final JLabel energyLabel;
    private final JLabel locationLabel;

    private final JButton actionButton;
    private final JButton moveLeftButton;
    private final JButton moveRightButton;

    private JButton tmp;

    private final GameScreen gameScreen;

    private final GameInstance gameInstance;

    public GamePanel(GameInstance gameInstance) {
        this.gameInstance = gameInstance;
        this.setLayout(new GridLayout(1,2));

        JSplitPane pane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT);
        pane.setResizeWeight(0.7);
        pane.setEnabled(false);
        pane.setDividerSize(0);

        this.graphicPanel = new JPanel();
        this.graphicPanel.setLayout(new GridLayout(1,1));
        this.gameScreen = new GameScreen(gameInstance);
        this.graphicPanel.add(this.gameScreen);
        pane.add(graphicPanel);


        this.controlPanel = new JPanel();
        this.controlPanel.setLayout(new GridLayout(0,1));

        this.tamaName = new JLabel("Tamagochoupi <3");
        this.controlPanel.add(this.tamaName);

        JPanel statusPanel = new JPanel();
        statusPanel.setLayout(new GridLayout(0,2));
        this.mStateLabel = new JLabel("État mental");
        statusPanel.add(this.mStateLabel);
        this.tamaMState = new JLabel("Bon");
        statusPanel.add(this.tamaMState);
        this.pStateLabel = new JLabel("État physique");
        statusPanel.add(this.pStateLabel);
        this.tamaPState = new JLabel("Mauvais");
        statusPanel.add(this.tamaPState);
        this.hungerGauge = new JLabel("TMP JAUGE");
        statusPanel.add(this.hungerGauge);
        this.hungerLabel = new JLabel("Faim");
        statusPanel.add(this.hungerLabel);
        this.hygeneGauge = new JLabel("TMP JAUGE");
        statusPanel.add(this.hygeneGauge);
        this.hygeneLabel = new JLabel("Higiène");
        statusPanel.add(this.hygeneLabel);
        this.happinessGauge = new JLabel("TMP JAUGE");
        statusPanel.add(this.happinessGauge);
        this.happinessLabel = new JLabel("Bonheur");
        statusPanel.add(this.happinessLabel);
        this.toiletGauge = new JLabel("TMP JAUGE");
        statusPanel.add(this.toiletGauge);
        this.toiletLabel = new JLabel("Toilettes");
        statusPanel.add(this.toiletLabel);
        this.energyGauge = new JLabel("TMP JAUGE");
        statusPanel.add(this.energyGauge);
        this.energyLabel = new JLabel("Energie");
        statusPanel.add(this.energyLabel);
        this.controlPanel.add(statusPanel);

        this.actionButton = new JButton();
        controlPanel.add(this.actionButton);

        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new GridLayout(0,3));
        this.moveLeftButton = new JButton("<");
        buttonPanel.add(this.moveLeftButton);
        this.locationLabel = new JLabel();
        buttonPanel.add(this.locationLabel);
        this.moveRightButton = new JButton(">");
        buttonPanel.add(this.moveRightButton);
        this.controlPanel.add(buttonPanel);

        pane.add(controlPanel);

        this.add(pane);
    }

    public void updatePanel(){
        this.actionButton.setText(LangFile.getLangFile().getString("attribute_action_"+this.gameInstance.getLocation().getAction()));
        this.locationLabel.setText(LangFile.getLangFile().getString("location_"+this.gameInstance.getLocation().getName()));
        this.tamaName.setText(this.gameInstance.getTamagotchi().getName());
        this.tamaMState.setText(this.gameInstance.getTamagotchi().getMood().name());
        this.tamaPState.setText(this.gameInstance.getTamagotchi().getShape().name());
        this.gameScreen.repaint();
    }

    public JPanel getGraphicPanel() {
        return graphicPanel;
    }

    public JPanel getControlPanel() {
        return controlPanel;
    }

    public JLabel getTamaName() {
        return tamaName;
    }

    public JLabel getmStateLabel() {
        return mStateLabel;
    }

    public JLabel getTamaMState() {
        return tamaMState;
    }

    public JLabel getpStateLabel() {
        return pStateLabel;
    }

    public JLabel getTamaPState() {
        return tamaPState;
    }

    public JLabel getHungerGauge() {
        return hungerGauge;
    }

    public JLabel getHungerLabel() {
        return hungerLabel;
    }

    public JLabel getHygeneGauge() {
        return hygeneGauge;
    }

    public JLabel getHygeneLabel() {
        return hygeneLabel;
    }

    public JLabel getHappinessGauge() {
        return happinessGauge;
    }

    public JLabel getHappinessLabel() {
        return happinessLabel;
    }

    public JLabel getToiletGauge() {
        return toiletGauge;
    }

    public JLabel getToiletLabel() {
        return toiletLabel;
    }

    public JLabel getEnergyGauge() {
        return energyGauge;
    }

    public JLabel getEnergyLabel() {
        return energyLabel;
    }

    public JLabel getLocationLabel() {
        return locationLabel;
    }

    public JButton getActionButton() {
        return actionButton;
    }

    public JButton getMoveLeftButton() {
        return moveLeftButton;
    }

    public JButton getMoveRightButton() {
        return moveRightButton;
    }

    public JButton getTmp() {
        return tmp;
    }
}
