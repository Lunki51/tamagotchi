package fr.tama.view.components;

import fr.tama.controller.LangFile;
import fr.tama.model.Constants;
import fr.tama.view.utils.Updatable;

import javax.swing.*;
import javax.swing.border.MatteBorder;
import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.PlainDocument;
import java.awt.*;
import java.awt.event.ActionListener;

public class TamaSaveCard extends JPanel implements Updatable {

    private String currentPanel; // = 0 par défaut
    private final EmptySave emptySavePanel;
    private final SaveCreation saveCreationPanel;
    private final CreatedSave createdSavePanel;
    private final Difficulty difficultyPanel;
    private final ConfirmDelete confirmDeletePanel;

    public static final String EMPTY = "0";
    public static final String SAVE_CREATION = "1";
    public static final String CREATED = "2";
    public static final String DIFFICULTY = "3";
    public static final String CONFIRM_DELETE = "4";

    /**
     * Customized JPanel handling the display of a save or the creation of a new one
     */
    public TamaSaveCard() {
        this.setLayout(new CardLayout());
        emptySavePanel = new EmptySave(new ImageIcon(this.getClass().getClassLoader().getResource("sprites/tamagotchi/egg_plus.png")));
        saveCreationPanel = new SaveCreation();
        createdSavePanel = new CreatedSave();
        this.confirmDeletePanel = new ConfirmDelete();
        this.difficultyPanel = new Difficulty();
        this.add(emptySavePanel,EMPTY);
        emptySavePanel.addActionListener(e-> this.changePanel(SAVE_CREATION));
        saveCreationPanel.getValidation().addActionListener(e->this.changePanel(DIFFICULTY));
        difficultyPanel.getRetour().addActionListener(e->this.changePanel(SAVE_CREATION));
        createdSavePanel.getBin().addActionListener(e->this.changePanel(CONFIRM_DELETE));
        confirmDeletePanel.getReturnButton().addActionListener(e->this.changePanel(CREATED));

        this.add(saveCreationPanel,SAVE_CREATION);
        this.add(createdSavePanel,CREATED);
        this.add(difficultyPanel,DIFFICULTY);
        this.add(confirmDeletePanel,CONFIRM_DELETE);
        this.changePanel(DIFFICULTY);
        this.changePanel(EMPTY);
        this.setBackground(Constants.PURPLE);
    }

    /**
     * Initialize from raw information
     * @param name Name of the tamagotchi
     * @param type Type of the tamagotchi
     * @param level Level of the tamagotchi
     */
    public TamaSaveCard(String name, String type, String level){
        this();
        this.changePanel(CREATED);
        this.createdSavePanel.setup(name,type,level);
    }

    /**
     * Return the Difficulty instance from the save
     * @return Difficulty instance of the save
     */
    public int getDifficulty() {
        return difficultyPanel.getDifficulty().getSelectedIndex();
    }

    /**
     * Change 
     */
    public void changePanel(String newPanel){
        this.currentPanel=newPanel;
        ((CardLayout)this.getLayout()).show(this,currentPanel);
        this.updatePanel();

        if(newPanel.equals(SAVE_CREATION))
            this.saveCreationPanel.getTextField().requestFocus();
    }

    /**
     * Modify the level
     * @param level Level to be set
     */
    public void setLevel(String level){
        this.createdSavePanel.setLevel(level);
    }

    @Override
    public void updatePanel() {
        switch (currentPanel){
            case "0":
                ((Updatable)this.emptySavePanel).updatePanel();
                break;
            case "1":
                ((Updatable)this.saveCreationPanel).updatePanel();
                break;
            case "2":
                ((Updatable)this.createdSavePanel).updatePanel();
                break;
            case "3":
                ((Updatable)this.difficultyPanel).updatePanel();
                break;
            case "4":
                ((Updatable)this.confirmDeletePanel).updatePanel();
                break;
        }
        this.repaint();
    }

    /**
     * Return type of the tamagotchi
     * @return Type of tamagotchi
     */
    public String getTamagotchi(){
        return this.saveCreationPanel.getTamagotchi();
    }

