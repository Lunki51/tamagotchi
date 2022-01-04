package fr.tama.view.panels;

import fr.tama.controller.GameInstance;
import fr.tama.controller.LangFile;
import fr.tama.model.Constants;
import fr.tama.model.Current;
import fr.tama.model.Tamagotchi;
import fr.tama.view.components.TamaBigButton;
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
    private final JLabel infoLabel;

    /**
     * Initialize JPanel and in-game components
     * @param gameInstance thread manipulating current game
     */
    public Game(GameInstance gameInstance) {
        this.gameInstance = gameInstance;
        this.setLayout(new GridLayout(1,2));

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
        this.returnButton = new TamaBigButton("");
        this.controlPanel.add(this.returnButton,c);

        this.tamaName = new JLabel();
        this.tamaName.setHorizontalAlignment(JLabel.LEFT);
        this.tamaName.setForeground(Color.WHITE);
        this.tamaName.setFont(Constants.BASIC_FONT);
        c.weighty=0.2;
        c.gridy=1;
        c.insets=new Insets(0,50,0,0);
        this.controlPanel.add(this.tamaName,c);
        c.insets=new Insets(0,0,0,0);

        infoLabel = new JLabel("");
        infoLabel.setHorizontalAlignment(JLabel.CENTER);
        infoLabel.setForeground(Color.WHITE);
        infoLabel.setFont(Constants.BASIC_FONT);

        c.gridy=2;
        this.controlPanel.add(infoLabel,c);

        JPanel statusPanel = new JPanel();
        statusPanel.setLayout(new GridBagLayout());
        GridBagConstraints c2 = new GridBagConstraints();
        c2.fill = GridBagConstraints.BOTH;
        c2.weightx=1;
        c2.weighty=0.2;
        c2.gridwidth=2;
        c2.insets = new Insets(2,5,2,0);
        statusPanel.setBackground(Constants.BLUE);
        this.mStateLabel = new JLabel("");
        this.mStateLabel.setHorizontalAlignment(JLabel.RIGHT);
        this.mStateLabel.setForeground(Color.WHITE);
        c2.gridx=0;
        statusPanel.add(this.mStateLabel,c2);
        this.tamaMState = new JLabel("");
        this.tamaMState.setHorizontalAlignment(JLabel.LEFT);
        this.tamaMState.setForeground(Color.WHITE);
        c2.gridx=2;
        statusPanel.add(this.tamaMState,c2);
        this.pStateLabel = new JLabel("");
        this.pStateLabel.setHorizontalAlignment(JLabel.RIGHT);
        this.pStateLabel.setForeground(Color.WHITE);
        c2.gridx=0;
        c2.gridy=1;
        statusPanel.add(this.pStateLabel,c2);
        this.tamaPState = new JLabel("");
        this.tamaPState.setHorizontalAlignment(JLabel.LEFT);
        this.tamaPState.setForeground(Color.WHITE);
        c2.gridx=2;
        statusPanel.add(this.tamaPState,c2);

        c2.insets = new Insets(2,11,2,0);
        c2.weightx=0.5;
        c2.gridy=2;

        c2.gridx=0;
        c2.gridwidth=3;
        this.hungerGauge = new TamaAttribBarComponent(Constants.LIGHT_PURPLE);
        statusPanel.add(this.hungerGauge,c2);

        c2.gridwidth=1;
        c2.weightx=1;
        c2.gridx=3;
        this.hungerLabel = new JLabel("");
        this.hungerLabel.setHorizontalAlignment(JLabel.CENTER);
        this.hungerLabel.setForeground(Color.WHITE);
        statusPanel.add(this.hungerLabel,c2);


        c2.gridy=3;
        c2.gridwidth=1;
        c2.weightx=1;
        c2.gridx=3;
        this.hygeneLabel = new JLabel("");
        this.hygeneLabel.setHorizontalAlignment(JLabel.CENTER);
        this.hygeneLabel.setForeground(Color.WHITE);
        statusPanel.add(this.hygeneLabel,c2);
        this.hygeneGauge = new TamaAttribBarComponent(Constants.LIGHT_PURPLE);
        c2.gridx=0;
        c2.gridwidth=3;
        statusPanel.add(this.hygeneGauge,c2);

        c2.gridy=4;
        c2.gridx=3;
        this.happinessLabel = new JLabel("");
        this.happinessLabel.setHorizontalAlignment(JLabel.CENTER);
        this.happinessLabel.setForeground(Color.WHITE);
        statusPanel.add(this.happinessLabel,c2);
        c2.gridx=0;
        this.happinessGauge = new TamaAttribBarComponent(Constants.LIGHT_PURPLE);
        statusPanel.add(this.happinessGauge,c2);

        c2.gridy=5;
        c2.gridx=3;
        this.toiletLabel = new JLabel("");
        this.toiletLabel.setHorizontalAlignment(JLabel.CENTER);
        this.toiletLabel.setForeground(Color.WHITE);
        statusPanel.add(this.toiletLabel,c2);
        c2.gridx=0;
        this.toiletGauge = new TamaAttribBarComponent(Constants.LIGHT_PURPLE);
        statusPanel.add(this.toiletGauge,c2);

        c2.gridy=6;
        c2.gridx=3;
        this.energyLabel = new JLabel("");
        this.energyLabel.setHorizontalAlignment(JLabel.CENTER);
        this.energyLabel.setForeground(Color.WHITE);
        statusPanel.add(this.energyLabel,c2);
        c2.gridx=0;
        this.energyGauge =new TamaAttribBarComponent(Constants.LIGHT_PURPLE);
        statusPanel.add(this.energyGauge,c2);

        c.gridy=3;
        c.weighty=0.8;
        c.gridwidth=1;
        //statusPanel.setBorder(BorderFactory.createMatteBorder(5,0,0,0,Constants.PURPLE));
        this.controlPanel.add(statusPanel,c);

        this.actionButton = new TamaBigButton(""); // Le text est rajouté à chaque update du panel
        c.gridwidth=2;
        c.weighty=0.1;
        c.gridy=4;
        controlPanel.add(this.actionButton,c);

        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new GridLayout(0,3));
        buttonPanel.setBackground(Constants.BLUE);
        this.moveLeftButton = new TamaBigButton("<");
        buttonPanel.add(this.moveLeftButton);
        this.locationLabel = new JLabel();
        this.locationLabel.setHorizontalAlignment(JLabel.CENTER);
        this.locationLabel.setForeground(Color.WHITE);
        this.locationLabel.setFont(new Font("Arial", Font.BOLD, 20));
        buttonPanel.add(this.locationLabel);
        this.moveRightButton = new TamaBigButton(">");
        buttonPanel.add(this.moveRightButton);
        c.gridy=5;
        this.controlPanel.add(buttonPanel,c);

        pane.add(controlPanel);

        this.add(pane);


    }

    public GameScreen getGameScreen() {
        return gameScreen;
    }

    /**
     * Method handling repainting of the panel
     */
    @Override
    public void updatePanel(){

        Tamagotchi tamagotchi = this.gameInstance.getTamagotchi();
        if(tamagotchi.getAttribute(this.gameInstance.getLocation().getAction()).getCoolDown()!=0 && !this.gameInstance.getLocation().getAction().equals("tiredness")){
            int time = tamagotchi.getAttribute(this.gameInstance.getLocation().getAction()).getCoolDown()*5;
            int minuts = time%60;
            int hours = time/60;
            String text = "";
            if(hours!=0){
                text+=hours + " " + LangFile.getLangFile().getString("game.hour")+" ";
            }
            if(minuts!=0){
                text+=minuts + " " + LangFile.getLangFile().getString("game.minute");
            }
            this.actionButton.setText(text);
        }else{
            this.actionButton.setText(LangFile.getLangFile().getString("attribute_action_"+this.gameInstance.getLocation().getAction()+"_"+(this.gameInstance.getTamagotchi().getCurrent()==Current.ASLEEP?"off":"on")));
        }

        this.locationLabel.setText(LangFile.getLangFile().getString("location_"+this.gameInstance.getLocation().getName()));
        this.energyGauge.updateDisplay(tamagotchi.getAttribute("tiredness").getMax(),tamagotchi.getAttribute("tiredness").getValue());
        this.happinessGauge.updateDisplay(tamagotchi.getAttribute("happiness").getMax(),tamagotchi.getAttribute("happiness").getValue());
        this.hungerGauge.updateDisplay(tamagotchi.getAttribute("hunger").getMax(),tamagotchi.getAttribute("hunger").getValue());
        this.hygeneGauge.updateDisplay(tamagotchi.getAttribute("cleanliness").getMax(),tamagotchi.getAttribute("cleanliness").getValue());
        this.toiletGauge.updateDisplay(tamagotchi.getAttribute("toilet").getMax(),tamagotchi.getAttribute("toilet").getValue());
        this.tamaName.setText(this.gameInstance.getTamagotchi().getName());
        this.tamaMState.setText(LangFile.getLangFile().getString("state." + this.gameInstance.getTamagotchi().getMood().name()));
        this.tamaPState.setText(LangFile.getLangFile().getString("state." + this.gameInstance.getTamagotchi().getShape().name()));
        this.infoLabel.setText(LangFile.getLangFile().getString("game.info"));
        this.returnButton.setText(LangFile.getLangFile().getString("menu.back"));
        this.mStateLabel.setText(LangFile.getLangFile().getString("game.mood"));
        this.pStateLabel.setText(LangFile.getLangFile().getString("game.shape"));

        if(this.gameInstance.getTamagotchi().getDifficulty()==2){
            this.energyGauge.setVisible(false);
            this.happinessGauge.setVisible(false);
            this.hungerGauge.setVisible(false);
            this.hygeneGauge.setVisible(false);
            this.toiletGauge.setVisible(false);
            this.toiletLabel.setText("");
            this.energyLabel.setText("");
            this.hungerLabel.setText("");
            this.hygeneLabel.setText("");
            this.happinessLabel.setText("");
        }else{
            this.energyGauge.setVisible(true);
            this.happinessGauge.setVisible(true);
            this.hungerGauge.setVisible(true);
            this.hygeneGauge.setVisible(true);
            this.toiletGauge.setVisible(true);
            this.toiletLabel.setText(LangFile.getLangFile().getString("attribute_name_toilet"));
            this.energyLabel.setText(LangFile.getLangFile().getString("attribute_name_tiredness"));
            this.hungerLabel.setText(LangFile.getLangFile().getString("attribute_name_hunger"));
            this.hygeneLabel.setText(LangFile.getLangFile().getString("attribute_name_cleanliness"));
            this.happinessLabel.setText(LangFile.getLangFile().getString("attribute_name_happiness"));
        }
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
