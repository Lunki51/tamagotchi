package fr.tama.view.panels;

import fr.tama.controller.GameInstance;
import fr.tama.controller.LangFile;
import fr.tama.model.Constants;
import fr.tama.model.Current;
import fr.tama.model.Tamagotchi;
import fr.tama.view.utils.Updatable;
import fr.tama.view.components.TamaAttribBarComponent;
import fr.tama.view.components.TamaButton;

import javax.swing.*;
import java.awt.*;

/**
 * The GamePanel
 * contains the gamescreen and the controlscreen
 */
public class Game extends JPanel implements Updatable {

    private final TamaButton returnButton;

    private final JPanel graphicPanel;
    private final JPanel controlPanel;

    private final JLabel tamaName;
    private final JLabel mStateLabel;
    private final JLabel tamaMState;
    private final JLabel pStateLabel;
    private final JLabel tamaPState;
    private final TamaAttribBarComponent hungerGauge;
    private final JLabel hungerLabel;
    private final TamaAttribBarComponent hygeneGauge;
    private final JLabel hygeneLabel;
    private final TamaAttribBarComponent happinessGauge;
    private final JLabel happinessLabel;
    private final TamaAttribBarComponent toiletGauge;
    private final JLabel toiletLabel;
    private final TamaAttribBarComponent energyGauge;
    private final JLabel energyLabel;
    private final JLabel locationLabel;

    private final JButton actionButton;
    private final JButton moveLeftButton;
    private final JButton moveRightButton;

    private final GameScreen gameScreen;

    private final GameInstance gameInstance;
    private final LangFile lang;

    public Game(GameInstance gameInstance) {
        this.gameInstance = gameInstance;
        this.setLayout(new GridLayout(1,2));

        this.lang = LangFile.getLangFile();

        JSplitPane pane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT);
        pane.setResizeWeight(0.9);
        pane.setEnabled(false);
        pane.setDividerSize(0);

        this.graphicPanel = new JPanel();
        this.graphicPanel.setLayout(new GridLayout(1,1));
        this.gameScreen = new GameScreen(gameInstance);
        this.graphicPanel.add(this.gameScreen);
        pane.add(graphicPanel);


