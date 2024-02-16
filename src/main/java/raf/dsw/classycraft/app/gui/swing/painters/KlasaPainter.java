package raf.dsw.classycraft.app.gui.swing.painters;

import raf.dsw.classycraft.app.gui.swing.model.DijagramElement;
import raf.dsw.classycraft.app.gui.swing.model.InterClass;
import raf.dsw.classycraft.app.gui.swing.model.interClass.Atribut;
import raf.dsw.classycraft.app.gui.swing.model.interClass.ClassContent;
import raf.dsw.classycraft.app.gui.swing.model.interClass.Klasa;
import raf.dsw.classycraft.app.gui.swing.model.interClass.Method;

import java.awt.*;
import java.awt.geom.Rectangle2D;
import java.util.ArrayList;

public class KlasaPainter extends ElementPainter {
    public KlasaPainter(DijagramElement element) {
        super(element);
        System.out.println("InterClassssssss");
    }

    @Override
    public void draw(Graphics g) {
       Klasa klasa = (Klasa) super.getElement();
        Graphics2D g2D = (Graphics2D) g;
        FontMetrics fm = g2D.getFontMetrics();

        int sideLength = Math.max(fm.stringWidth(klasa.getName()) + 20, 100);
        klasa.setWidthAndHeight(sideLength, sideLength);

        setShape(new Rectangle2D.Double(klasa.getX(), klasa.getY(), sideLength, sideLength));

        g2D.setStroke(new BasicStroke(klasa.getStrokeWidth())); // Line thickness
        g2D.setColor(new Color(klasa.getColourInside())); // Fill color

        g2D.fill(getShape());

        g2D.setColor(Color.RED); // Outline color
        g2D.draw(getShape());

        // Display the class name inside the square
        double xString = klasa.getX() + ((sideLength - fm.stringWidth(klasa.getName())) / 2);
        double yString = klasa.getY() + fm.getAscent(); // Adjusted for the top
        g2D.drawString(klasa.getName(), (float) xString, (float) yString);

        ArrayList<ClassContent> listaContenta = (ArrayList<ClassContent>) klasa.getListaContenta();

        int methodFontSize = 12; // Set the font size for methods
        int methodIndentation = 15;
        int methodSpacing = 15;

        g2D.setColor(Color.BLACK);
// Draw methods below the class name
        for (int i = 0; i < listaContenta.size(); i++) {
            ClassContent sadrzaj = listaContenta.get(i);

            if (sadrzaj instanceof Atribut) {
                double yAtribut = yString + (i + 1) * methodFontSize; // Adjust the position for each method
                double xAtribut = xString - methodIndentation;
                g2D.drawString(sadrzaj.toString(), (float) xAtribut, (float) yAtribut);
            } else if (sadrzaj instanceof Method) {
                double yMethod = yString + (i + 1) * methodFontSize; // Adjust the position for each method
                double xMethod = xString - methodIndentation;
                g2D.drawString(sadrzaj.toString(), (float) xMethod, (float) yMethod);
            }
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
