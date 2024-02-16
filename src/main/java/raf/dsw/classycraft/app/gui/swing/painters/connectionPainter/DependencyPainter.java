package raf.dsw.classycraft.app.gui.swing.painters.connectionPainter;

import raf.dsw.classycraft.app.gui.swing.model.connection.Dependency;
import raf.dsw.classycraft.app.gui.swing.painters.ConnectionPainter;
import raf.dsw.classycraft.app.gui.swing.model.DijagramElement;

import java.awt.*;
import java.awt.geom.Line2D;
import java.awt.geom.Rectangle2D;

public class DependencyPainter extends ConnectionPainter {

    public DependencyPainter(DijagramElement element) {
        super(element);
    }

    @Override
    public void draw(Graphics g) {
        Dependency dependency = (Dependency) super.getElement();
        dependency.recalculateCoordinates();
        Graphics2D g2D = (Graphics2D) g;

        Line2D.Double line = new Line2D.Double(dependency.getXFrom(), dependency.getYFrom(), dependency.getXTo(), dependency.getYTo());
        setShape(line);

        float[] dashPattern = {5f, 5f}; // Adjust the pattern as needed
        g2D.setStroke(new BasicStroke(dependency.getStrokeWidth(), BasicStroke.CAP_BUTT, BasicStroke.JOIN_MITER, 10.0f, dashPattern, 0.0f));
        g2D.setColor(new Color(dependency.getColourOutline()));

        g2D.draw(getShape());

        if (getSelected()) {
            Rectangle2D rectangle = getShape().getBounds2D();
            g2D.setColor(Color.CYAN);
            g2D.draw(rectangle);
        }
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