        this.controlPanel = new JPanel();
        this.controlPanel.setLayout(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();
        c.weightx=1;
        c.fill=GridBagConstraints.BOTH;
        c.gridwidth=2;
        this.controlPanel.setBackground(Constants.BLUE);

        c.weighty=0.1;
        c.gridy=0;
        c.gridx=0;
        this.returnButton = new TamaButton(LangFile.getLangFile().getString("menu.back"));
        this.controlPanel.add(this.returnButton,c);

        this.tamaName = new JLabel();
        this.tamaName.setHorizontalAlignment(JLabel.LEFT);
        this.tamaName.setForeground(Color.WHITE);
        this.tamaName.setFont(new Font("Arial", Font.BOLD, 30));
        c.weighty=0.2;
        c.gridy=1;
        c.insets=new Insets(0,50,0,0);
        this.controlPanel.add(this.tamaName,c);
        c.insets=new Insets(0,0,0,0);

        JLabel infoLabel = new JLabel("Informations : ");
        infoLabel.setHorizontalAlignment(JLabel.CENTER);
        infoLabel.setForeground(Color.WHITE);
        infoLabel.setFont(new Font("Arial", Font.BOLD, 20));

        c.gridy=2;
        this.controlPanel.add(infoLabel,c);

        JPanel statusPanel = new JPanel();
        statusPanel.setLayout(new GridBagLayout());
        GridBagConstraints c2 = new GridBagConstraints();
        c2.fill = GridBagConstraints.BOTH;
        c2.weightx=1;
        c2.weighty=0.2;
        c2.gridwidth=1;
        c2.insets = new Insets(2,5,2,0);
        statusPanel.setBackground(Constants.BLUE);
        this.mStateLabel = new JLabel("État mental : ");
        this.mStateLabel.setHorizontalAlignment(JLabel.RIGHT);
        this.mStateLabel.setForeground(Color.WHITE);
        c2.gridx=0;
        statusPanel.add(this.mStateLabel,c2);
        this.tamaMState = new JLabel("Bon");
        this.tamaMState.setHorizontalAlignment(JLabel.LEFT);
        this.tamaMState.setForeground(Color.WHITE);
        c2.gridx=1;
        statusPanel.add(this.tamaMState,c2);
        this.pStateLabel = new JLabel("État physique : ");
        this.pStateLabel.setHorizontalAlignment(JLabel.RIGHT);
        this.pStateLabel.setForeground(Color.WHITE);
        c2.gridx=0;
        c2.gridy=1;
        statusPanel.add(this.pStateLabel,c2);
        this.tamaPState = new JLabel("Mauvais");
        this.tamaPState.setHorizontalAlignment(JLabel.LEFT);
        this.tamaPState.setForeground(Color.WHITE);
        c2.gridx=1;
        statusPanel.add(this.tamaPState,c2);

        c2.weightx=0.2;
        c2.gridy=2;
        c2.gridx=1;
        this.hungerLabel = new JLabel("Faim");
        this.hungerLabel.setHorizontalAlignment(JLabel.LEFT);
        this.hungerLabel.setForeground(Color.WHITE);
        statusPanel.add(this.hungerLabel,c2);
        c2.gridx=0;
        c2.weightx=0.8;
        this.hungerGauge = new TamaAttribBarComponent(Color.DARK_GRAY);
        statusPanel.add(this.hungerGauge,c2);

        c2.gridy=3;
        c2.gridx=1;
        this.hygeneLabel = new JLabel("Hygiène");
        this.hygeneLabel.setHorizontalAlignment(JLabel.LEFT);
        this.hygeneLabel.setForeground(Color.WHITE);
        statusPanel.add(this.hygeneLabel,c2);
        this.hygeneGauge = new TamaAttribBarComponent(Color.CYAN);
        c2.gridx=0;
        statusPanel.add(this.hygeneGauge,c2);

        c2.gridy=4;
        c2.gridx=1;
        this.happinessLabel = new JLabel("Bonheur");
        this.happinessLabel.setHorizontalAlignment(JLabel.LEFT);
        this.happinessLabel.setForeground(Color.WHITE);
        statusPanel.add(this.happinessLabel,c2);
        c2.gridx=0;
        this.happinessGauge = new TamaAttribBarComponent(Color.ORANGE);
        statusPanel.add(this.happinessGauge,c2);

        c2.gridy=5;
        c2.gridx=1;
        this.toiletLabel = new JLabel("Toilettes");
        this.toiletLabel.setHorizontalAlignment(JLabel.LEFT);
        this.toiletLabel.setForeground(Color.WHITE);
        statusPanel.add(this.toiletLabel,c2);
        c2.gridx=0;
        this.toiletGauge = new TamaAttribBarComponent(Color.BLUE);
        statusPanel.add(this.toiletGauge,c2);

        c2.gridy=6;
        c2.gridx=1;
        this.energyLabel = new JLabel("Energie");
        this.energyLabel.setHorizontalAlignment(JLabel.LEFT);
        this.energyLabel.setForeground(Color.WHITE);
        statusPanel.add(this.energyLabel,c2);
        c2.gridx=0;
        this.energyGauge =new TamaAttribBarComponent(Color.YELLOW);
        statusPanel.add(this.energyGauge,c2);

        c.gridy=3;
        c.weighty=0.8;
        c.gridwidth=1;
        //statusPanel.setBorder(BorderFactory.createMatteBorder(5,0,0,0,Constants.PURPLE));
        this.controlPanel.add(statusPanel,c);

        this.actionButton = new TamaButton(""); // Le text est rajouté à chaque update du panel
        c.gridwidth=2;
        c.weighty=0.1;
        c.gridy=4;
        controlPanel.add(this.actionButton,c);

        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new GridLayout(0,3));
        buttonPanel.setBackground(Constants.BLUE);
        this.moveLeftButton = new TamaButton("<");
        buttonPanel.add(this.moveLeftButton);
        this.locationLabel = new JLabel();
        this.locationLabel.setHorizontalAlignment(JLabel.CENTER);
        this.locationLabel.setForeground(Color.WHITE);
        this.locationLabel.setFont(new Font("Arial", Font.BOLD, 20));
        buttonPanel.add(this.locationLabel);
        this.moveRightButton = new TamaButton(">");
        buttonPanel.add(this.moveRightButton);
        c.gridy=5;
        this.controlPanel.add(buttonPanel,c);

