package raf.dsw.classycraft.app.gui.swing.painters;

import raf.dsw.classycraft.app.gui.swing.model.DijagramElement;

import java.awt.*;

public abstract class ConnectionPainter extends ElementPainter{



    protected ConnectionPainter(DijagramElement element) {
        super(element);
    }

    @Override
    public abstract void draw(Graphics g);

    @Override
    public abstract boolean elementAt(int x, int y);

    @Override
    public abstract boolean equals(Object obj);
}
