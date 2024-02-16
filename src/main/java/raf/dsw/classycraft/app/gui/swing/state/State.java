package raf.dsw.classycraft.app.gui.swing.state;

import raf.dsw.classycraft.app.gui.swing.desktop.view.DijagramView;
import raf.dsw.classycraft.app.gui.swing.desktop.view.PackageView;

import java.awt.*;

public abstract class State {
    public abstract void misKliknut(Point e, DijagramView dijagramView, PackageView packageView);
    public abstract void misOtpusten(Point e, DijagramView dijagramView, PackageView packageView);
    public abstract void misPovucen(Point e, DijagramView dijagramView, PackageView packageView);
    public abstract void misPritisnut(Point e, DijagramView dijagramView, PackageView packageView);

}
