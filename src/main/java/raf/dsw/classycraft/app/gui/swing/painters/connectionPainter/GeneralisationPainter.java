package raf.dsw.classycraft.app.gui.swing.painters.connectionPainter;

import raf.dsw.classycraft.app.gui.swing.model.connection.Generalisation;
import raf.dsw.classycraft.app.gui.swing.painters.ConnectionPainter;
import raf.dsw.classycraft.app.gui.swing.model.DijagramElement;

import java.awt.*;
import java.awt.geom.Line2D;
import java.awt.geom.Rectangle2D;

public class GeneralisationPainter extends ConnectionPainter {

    public GeneralisationPainter(DijagramElement element) {
        super(element);
    }

    @Override
    public void draw(Graphics g) {
        Generalisation generalization = (Generalisation) super.getElement();
        generalization.recalculateCoordinates();
        Graphics2D g2D = (Graphics2D) g;

        Line2D.Double line = new Line2D.Double(
                generalization.getXFrom(),
                generalization.getYFrom(),
                generalization.getXTo(),
                generalization.getYTo()
        );
        setShape(line);

        g2D.setStroke(new BasicStroke(generalization.getStrokeWidth()));
        g2D.setColor(new Color(generalization.getColourOutline()));

        g2D.draw(getShape());

        // Draw generalization arrowhead at the end of the line
        drawGeneralizationArrowhead(g2D, generalization.getXTo(), generalization.getYTo(), 10); // Adjust the size as needed

        if (getSelected()) {
            Rectangle2D rectangle = getShape().getBounds2D();
            g2D.setColor(Color.CYAN);
            g2D.draw(rectangle);
        }
    }

    private void drawGeneralizationArrowhead(Graphics2D g2D, double x, double y, int size) {
        Polygon arrowhead = new Polygon();
        arrowhead.addPoint((int) x, (int) y);
        arrowhead.addPoint((int) (x - size), (int) (y - size));
        arrowhead.addPoint((int) (x + size), (int) (y - size));

        g2D.setColor(Color.BLACK);
        g2D.fill(arrowhead);
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
