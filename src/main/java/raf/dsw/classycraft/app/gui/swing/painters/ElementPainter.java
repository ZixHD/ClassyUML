package raf.dsw.classycraft.app.gui.swing.painters;

import lombok.Getter;
import lombok.Setter;
import raf.dsw.classycraft.app.gui.swing.model.DijagramElement;
import raf.dsw.classycraft.app.gui.swing.model.InterClass;

import java.awt.*;
import java.awt.geom.AffineTransform;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Rectangle2D;
@Getter
@Setter
public abstract class ElementPainter {
    private DijagramElement element;
    private Shape shape;
    private Boolean selected;

    private boolean checked = false;

    private int graflevel = 0;


    protected ElementPainter(DijagramElement element) {
        this.element = element;
        this.selected = false;
    }
    //public abstract Rectangle getBoundingRectangle(ElementPainter p);

    public abstract void draw(Graphics g);
    public abstract boolean elementAt(int x, int y);

    public void setSelected(Boolean selected) {
        this.selected = selected;
        element.setSelected(selected);
    }

    public void setChecked(boolean checked) {
        this.checked = checked;
    }


//    protected void applyTransformToPoints(AffineTransform transform) {
//        var point = new Point(x, y);
//        transform.transform(point, point);
//        x = point.x;
//        y = point.y;
//    }
}
