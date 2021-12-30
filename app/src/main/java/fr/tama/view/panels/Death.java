package fr.tama.view.panels;

import fr.tama.view.utils.Updatable;

import javax.swing.*;
import java.awt.*;

public class Death extends JPanel implements Updatable {

    public Death() {
        this.add(new Label("T MOR"));
    }

    @Override
    public void updatePanel() {

    }
}