    /**
     * Return name of the tamagotchi
     * @return Name of the tamagotchi
     */
    public String getName(){
        return this.saveCreationPanel.getName();
    }

    public void addCreateSaveListener(ActionListener l){
        this.difficultyPanel.getValidation().addActionListener(e->{
            this.changePanel(CREATED);
            this.createdSavePanel.setup(this.saveCreationPanel.getName(),this.saveCreationPanel.getTamagotchi(),"egg");
        });
        this.difficultyPanel.getValidation().addActionListener(l);
    }

    public void addDeleteSaveListener(ActionListener l){
        this.confirmDeletePanel.getConfirmButton().addActionListener(e-> this.changePanel(EMPTY));
        this.confirmDeletePanel.getConfirmButton().addActionListener(l);
    }

    public void addLoadSaveListener(ActionListener l) {
        this.createdSavePanel.addActionListener(l);
    }
}

class CreatedSave extends AbstractButton implements Updatable {

    private String name;
    private String type;
    private String level;
    private final JLabel label;
    private final EmptySave image;
    private final EmptySave bin;

    /**
     * Default save
     */
    public CreatedSave() {
        super();
        this.name = LangFile.getLangFile().getString("menu.name");
        this.type="Chien";
        this.level="egg";
        this.image = new EmptySave(new ImageIcon("/sprites/tamagotchi/big_egg_chat.png"));
        this.label = new JLabel(this.name);

        this.setLayout(new BorderLayout());
        JLayeredPane pane = new JLayeredPane();
        SpringLayout layout = new SpringLayout();
        pane.setLayout(layout);
        //pane.setLayout(new FlowLayout());
        JPanel inner = new JPanel();
        inner.setLayout(new GridBagLayout());
        inner.setBackground(Constants.PURPLE);
        inner.setBorder(null);
        GridBagConstraints c = new GridBagConstraints();

        c.fill = GridBagConstraints.BOTH;
        c.gridheight=1;
        c.weightx=1;
        c.weighty=0.25;
        c.gridy=0;
        c.gridx=0;
        c.gridwidth=1;
        label.setFont(Constants.BASIC_FONT);
        label.setHorizontalAlignment(SwingConstants.CENTER);
        label.setForeground(Color.WHITE);
        inner.add(label,c);
        c.gridy=1;
        c.weighty=0.75;
        c.weightx=1;
        inner.add(this.image,c);
        inner.setFocusable(false);
        pane.add(inner, Integer.valueOf(1));
        layout.putConstraint(SpringLayout.EAST, inner, 0, SpringLayout.EAST, pane);
        layout.putConstraint(SpringLayout.WEST, inner, 0, SpringLayout.WEST, pane);
        layout.putConstraint(SpringLayout.NORTH, inner, 0, SpringLayout.NORTH, pane);
        layout.putConstraint(SpringLayout.SOUTH, inner, 0, SpringLayout.SOUTH, pane);
        bin = new EmptySave(new ImageIcon(this.getClass().getClassLoader().getResource("sprites/background/trash.png")));
        bin.setHasBackground(false);
        pane.add(bin,Integer.valueOf(2));
        layout.putConstraint(SpringLayout.NORTH,bin,50,SpringLayout.HORIZONTAL_CENTER,pane);
        layout.putConstraint(SpringLayout.WEST,bin,50,SpringLayout.VERTICAL_CENTER,pane);
        layout.putConstraint(SpringLayout.EAST,bin,0,SpringLayout.EAST,pane);
        layout.putConstraint(SpringLayout.SOUTH,bin,0,SpringLayout.SOUTH,pane);

        this.add(pane,BorderLayout.CENTER);
    }

    /**
     * Set attributes raw information
     * @param name Name of the tamagotchi
     * @param type Type of the tamagotchi
     * @param level Level of the tamagotchi
     */
    public void setup(String name, String type,String level){
        this.name = name;
        this.type=type;
        this.level=level;
        this.updatePanel();
    }

    /**
     * Set new level value
     * @param level new value
     */
    public void setLevel(String level) {
        this.level = level;
    }

    public EmptySave getBin() {
        return bin;
    }

