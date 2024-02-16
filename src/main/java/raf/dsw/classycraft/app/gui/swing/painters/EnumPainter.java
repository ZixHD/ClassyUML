package raf.dsw.classycraft.app.gui.swing.painters;

import raf.dsw.classycraft.app.gui.swing.model.DijagramElement;
import raf.dsw.classycraft.app.gui.swing.model.InterClass;
import raf.dsw.classycraft.app.gui.swing.model.interClass.Enum;
import raf.dsw.classycraft.app.gui.swing.model.interClass.Method;

import java.awt.*;
import java.awt.geom.Rectangle2D;
import java.util.ArrayList;

public class EnumPainter extends ElementPainter{

    public EnumPainter(DijagramElement element) {
        super(element);
    }

    @Override
    public void draw(Graphics g) {
        Enum en = (Enum) super.getElement();
        Graphics2D g2D = (Graphics2D) g;
        FontMetrics fm = g2D.getFontMetrics();

        int sideLength = Math.max(fm.stringWidth(en.getName()) + 20,100);
        en.setWidthAndHeight(sideLength, sideLength);

        setShape(new Rectangle2D.Double(en.getX(), en.getY(), sideLength, sideLength));

        g2D.setStroke(new BasicStroke(en.getStrokeWidth())); // Line thickness
        g2D.setColor(new Color(en.getColourInside())); // Fill color

        g2D.fill(getShape());

        g2D.setColor(Color.ORANGE); // Outline color
        g2D.draw(getShape());
        System.out.println("Koordinate su: " + en.getX() + ", " + en.getY());
        // Display the class name inside the square
        double xString = en.getX() + ((sideLength - fm.stringWidth(en.getName())) / 2);
        double yString = en.getY() + fm.getAscent(); // Adjusted for the top
        g2D.drawString(en.getName(), (float) xString, (float) yString);

        ArrayList<String> enums = (ArrayList<String>) en.getListaEnuma();
        int methodFontSize = 12; // Set the font size for methods
        int methodIdentation = 15;
        g2D.setColor(Color.BLACK);
// Draw methods below the class name
        for (int i = 0; i < enums.size(); i++) {

            String enuum = enums.get(i);
            double yEnum = yString + (i + 1) * methodFontSize; // Adjust the position for each method
            double xEnum = xString - methodIdentation;
            g2D.drawString(enuum, (float) xEnum, (float) yEnum);
        }
        // Draw a selection rectangle if the square is selected
        if (getSelected()) {
            Rectangle2D rectangle = getShape().getBounds2D();
            g2D.setColor(Color.CYAN);
            g2D.draw(rectangle);
        }
    }

    @Override
    public boolean elementAt(int x, int y) {
        return getShape().contains(x, y);
    }}
