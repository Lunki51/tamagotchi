package fr.tama.view;

import fr.tama.model.Constants;
import fr.tama.model.Lapin;

import javax.swing.*;
import javax.swing.border.MatteBorder;
import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.PlainDocument;
import java.awt.*;
import java.awt.event.ActionListener;

public class SaveCardPanel extends JPanel implements UpdatablePanel {

    private int currentPanel=0;
    private final EmptySavePanel emptySavePanel;
    private final SaveCreationPanel saveCreationPanel;
    private final CreatedSavePanel createdSavePanel;

    public SaveCardPanel() {
        this.setLayout(new CardLayout());
        emptySavePanel = new EmptySavePanel(new ImageIcon(this.getClass().getClassLoader().getResource("sprites/tamagotchi/big_egg_chien.png")));
        saveCreationPanel = new SaveCreationPanel();
        createdSavePanel = new CreatedSavePanel();
        this.add(emptySavePanel,"0");
        emptySavePanel.addActionListener(e-> this.changePanel(1));
        this.add(saveCreationPanel,"1");
        this.add(createdSavePanel,"2");
        switch(currentPanel){
            case 0:
                ((CardLayout)this.getLayout()).show(this,"0");
                break;
            case 1:
                ((CardLayout)this.getLayout()).show(this,"1");
                break;
            case 2:
                ((CardLayout)this.getLayout()).show(this,"2");
                break;
        }
        this.setBackground(Constants.PURPLE);
    }

    public SaveCardPanel(String name,String type,String level){
        this();
        this.changePanel(2);
        this.createdSavePanel.setup(name,type,level);
    }

    @Override
    public void setSize(int width, int height) {
        super.setSize(height, height);
    }

    @Override
    public void setSize(Dimension d) {
        this.setSize( d.height, d.height);
    }

    void changePanel(int newPanel){
        this.currentPanel=newPanel;
        switch(currentPanel){
            case 0:
                ((CardLayout)this.getLayout()).show(this,"0");
                break;
            case 1:
                ((CardLayout)this.getLayout()).show(this,"1");
                break;
            case 2:
                ((CardLayout)this.getLayout()).show(this,"2");
                break;
        }
        this.updatePanel();
    }

    @Override
    public void updatePanel() {
        switch (currentPanel){
            case 0:
                ((UpdatablePanel)this.emptySavePanel).updatePanel();
                break;
            case 1:
                ((UpdatablePanel)this.saveCreationPanel).updatePanel();
                break;
            case 2:
                ((UpdatablePanel)this.createdSavePanel).updatePanel();
                break;
        }
        this.repaint();
    }

    public String getTamagotchi(){
        return this.saveCreationPanel.getTamagotchi();
    }

    public String getName(){
        return this.saveCreationPanel.getName();
    }

    public void addCreateSaveListener(ActionListener l){
        this.saveCreationPanel.getValidation().addActionListener(e->{
            this.changePanel(2);
            this.createdSavePanel.setup(this.saveCreationPanel.getName(),this.saveCreationPanel.getTamagotchi(),"egg");
        });
        this.saveCreationPanel.getValidation().addActionListener(l);
    }

    public void addDeleteSaveListener(ActionListener l){
        this.createdSavePanel.getBin().addActionListener(e->{
            this.changePanel(0);
        });
        this.createdSavePanel.getBin().addActionListener(l);
    }

    public void addLoadSaveListener(ActionListener l) {
        this.createdSavePanel.addActionListener(l);
    }
}

class CreatedSavePanel extends AbstractButton implements UpdatablePanel{

    private String name;
    private String type;
    private String level;
    private final JLabel label;
    private final EmptySavePanel image;
    private final EmptySavePanel bin;

