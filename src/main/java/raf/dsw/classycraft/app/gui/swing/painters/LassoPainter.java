package raf.dsw.classycraft.app.gui.swing.painters;

import raf.dsw.classycraft.app.gui.swing.model.DijagramElement;
import raf.dsw.classycraft.app.gui.swing.model.InterClass;

import java.awt.*;
import java.awt.geom.Rectangle2D;

public class LassoPainter extends ElementPainter{
    private double x1, y1;
    private double x2, y2;

    public LassoPainter(double x1, double y1) {
        super(null);
        this.x1 = x1;
        this.y1 = y1;
        setShape(new Rectangle2D.Double(this.x1, this.y1, 1, 1));
    }

    @Override
    public void draw(Graphics g) {
        Graphics2D g2D = (Graphics2D)g;

        float dash1[] = {10.0f};
        g2D.setStroke(new BasicStroke(2, BasicStroke.CAP_BUTT, BasicStroke.JOIN_MITER, 10.0f, dash1, 0.0f));
        g2D.setColor(Color.BLACK);
        g2D.draw(getShape());

        Rectangle2D square = (Rectangle2D) getShape(); // Adjust coordinates and size as needed

        g2D.draw(square);


    }

    @Override
    public boolean elementAt(int x, int y) {
        return getShape().contains(x, y);
    }

    public void updateCoordinates(double x1, double y1, double x2, double y2){
        this.x1 = Math.min(x1, x2);
        this.y1 = Math.min(y1, y2);
        this.x2 = Math.max(x1, x2);
        this.y2 = Math.max(y1, y2);
        setShape(new Rectangle2D.Double(this.x1, this.y1, this.x2-this.x1, this.y2-this.y1));
    }

    @Override
    public boolean equals(Object obj) {
        if(!(obj instanceof LassoPainter)) return false;
        LassoPainter lp = (LassoPainter) obj;
        return lp.x1 == x1 && lp.x2 == x2 && lp.y1 == y1 && lp.y2 == y2;
    }
}