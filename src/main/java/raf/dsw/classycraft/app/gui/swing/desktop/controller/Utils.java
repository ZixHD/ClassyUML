package raf.dsw.classycraft.app.gui.swing.desktop.controller;

import java.awt.*;

public class Utils {
    public static boolean rectanglesOverlap(Rectangle r1, Rectangle r2)
    {
        int x1 = r1.x, y1 = r1.y, w1 = r1.width, h1 = r1.height;
        int x2 = r2.x, y2 = r2.y, w2 = r2.width, h2 = r2.height;
        int bottom1 = y1 + h1, bottom2 = y2 + h2;
        int right1 = x1 + w1, right2 = x2 + w2;

        if (bottom1 < y2)
            return false;
        if(y1 > bottom2)
            return false;
        if (right1 < x2)
            return false;
        if (x1 > right2)
            return false;

        return true;
    }

}
