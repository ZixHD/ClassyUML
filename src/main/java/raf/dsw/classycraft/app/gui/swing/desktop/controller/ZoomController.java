package raf.dsw.classycraft.app.gui.swing.desktop.controller;
import raf.dsw.classycraft.app.gui.swing.desktop.view.DijagramView;

import java.awt.*;
import java.awt.geom.AffineTransform;
import java.awt.geom.NoninvertibleTransformException;

public class ZoomController {
    private AffineTransform currentTransform;
    public ZoomController()
    {
        currentTransform = new AffineTransform();
    }
    public void handleClick(int x, int y, DijagramView diagramView, double scaleFactor)
    {
        var cx = diagramView.getWidth() / 2;    // Center of screen x
        var cy = diagramView.getHeight() / 2;   // Center of screen y
        // Move (x,y) to center of screen
        var dx = cx - x;
        var dy = cy - y;
        var translateTransform = new AffineTransform();
        translateTransform.translate(dx, dy);

        var zoomTransform = new AffineTransform();
        // Scale around (cx,cy), note that java combines transforms in reverse order
        // Translate back to (cx,cy)
        zoomTransform.translate(cx, cy);
        // Scale around (0,0)
        zoomTransform.scale(scaleFactor, scaleFactor);
        // Translate to (cx,cy)
        zoomTransform.translate(-cx, -cy);
        // Combine with translate transform
        zoomTransform.concatenate(translateTransform);

        zoomTransform.concatenate(currentTransform);
        currentTransform = zoomTransform;
    }
    public Point getOriginalPoint(Point p) {
        var inverseTransform = new AffineTransform();
        try {
            inverseTransform = currentTransform.createInverse();
        } catch (NoninvertibleTransformException e) {
            return null;
        }

        var point = new Point(p.x, p.y);
        inverseTransform.transform(point, point);
        return point;
    }
    public void applyTransform(Graphics2D g)
    {
        g.transform(currentTransform);
    }
    public void resetTransform()
    {
        currentTransform = new AffineTransform();
    }

}