package raf.dsw.classycraft.app.gui.swing.state;

import raf.dsw.classycraft.app.gui.swing.desktop.view.DijagramView;
import raf.dsw.classycraft.app.gui.swing.desktop.view.PackageView;
import raf.dsw.classycraft.app.gui.swing.model.InterClass;
import raf.dsw.classycraft.app.gui.swing.painters.ElementPainter;

import java.awt.*;
import java.awt.geom.AffineTransform;

public class PanState extends State{

    private Point startPoint;
    @Override
    public void misKliknut(Point e, DijagramView dijagramView, PackageView packageView) {

    }

    @Override
    public void misOtpusten(Point e, DijagramView dijagramVieww, PackageView packageView) {
        startPoint = null;
    }

    @Override
    public void misPovucen(Point e, DijagramView dijagramView, PackageView packageView) {
        if (startPoint == null)
            return;

        int dx = e.x - startPoint.x;
        int dy = e.y - startPoint.y;
        var transform = AffineTransform.getTranslateInstance(dx, dy);
        System.out.println(dijagramView.getPainters());
        for (ElementPainter painter : dijagramView.getPainters()) {
            InterClass klasa = (InterClass) painter.getElement();
            Point point = new Point(klasa.getX(), klasa.getY());
            transform.transform(point, point);

            // Update the x and y coordinates based on the transformed point
            klasa.setX(point.x);
            klasa.setY(point.y);
        }

        startPoint = new Point(e.x, e.y);
        dijagramView.repaint();
    }



    @Override
    public void misPritisnut(Point e, DijagramView dijagramView, PackageView packageView) {
        startPoint = new Point(e.x, e.y);
    }
}