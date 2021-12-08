package fr.tama.view;

import javax.swing.*;
import java.awt.*;

public class GamePanel extends JPanel {

    private JPanel graphicPanel;
    private JPanel controlPanel;

    private JLabel tamaName;
    private JLabel mStateLabel;
    private JLabel tamaMState;
    private JLabel pStateLabel;
    private JLabel tamaPState;
    private JLabel hungerGauge;
    private JLabel hungerLabel;
    private JLabel hygeneGauge;
    private JLabel hygeneLabel;
    private JLabel happinessGauge;
    private JLabel happinessLabel;
    private JLabel toiletGauge;
    private JLabel toiletLabel;
    private JLabel energyGauge;
    private JLabel energyLabel;
    private JLabel locationLabel;

    private JButton actionButton;
    private JButton moveLeftButton;
    private JButton moveRightButton;

    private JButton tmp;

    public GamePanel() {
        this.setLayout(new GridLayout(1,2));

        this.graphicPanel = new JPanel();
        this.graphicPanel.setLayout(new GridLayout(1,1));
        this.tmp = new JButton("ÉCRAN DE JEU");
        this.graphicPanel.add(this.tmp);
        this.add(graphicPanel);


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

        this.actionButton = new JButton("Faire ses besoins");
        controlPanel.add(this.actionButton);

        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new GridLayout(0,3));
        this.moveLeftButton = new JButton("<");
        buttonPanel.add(this.moveLeftButton);
        this.locationLabel = new JLabel("Salon");
        buttonPanel.add(this.locationLabel);
        this.moveRightButton = new JButton(">");
        buttonPanel.add(this.moveRightButton);
        this.controlPanel.add(buttonPanel);

        this.add(controlPanel);
    }

}
