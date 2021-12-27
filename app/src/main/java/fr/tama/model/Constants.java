package fr.tama.model;

import java.awt.*;

public class Constants {
    public final static Color BLUE = new Color(126,206,202);
    //public final static Color DARKBLUE = ColorShift(BLUE, -30);
    public final static Color PURPLE = new Color(130,3,120);
    public final static Font BASIC_FONT = new Font(Font.SANS_SERIF,  Font.BOLD, 20);
    public final static Font TITLE_FONT = new Font(Font.SANS_SERIF,  Font.BOLD, 30);

    /*public static Color ColorShift(Color c, int shift)
    {
        float[] v = Color.RGBtoHSB(c.getRed(), c.getGreen(), c.getBlue(), null);
        v[2] += shift/255f;
        if(v[2] < 0)
            v[2] = 0;
        else if(v[2] > 1)
            v[2] = 1;
        return new Color(Color.HSBtoRGB(v[0], v[1], v[2]));
    }*/
}
