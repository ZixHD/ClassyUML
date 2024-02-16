package raf.dsw.classycraft.app.gui.swing.painters;

import raf.dsw.classycraft.app.gui.swing.model.DijagramElement;
import raf.dsw.classycraft.app.gui.swing.model.InterClass;
import raf.dsw.classycraft.app.gui.swing.model.interClass.Interface;
import raf.dsw.classycraft.app.gui.swing.model.interClass.Method;

import java.awt.*;
import java.awt.geom.Rectangle2D;
import java.util.ArrayList;

public class InterfacePainter extends ElementPainter {

    public InterfacePainter(DijagramElement element) {
        super(element);
    }

    @Override
    public void draw(Graphics g) {
        Interface inter = (Interface) super.getElement();
        Graphics2D g2D = (Graphics2D) g;
        FontMetrics fm = g2D.getFontMetrics();

        int sideLength = Math.max(fm.stringWidth(inter.getName()) + 20, 100);
        inter.setWidthAndHeight(sideLength, sideLength);

        setShape(new Rectangle2D.Double(inter.getX(), inter.getY(), sideLength, sideLength));

        g2D.setStroke(new BasicStroke(inter.getStrokeWidth())); // Line thickness
        g2D.setColor(new Color(inter.getColourInside())); // Fill color

        g2D.fill(getShape());

        g2D.setColor(Color.GREEN); // Outline color
        g2D.draw(getShape());

        // Display the class name inside the square
        double xString = inter.getX() + ((sideLength - fm.stringWidth(inter.getName())) / 2);
        double yString = inter.getY() + fm.getAscent(); // Adjusted for the top
        g2D.drawString(inter.getName(), (float) xString, (float) yString);


        ArrayList<Method> methods = (ArrayList<Method>) inter.getMetode();

        int methodFontSize = 12; // Set the font size for methods
        int methodIndentation = 12;

        g2D.setColor(Color.BLACK);
// Draw methods below the class name
        for (int i = 0; i < methods.size(); i++) {
            Method method = methods.get(i);
            double yMethod = yString + (i + 1) * methodFontSize; // Adjust the position for each method
            double xMethod = xString - methodIndentation;
            g2D.drawString(method.toString(), (float) xMethod, (float) yMethod);
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
    }
}