    @Override
    public void addActionListener(ActionListener l) {
        super.addActionListener(l);
        this.image.addActionListener(l);
    }

    @Override
    public void updatePanel() {
        this.label.setText(this.name);
        this.image.setIcon(new ImageIcon(this.getClass().getClassLoader().getResource("sprites/tamagotchi/"+this.level.toLowerCase()+"_"+this.type.toLowerCase()+".png")));
        this.image.updatePanel();
        this.repaint();
    }
}

class EmptySave extends JButton implements Updatable {

    private ImageIcon icon;
    private boolean hasBackground;

    public EmptySave(ImageIcon icon) {
        this.setBackground(Constants.PURPLE);
        this.icon=icon;
        this.hasBackground=true;
        this.setBorder(null);
    }

    public void setHasBackground(boolean hasBackground) {
        this.hasBackground = hasBackground;
    }

    @Override
    protected void paintComponent(Graphics g) {
        if(this.hasBackground){
            g.setColor(this.getBackground());
            g.fillRect(0,0,this.getWidth(),this.getHeight());
        }

        if(this.getWidth()<this.getHeight()){
            float size = ((this.getWidth()*icon.getIconHeight())/(float)icon.getIconWidth());
            g.drawImage(icon.getImage(),0,(int)((this.getHeight()-size)/2),this.getWidth(),(int)size,null);
        }else{
            float size = ((this.getHeight()*icon.getIconWidth())/(float)icon.getIconHeight());
            g.drawImage(icon.getImage(),(int)((this.getWidth()-size)/2),0,(int)size,this.getHeight(),null);
        }
    }

    public void setIcon(ImageIcon icon) {
        this.icon = icon;
    }

    @Override
    public void updatePanel() {
        this.repaint();
    }
}

class ArrowButton extends JButton{
    public static int LEFT = 0;
    public static int RIGHT = 1;
    public static int TOP = 2;
    public static int BOTTOM = 3;
    private final int side;

    public ArrowButton(int side) {
        this.side = side;
        this.setBorder(null);
    }

    @Override
    protected void paintComponent(Graphics g) {
        g.setColor(Constants.PURPLE);
        g.fillRect(0,0,this.getWidth(),this.getHeight());
        g.setColor(this.getBackground());

        if(this.side == 0)
        {
            g.fillPolygon(new int[]{0,this.getWidth()/3,this.getWidth()/3},new int[]{this.getHeight()/2,0,this.getHeight()},3);
            g.fillRect(this.getWidth()/3,this.getHeight()/2 - (this.getHeight()/6),(this.getWidth()/3)*2,(this.getHeight()/3));
        }
        else if(this.side == 1)
        {
            g.fillPolygon(new int[]{this.getWidth(),(this.getWidth()/3)*2,(this.getWidth()/3)*2},new int[]{this.getHeight()/2,0,this.getHeight()},3);
            g.fillRect(0,this.getHeight()/2 - (this.getHeight()/6),(this.getWidth()/3)*2,(this.getHeight()/3));
        }
    }
}

class SaveCreation extends JPanel implements Updatable {

    ImageIcon[] images = new ImageIcon[]{new ImageIcon(this.getClass().getClassLoader().getResource("sprites/tamagotchi/big_egg_chien.png")),
            new ImageIcon(this.getClass().getClassLoader().getResource("sprites/tamagotchi/big_egg_chat.png")),
            new ImageIcon(this.getClass().getClassLoader().getResource("sprites/tamagotchi/big_egg_lapin.png"))
             ,new ImageIcon(this.getClass().getClassLoader().getResource("sprites/tamagotchi/big_egg_robot.png"))
    };

    private int currentEgg = 0;

    private final EmptySave image;
    private final AbstractButton validation;
    private final JTextField jTextField;
    private final JLabel nameLabel;

