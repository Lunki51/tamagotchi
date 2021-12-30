package fr.tama.model;

import java.awt.*;

public class Constants {
    public final static double version = 1.0;
    public static final Color LIGHT_PURPLE =  new Color(170,30,165);
    public static final Color BLUE = new Color(76,156,152);
    public final static Color PURPLE = new Color(130,3,120);
    public final static Color DARK_PURPLE = ColorShift(PURPLE, -45);
    public final static Font BASIC_FONT = new Font(Font.SANS_SERIF,  Font.BOLD, 20);
    public final static Font TITLE_FONT = new Font(Font.SANS_SERIF,  Font.BOLD, 30);

    public static Color ColorShift(Color c, int shift)
    {
        int[] cv = new int[]{c.getRed(), c.getGreen(), c.getBlue()};
        
        for(int i = 0; i < cv.length; i++)
        {
            cv[i] += shift;
            if(cv[i] > 255)
                cv[i] = 255;
            else if(cv[i] < 0)
                cv[i] = 0;
        }

        return new Color(cv[0], cv[1], cv[2]);
    }
}
