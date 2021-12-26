package fr.tama.view;

import fr.tama.controller.LangFile;
import fr.tama.model.Constants;

import javax.swing.*;
import java.awt.*;

public class SavesPanel extends JPanel {

    private final MenuButton backButton;
    private final SaveButton s1;
    private final SaveButton s2;
    private final SaveButton s3;

    public SavesPanel() {

        super(new GridLayout(3, 3));

        setBackground(Constants.BLUE);
        this.backButton = new MenuButton("<-  " + LangFile.getLangFile().getString("menu.back"));
        this.add(backButton);

        this.s1 = new SaveButton();
        this.s2 = new SaveButton();
        this.s3 = new SaveButton();

        JLabel title = new JLabel(LangFile.getLangFile().getString("menu.saves"));
        title.setHorizontalAlignment(JLabel.CENTER);
        title.setFont(Constants.TITLE_FONT);
        this.add(title);
        this.add(new JLabel());
        this.add(s1);
        this.add(s2);
        this.add(s3);
        this.add(new JLabel());
        this.add(new JLabel());
        this.add(new JLabel());
    }

    public JButton getTmpButton() {
        return backButton;
    }
}