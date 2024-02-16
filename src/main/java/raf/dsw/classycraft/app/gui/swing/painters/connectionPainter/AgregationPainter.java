package raf.dsw.classycraft.app.gui.swing.painters.connectionPainter;

import raf.dsw.classycraft.app.gui.swing.model.connection.Agregation;
import raf.dsw.classycraft.app.gui.swing.painters.ConnectionPainter;
import raf.dsw.classycraft.app.gui.swing.model.DijagramElement;

import java.awt.*;
import java.awt.geom.Path2D;
import java.awt.geom.Rectangle2D;

public class AgregationPainter extends ConnectionPainter {
    public AgregationPainter(DijagramElement element) {
        super(element);
    }

    @Override
    public void draw(Graphics g) {
        Agregation a = (Agregation) super.getElement();
        a.recalculateCoordinates();
        Graphics2D g2D = (Graphics2D)g;


        Point newP1 = new Point((int) a.getXFrom(), (int) a.getYFrom());
        Point newP2 = new Point((int) a.getXTo(), (int) a.getYTo());
        drawAggregationArrow(g2D, newP1, newP2);
        if(getSelected()){
            Rectangle2D rectangle = getShape().getBounds2D();
            g2D.setColor(Color.CYAN);
            g2D.draw(rectangle);
        }
    }


    private void drawAggregationArrow(Graphics2D g2d, Point start, Point end) {
        int arrowSize = 10;

        // Draw the arrow line
        g2d.setColor(Color.BLACK);
        g2d.setStroke(new BasicStroke(2));
        g2d.drawLine(start.x, start.y, end.x, end.y);

        // Calculate the angle of the line
        double angle = Math.atan2(end.y - start.y, end.x - start.x);

        // Draw the arrowhead (diamond shape)
        Path2D arrowhead = new Path2D.Double();
        arrowhead.moveTo(end.x, end.y);
        arrowhead.lineTo(
                end.x - arrowSize * Math.cos(angle - Math.PI / 6),
                end.y - arrowSize * Math.sin(angle - Math.PI / 6)
        );
        arrowhead.lineTo(
                end.x - arrowSize * Math.cos(angle + Math.PI / 6),
                end.y - arrowSize * Math.sin(angle + Math.PI / 6)
        );
        arrowhead.closePath();

        g2d.fill(arrowhead);
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
