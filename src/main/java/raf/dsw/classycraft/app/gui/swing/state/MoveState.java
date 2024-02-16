package raf.dsw.classycraft.app.gui.swing.state;

import raf.dsw.classycraft.app.gui.swing.desktop.view.DijagramView;
import raf.dsw.classycraft.app.gui.swing.desktop.view.PackageView;
import raf.dsw.classycraft.app.gui.swing.model.InterClass;
import raf.dsw.classycraft.app.gui.swing.painters.ElementPainter;
import raf.dsw.classycraft.app.gui.swing.painters.KlasaPainter;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class MoveState extends State{

    private Point startPoint = null;
    private Point prevDragPosition = null;
    private Boolean movingComponents = false;
    @Override
    public void misKliknut(Point e, DijagramView dijagramView, PackageView packageView) {

    }

    @Override
    public void misPritisnut(Point e, DijagramView dijagramView, PackageView packageView) {

        startPoint = e;
        prevDragPosition = startPoint;
        if(!dijagramView.getSelectedPainters().isEmpty())
            movingComponents = true;
    }

    @Override
    public void misOtpusten(Point e, DijagramView dijagramView, PackageView packageView) {
        doMove(dijagramView, e.x, e.y);
        movingComponents = false;
        startPoint = null;
        prevDragPosition = null;
    }
    @Override
    public void misPovucen(Point e, DijagramView dijagramView, PackageView packageView) {
        DijagramView currMapView = dijagramView;

        //Pomeramo mapu (krecemo se po njoj)
        if(!movingComponents){
            currMapView.scroll(e.getX() - startPoint.getX(), e.getY() - startPoint.getY());
            return;
        }

        //Pomeramo selektovane elemente
        for(ElementPainter p : dijagramView.getSelectedPainters()){
            if(p instanceof KlasaPainter){
                InterClass topicBeingMoved = (InterClass) p.getElement();
                double newXCoordinate = topicBeingMoved.getX() + e.getX() - prevDragPosition.getX();
                double newYCoordinate = topicBeingMoved.getY() + e.getY() - prevDragPosition.getY();

                //pojam ne sme da izadje izvan okvira mape
                double workspaceHeight = currMapView.getSize().getHeight();
                double workspaceWidth = currMapView.getSize().getWidth();
                if(newXCoordinate < 0) newXCoordinate = 0;
                if(newYCoordinate < 0) newYCoordinate = 0;
                if(newXCoordinate > workspaceWidth * 5 - topicBeingMoved.getWidth()) newXCoordinate = workspaceWidth * 5 - topicBeingMoved.getWidth();
                if(newYCoordinate > workspaceHeight * 5 - topicBeingMoved.getWidth()) newYCoordinate = workspaceHeight * 5 - topicBeingMoved.getWidth();
                topicBeingMoved.setX((int)newXCoordinate);
                topicBeingMoved.setY((int)newYCoordinate);
            }
        }
        prevDragPosition = e;
    }

    private void deselectAll(DijagramView diagramView)
    {
        for(var painter: diagramView.getPainters())
            painter.setSelected(false);
    }

    public void doMove(DijagramView dv, int x, int y){
        List<ElementPainter> lista = new ArrayList<ElementPainter>();
        for(ElementPainter p : lista){
            InterClass t = (InterClass) p.getElement();
            double newXCoordinate = t.getX() + x;
            double newYCoordinate = t.getY() + y;
            checkCoordinates(t, newXCoordinate, newYCoordinate, dv);
        }
    }
    private void checkCoordinates(InterClass t, double xCoordinate, double yCoordinate, DijagramView dv){
        double workspaceHeight = dv.getSize().getHeight();;
        double workspaceWidth = dv.getSize().getWidth();
        if(xCoordinate < 0) xCoordinate = 0;
        if(yCoordinate < 0) yCoordinate = 0;
        if(xCoordinate > workspaceWidth * 5 - t.getWidth()) xCoordinate = workspaceWidth * 5 - t.getWidth();
        if(yCoordinate > workspaceHeight * 5 - t.getHeight()) yCoordinate = workspaceHeight * 5 - t.getHeight();
        t.setX((int)xCoordinate);
        t.setY((int)yCoordinate);
    }
}