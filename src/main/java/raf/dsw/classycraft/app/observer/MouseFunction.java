package raf.dsw.classycraft.app.observer;

import raf.dsw.classycraft.app.gui.swing.desktop.view.DijagramView;
import raf.dsw.classycraft.app.gui.swing.desktop.view.PackageView;

import java.awt.*;

public interface MouseFunction {
    void handleEvent(Point e, DijagramView dijagramView);
}
