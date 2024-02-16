package raf.dsw.classycraft.app.gui.swing.state;

import raf.dsw.classycraft.app.gui.swing.desktop.view.DijagramView;
import raf.dsw.classycraft.app.gui.swing.desktop.view.PackageView;

import java.awt.*;

public class ZoomInState extends State{
    @Override
    public void misKliknut(Point e, DijagramView dijagramView, PackageView packageView) {
        //dijagramView.getZoomController().handleClick(e.x, e.y,dijagramView,1.20);
        dijagramView.zoomIn();
        dijagramView.repaint();
    }

    @Override
    public void misOtpusten(Point e, DijagramView dijagramVieww, PackageView packageView) {
        //ne
    }

    @Override
    public void misPovucen(Point e, DijagramView dijagramView, PackageView packageView) {
        //ne
    }

    @Override
    public void misPritisnut(Point e, DijagramView dijagramView, PackageView packageView) {
        //ne
    }
}
