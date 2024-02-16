package raf.dsw.classycraft.app.gui.swing.painters;

import lombok.Getter;
import raf.dsw.classycraft.app.gui.swing.model.InterClass;

import java.awt.*;
import java.awt.geom.AffineTransform;

public class TempArrowPainter extends ElementPainter
{
    @Getter
    private int fromX, fromY;
    private int toX, toY;
    public TempArrowPainter(InterClass element, int x, int y) {
        super(element);
        this.fromX = x;
        this.fromY = y;
    }


//    @Override
//    public Rectangle getBoundingBox() {
//
//        return new Rectangle(end,y,Math.abs(endX - x),Math.abs(endY - y));
//    }

    public void updateEndPos(int x, int y)
    {
        this.toX = x;
        this.toY = y;
    }
//    @Override
//    public void applyTransformToPoints(AffineTransform transform)
//    {
//        super.applyTransformToPoints(transform);
//        var point = new Point(fromX, fromY);
//        transform.transform(point, point);
//        fromX = point.x;
//        fromY = point.y;
//    }

    @Override
    public void draw(Graphics g) {
        // Draw dashed gray line
//        Stroke dashed = new BasicStroke(3, BasicStroke.CAP_BUTT, BasicStroke.JOIN_BEVEL, 0, new float[]{9}, 0);
//        g.setStroke(dashed);
        g.setColor(Color.GRAY);
        g.drawLine(toX, toY, fromX, fromY);
    }

    @Override
    public boolean elementAt(int x, int y) {
        return false;
    }
}