    public CreatedSavePanel() {
        super();
        this.name = "Name";
        this.type="Chien";
        this.level="egg";
        this.image = new EmptySavePanel(new ImageIcon("/sprites/tamagotchi/big_egg_chat.png"));
        this.label =new JLabel(this.name);

        this.setLayout(new BorderLayout());
        this.setBorder(BorderFactory.createLineBorder(Color.BLACK));
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
        inner.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        pane.add(inner, Integer.valueOf(1));
        layout.putConstraint(SpringLayout.EAST, inner, 0, SpringLayout.EAST, pane);
        layout.putConstraint(SpringLayout.WEST, inner, 0, SpringLayout.WEST, pane);
        layout.putConstraint(SpringLayout.NORTH, inner, 0, SpringLayout.NORTH, pane);
        layout.putConstraint(SpringLayout.SOUTH, inner, 0, SpringLayout.SOUTH, pane);
        bin = new EmptySavePanel(new ImageIcon(this.getClass().getClassLoader().getResource("sprites/background/trash.png")));
        bin.setHasBackground(false);
        pane.add(bin,Integer.valueOf(2));
        layout.putConstraint(SpringLayout.NORTH,bin,50,SpringLayout.HORIZONTAL_CENTER,pane);
        layout.putConstraint(SpringLayout.WEST,bin,50,SpringLayout.VERTICAL_CENTER,pane);
        layout.putConstraint(SpringLayout.EAST,bin,0,SpringLayout.EAST,pane);
        layout.putConstraint(SpringLayout.SOUTH,bin,0,SpringLayout.SOUTH,pane);

        pane.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        this.add(pane,BorderLayout.CENTER);
    }

    public void setup(String name, String type,String level){
        this.name = name;
        this.type=type;
        this.level=level;
        this.updatePanel();
    }

    public EmptySavePanel getBin() {
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

class EmptySavePanel extends JButton implements UpdatablePanel{

    private ImageIcon icon;
    private boolean hasBackground;

    public EmptySavePanel(ImageIcon icon) {
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
        g.setColor(this.getBackground());
        switch (this.side){
            case 0:
                g.fillPolygon(new int[]{0,this.getWidth()/3,this.getWidth()/3},new int[]{this.getHeight()/2,0,this.getHeight()},3);
                g.fillRect(this.getWidth()/3,this.getHeight()/3,(this.getWidth()/3)*2,(this.getHeight()/3));
                break;
            case 1:
                g.fillPolygon(new int[]{this.getWidth(),(this.getWidth()/3)*2,(this.getWidth()/3)*2},new int[]{this.getHeight()/2,0,this.getHeight()},3);
                g.fillRect(0,this.getHeight()/3,(this.getWidth()/3)*2,(this.getHeight()/3));
                break;
            case 2:

                break;
            case 3:

                break;
        }
    }
}

class SaveCreationPanel extends JPanel implements UpdatablePanel{

    ImageIcon[] images = new ImageIcon[]{new ImageIcon(this.getClass().getClassLoader().getResource("sprites/tamagotchi/big_egg_chien.png")),
            new ImageIcon(this.getClass().getClassLoader().getResource("sprites/tamagotchi/big_egg_chat.png")),
            new ImageIcon(this.getClass().getClassLoader().getResource("sprites/tamagotchi/big_egg_lapin.png"))
             ,new ImageIcon(this.getClass().getClassLoader().getResource("sprites/tamagotchi/big_egg_robot.png"))
    };

    private int currentEgg = 0;

    private final EmptySavePanel image;
    private final JButton validation;
    private final JTextField jTextField;

    public SaveCreationPanel() {
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
        JLabel label = new JLabel("NAME : ");
        label.setFont(Constants.BASIC_FONT);
        label.setForeground(Color.WHITE);
        this.jTextField = new JTextField(7);
        this.jTextField.setBackground(Constants.PURPLE);
        this.jTextField.setBorder(new MatteBorder(0,0,1,0,Color.WHITE));
        this.jTextField.setFont(Constants.BASIC_FONT);
        this.jTextField.setForeground(Color.WHITE);
        this.jTextField.setDocument(new CustomTField(10));
        label.setHorizontalAlignment(SwingConstants.CENTER);
        name.add(label);
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
        image = new EmptySavePanel(images[currentEgg]);

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
        validation = new JButton("Valider");
        validate.add(validation);

        this.add(validate,c);
    }

    public JButton getValidation() {
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

    @Override
    public void updatePanel() {
        image.setIcon((images[currentEgg]));
        image.updatePanel();
        this.repaint();
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