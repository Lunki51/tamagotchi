package fr.tama.view;

import fr.tama.controller.LangFile;
import fr.tama.model.Constants;

import javax.swing.*;
import java.awt.*;

public class SavesPanel extends JPanel {

    private final JPanel topPanel;
    private final JPanel choicePanel;
    private final JPanel choicePlayPanel;
    private final JPanel choiceDeletePanel;
    private final MenuButton returnButton;
    private final SaveButton s1;
    private final SaveButton s2;
    private final SaveButton s3;
    private final JButton d1;
    private final JButton d2;
    private final JButton d3;

    public SavesPanel() {

        super(new GridLayout(2, 1));

        this.topPanel = new JPanel(new GridLayout(1, 3));
        this.choicePanel = new JPanel(new GridLayout(1, 2));
        this.choicePlayPanel = new JPanel(new GridLayout(0, 1));
        this.choiceDeletePanel = new JPanel(new GridLayout(0, 1));

        this.add(topPanel);
        this.add(choicePanel);

        this.choicePanel.add(choicePlayPanel);
        this.choicePanel.add(choiceDeletePanel);

        this.setBackground(Constants.BLUE);
        this.topPanel.setBackground(Constants.BLUE);
        this.choicePanel.setBackground(Constants.BLUE);
        this.choicePlayPanel.setBackground(Constants.BLUE);
        this.choiceDeletePanel.setBackground(Constants.BLUE);

        this.returnButton = new MenuButton("<-  " + LangFile.getLangFile().getString("menu.back"));
        this.topPanel.add(this.returnButton);

        this.s1 = new SaveButton("Save 1");
        this.s2 = new SaveButton("");
        this.s3 = new SaveButton("");

        this.d1 = new JButton("Suppr save1");
        this.d2 = new JButton("Suppr save2");
        this.d3 = new JButton("Suppr save3");

        JLabel title = new JLabel(LangFile.getLangFile().getString("menu.saves"));
        title.setHorizontalAlignment(JLabel.CENTER);
        title.setFont(Constants.TITLE_FONT);
        this.topPanel.add(title);
        this.topPanel.add(new JLabel());
        this.choicePlayPanel.add(s1);
        this.choicePlayPanel.add(s2);
        this.choicePlayPanel.add(s3);

        this.choiceDeletePanel.add(d1);
        this.choiceDeletePanel.add(d2);
        this.choiceDeletePanel.add(d3);
    }

    public JButton getReturnButton() { return this.returnButton; }

    public JButton getTmpButton() {
        return s1;
    }
}