        pane.add(controlPanel);

        this.add(pane);
    }

    public GameScreen getGameScreen() {
        return gameScreen;
    }

    public void updatePanel(){
        Tamagotchi tamagotchi = this.gameInstance.getTamagotchi();
        if(tamagotchi.getAttribute(this.gameInstance.getLocation().getAction()).getCoolDown()!=0 && !this.gameInstance.getLocation().getAction().equals("tiredness")){
            this.actionButton.setText(String.valueOf(tamagotchi.getAttribute(this.gameInstance.getLocation().getAction()).getCoolDown()));
        }else{
            this.actionButton.setText(LangFile.getLangFile().getString("attribute_action_"+this.gameInstance.getLocation().getAction()+"_"+(this.gameInstance.getTamagotchi().getCurrent()==Current.ASLEEP?"off":"on")));
        }

        this.locationLabel.setText(LangFile.getLangFile().getString("location_"+this.gameInstance.getLocation().getName()));
        this.energyGauge.updateDisplay(tamagotchi.getAttribute("tiredness").getMax(),tamagotchi.getAttribute("tiredness").getValue());
        this.happinessGauge.updateDisplay(tamagotchi.getAttribute("happiness").getMax(),tamagotchi.getAttribute("happiness").getValue());
        this.hungerGauge.updateDisplay(tamagotchi.getAttribute("hunger").getMax(),tamagotchi.getAttribute("hunger").getValue());
        this.hygeneGauge.updateDisplay(tamagotchi.getAttribute("cleanliness").getMax(),tamagotchi.getAttribute("cleanliness").getValue());
        this.toiletGauge.updateDisplay(tamagotchi.getAttribute("toilet").getMax(),tamagotchi.getAttribute("toilet").getValue());
        this.tamaName.setText(this.gameInstance.getTamagotchi().getName()+this.gameInstance.getTamagotchi().getEvolCD());
        this.tamaMState.setText(lang.getString("state." + this.gameInstance.getTamagotchi().getMood().name())+tamagotchi.getMoodCD());
        this.tamaPState.setText(lang.getString("state." + this.gameInstance.getTamagotchi().getShape().name())+tamagotchi.getShapeCD());
        this.repaint();
    }

    public JButton getReturnButton() { return this.returnButton; }

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

    public TamaAttribBarComponent getHungerGauge() {
        return hungerGauge;
    }

    public JLabel getHungerLabel() {
        return hungerLabel;
    }

    public TamaAttribBarComponent getHygeneGauge() {
        return hygeneGauge;
    }

    public JLabel getHygeneLabel() {
        return hygeneLabel;
    }

    public TamaAttribBarComponent getHappinessGauge() {
        return happinessGauge;
    }

    public JLabel getHappinessLabel() {
        return happinessLabel;
    }

    public TamaAttribBarComponent getToiletGauge() {
        return toiletGauge;
    }

    public JLabel getToiletLabel() {
        return toiletLabel;
    }

    public TamaAttribBarComponent getEnergyGauge() {
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

}
