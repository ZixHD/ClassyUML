package raf.dsw.classycraft.app.gui.swing.state;

import raf.dsw.classycraft.app.gui.swing.desktop.view.DijagramView;
import raf.dsw.classycraft.app.gui.swing.desktop.view.PackageView;
import raf.dsw.classycraft.app.gui.swing.painters.ElementPainter;
import raf.dsw.classycraft.app.gui.swing.painters.LassoPainter;

import java.awt.*;
import java.awt.geom.Rectangle2D;
import java.util.ArrayList;

public class SelectState extends State{

    private Point mousePress;
    private LassoPainter lassoPainter;
    @Override
    public void misKliknut(Point e, DijagramView dijagramView, PackageView packageView) {
        //NE KORISTIMO
    }

    @Override
    public void misPritisnut(Point e, DijagramView dijagramView, PackageView packageView) {
        mousePress = e;
        lassoPainter = new LassoPainter(e.x,e.y);
        dijagramView.getPainters().add(lassoPainter);
    }

    @Override
    public void misPovucen(Point e, DijagramView dijagramView, PackageView packageView) {
        lassoPainter.updateCoordinates(mousePress.x,mousePress.y,e.x,e.y);
        dijagramView.repaint();
    }

    @Override
    public void misOtpusten(Point e, DijagramView dijagramView, PackageView packageView) {
        dijagramView.getPainters().remove(lassoPainter);
        ArrayList<ElementPainter> currPainters = (ArrayList<ElementPainter>) dijagramView.getPainters();
        ArrayList<ElementPainter> temp = new ArrayList<>();
        for(ElementPainter painter: currPainters){
            if(painter.getShape().intersects((Rectangle2D) lassoPainter.getShape())){
                temp.add(painter);
                painter.setSelected(true);
            }else{
                painter.setSelected(false);
            }
        }

//        dijagramView.getPainters().addAll(temp);
        dijagramView.getSelectedPainters().addAll(temp);

        dijagramView.getPainters().remove(lassoPainter);
        System.out.println(dijagramView.getSelectedPainters());
        lassoPainter = null;
    }
}