    public SaveCreation() {
        this.setLayout(new GridBagLayout());
        this.setBackground(Constants.PURPLE);
        GridBagConstraints c = new GridBagConstraints();
        c.fill = GridBagConstraints.BOTH;
        c.gridheight=1;
        c.weightx=1;

        c.weighty=0.1;
        c.gridy=0;
        c.gridx=0;
        c.gridwidth=3;
        JPanel name = new JPanel();
        name.setBackground(Constants.PURPLE);
        nameLabel = new JLabel( );
        nameLabel.setFont(Constants.BASIC_FONT);
        nameLabel.setForeground(Color.WHITE);
        this.jTextField = new JTextField(7);
        this.jTextField.setBackground(Constants.PURPLE);
        this.jTextField.setBorder(new MatteBorder(0,0,1,0,Color.WHITE));
        this.jTextField.setFont(Constants.BASIC_FONT);
        this.jTextField.setForeground(Color.WHITE);
        this.jTextField.setDocument(new CustomTField(10));
        nameLabel.setHorizontalAlignment(SwingConstants.CENTER);
        name.add(nameLabel);
        name.add(this.jTextField);
        this.add(name,c);


        c.weighty=0.5;
        c.gridy=1;

        ArrowButton arrowLeft = new ArrowButton(ArrowButton.LEFT);
        ArrowButton arrowRight = new ArrowButton(ArrowButton.RIGHT);
        arrowLeft.addActionListener(e->{
            this.currentEgg++;
            if(this.currentEgg==this.images.length)this.currentEgg=0;
            this.updatePanel();
        });
        arrowRight.addActionListener(e->{
            this.currentEgg--;
            if(this.currentEgg<0)this.currentEgg=this.images.length-1;
            this.updatePanel();
        });
        arrowLeft.setBackground(Color.BLACK);
        arrowRight.setBackground(Color.BLACK);
        image = new EmptySave(images[currentEgg]);

        c.gridheight=1;
        c.gridx=0;
        c.weightx=0.2;
        c.gridwidth=1;
        c.weighty=1;
        JPanel panel = new JPanel();
        panel.setBackground(Constants.PURPLE);
        this.add(panel,c);
        c.gridy=2;
        c.weighty=0.4;
        this.add(arrowLeft,c);
        c.gridy=3;
        c.weighty=0.5;
        panel = new JPanel();
        panel.setBackground(Constants.PURPLE);
        this.add(panel,c);

        c.gridheight=3;
        c.gridx=1;
        c.weighty=1;
        c.gridy=1;
        c.gridwidth=1;
        c.weightx=0.8;
        this.add(image,c);

        c.gridheight=1;
        c.gridx=2;
        c.weightx=0.2;
        c.gridwidth=1;
        c.weighty=1;
        panel = new JPanel();
        panel.setBackground(Constants.PURPLE);
        this.add(panel,c);
        c.gridy=2;
        c.weighty=0.4;
        this.add(arrowRight,c);
        c.gridy=3;
        c.weighty=0.5;
        panel = new JPanel();
        panel.setBackground(Constants.PURPLE);
        this.add(panel,c);

        c.weighty=0.05;
        c.weightx=1;
        c.gridy=4;
        c.gridx=0;
        c.gridwidth=3;
        c.gridheight=1;
        JPanel validate = new JPanel();
        validate.setBackground(Constants.PURPLE);
        validation = new TamaButton("");
        validate.add(validation);

        this.add(validate,c);

    }

    @Override
    public void updatePanel() {
        image.setIcon((images[currentEgg]));
        image.updatePanel();
        this.nameLabel.setText(LangFile.getLangFile().getString("menu.name")  + " : ");
        this.validation.setText(LangFile.getLangFile().getString("menu.validate"));
        this.repaint();
    }

    public AbstractButton getValidation() {
        return validation;
    }

    public String getName(){
        return this.jTextField.getText();
    }

    public String getTamagotchi(){
        switch (this.currentEgg){
            case 0:
                return "Chien";
            case 1:
                return "Chat";
            case 2:
                return "Lapin";
            default:
                return "Robot";
        }
    }

    public JTextField getTextField() 
    {
        return this.jTextField;
    }
}

class CustomTField extends PlainDocument {

    private final int maxSize;

    public CustomTField(int maxSize) {
        super();
        this.maxSize=maxSize;
    }

    public void insertString(int offset, String str, AttributeSet attr) throws BadLocationException {
        if (str == null)
            return;

        if ((getLength() + str.length()) <= this.maxSize) {
            super.insertString(offset, str, attr);
        }
    }
}

