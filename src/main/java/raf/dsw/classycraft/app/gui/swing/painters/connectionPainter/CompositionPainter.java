package raf.dsw.classycraft.app.gui.swing.painters.connectionPainter;

import raf.dsw.classycraft.app.gui.swing.model.connection.Composition;
import raf.dsw.classycraft.app.gui.swing.painters.ConnectionPainter;
import raf.dsw.classycraft.app.gui.swing.model.DijagramElement;

import java.awt.*;
import java.awt.geom.Line2D;
import java.awt.geom.Rectangle2D;

public class CompositionPainter extends ConnectionPainter {
    public CompositionPainter(DijagramElement element) {
        super(element);
    }

    @Override
    public void draw(Graphics g) {
        Composition a = (Composition) super.getElement();
        a.recalculateCoordinates();
        Graphics2D g2D = (Graphics2D)g;
        Line2D.Double line = new Line2D.Double(a.getXFrom(), a.getYFrom(), a.getXTo(), a.getYTo());
        setShape(line);

        g2D.setStroke(new BasicStroke(a.getStrokeWidth()));
        g2D.setColor(new Color(a.getColourOutline()));

        g2D.draw(getShape());
        drawCompositionSymbol(g2D, a.getXTo(), a.getYTo(), 50);
        if(getSelected()){
            Rectangle2D rectangle = getShape().getBounds2D();
            g2D.setColor(Color.CYAN);
            g2D.draw(rectangle);
        }
    }

    private void drawCompositionSymbol(Graphics2D g2D, double x, double y, int size) {

        int[] xPoints = {(int) x, (int) (x - size), (int) x, (int) (x + size)};
        int[] yPoints = {(int) y, (int) (y + size), (int) (y + 2 * size), (int) (y + size)};

        double midX = (xPoints[0] + xPoints[1]) / 2.0;
        double midY = (yPoints[0] + yPoints[1]) / 2.0;

        // Adjust starting point for drawing the rhomboid
        int[] adjustedXPoints = {(int) midX, (int) (midX    - size), (int) midX, (int) (midX + size)};
        int[] adjustedYPoints = {(int) midY, (int) (midY + size), (int) (midY + 2 * size), (int) (midY + size)};

        Polygon rhomboid = new Polygon(adjustedXPoints, adjustedYPoints, 4);
        g2D.setColor(Color.BLACK); // Set to a visible color
        g2D.fill(rhomboid);
        g2D.setColor(Color.BLACK);
        g2D.draw(rhomboid);
    }

    @Override
    public boolean elementAt(int x, int y) {
        if(getShape() == null) return false;
        return getShape().getBounds2D().contains(x, y);
    }

    @Override
    public boolean equals(Object obj) {
        if(!(obj instanceof ConnectionPainter)) return false;
        ConnectionPainter ap = (ConnectionPainter) obj;
        return this.getElement().equals(ap.getElement());
    }
}