class Difficulty extends JPanel implements Updatable{

    private final AbstractButton validation;
    private final JComboBox<String> difficulty;
    private final AbstractButton retour;
    private final JLabel title;

    public Difficulty() {
        this.setBackground(Constants.PURPLE);
        this.setLayout(new GridBagLayout());
        String[] difficulties = {LangFile.getLangFile().getString("menu.difficulty.0"),
                LangFile.getLangFile().getString("menu.difficulty.1"),
                LangFile.getLangFile().getString("menu.difficulty.2")};
        GridBagConstraints c = new GridBagConstraints();
        c.weightx=1;
        c.weighty=1;
        this.retour = new TamaButton("Retour");
        this.validation = new TamaButton("");
        this.difficulty = new JComboBox<>(difficulties);
        this.difficulty.setPreferredSize(new Dimension(200,40));
        this.title = new JLabel("");
        this.title.setFont(Constants.BASIC_FONT);
        this.title.setForeground(Color.WHITE);
        this.title.setVerticalAlignment(SwingConstants.CENTER);
        c.gridwidth=2;
        this.add(title,c);
        c.gridy=1;

        c.fill = GridBagConstraints.HORIZONTAL;
        c.insets=new Insets(0,50,0,50);
        this.add(this.difficulty,c);
        c.insets=new Insets(0,0,0,0);
        c.fill = GridBagConstraints.NONE;
        c.gridy=2;
        this.difficulty.setSelectedIndex(1);
        c.gridwidth=1;
        this.add(retour,c);

        c.gridx=1;
        this.add(validation,c);
    }

    public AbstractButton getValidation() {
        return validation;
    }

    public JComboBox<String> getDifficulty() {
        return difficulty;
    }

    public AbstractButton getRetour() {
        return retour;
    }

    @Override
    public void updatePanel() {
        this.validation.setText(LangFile.getLangFile().getString("menu.validate"));
        String[] difficulties = {LangFile.getLangFile().getString("menu.difficulty.0"),
                LangFile.getLangFile().getString("menu.difficulty.1"),
                LangFile.getLangFile().getString("menu.difficulty.2")};
        int selected = this.difficulty.getSelectedIndex();
        this.difficulty.setModel(new DefaultComboBoxModel<String>(difficulties));
        this.difficulty.setSelectedIndex(selected);
        this.title.setText(LangFile.getLangFile().getString("menu.difficulty"));
        this.retour.setText(LangFile.getLangFile().getString("save.back"));
    }
}

class ConfirmDelete extends JPanel implements Updatable{

    private final JLabel confirmText;
    private final AbstractButton confirmButton;
    private final AbstractButton returnButton;

    /**
     * Customized JPanel displaying asking confirmation to deletion of a save
     */
    public ConfirmDelete() {
        super();
        this.setLayout(new GridBagLayout());
        this.setBackground(Constants.PURPLE);
        this.confirmText = new JLabel();
        this.confirmButton = new TamaButton("");
        this.returnButton = new TamaButton("");
        this.confirmText.setFont(Constants.BASIC_FONT);
        this.confirmText.setForeground(Color.WHITE);
        this.confirmText.setVerticalAlignment(SwingConstants.CENTER);

        GridBagConstraints c =new GridBagConstraints();
        c.weightx=1;
        c.gridx=0;
        c.weighty=1;
        c.gridy=0;
        c.gridwidth=2;
        this.add(confirmText,c);
        c.gridy=1;
        c.gridwidth=1;
        this.add(returnButton,c);
        c.gridx=1;
        this.add(confirmButton,c);
    }

    public AbstractButton getConfirmButton() {
        return confirmButton;
    }

    public AbstractButton getReturnButton() {
        return returnButton;
    }

    @Override
    public void updatePanel() {
        this.confirmText.setText(LangFile.getLangFile().getString("save.delete.confirm"));
        this.confirmButton.setText(LangFile.getLangFile().getString("save.yes"));
        this.returnButton.setText(LangFile.getLangFile().getString("save.no"));
    }
}