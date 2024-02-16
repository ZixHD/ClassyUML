package raf.dsw.classycraft.app.gui.swing.desktop.controller;

import raf.dsw.classycraft.app.gui.swing.desktop.view.DijagramView;
import raf.dsw.classycraft.app.gui.swing.desktop.view.PackageView;
import raf.dsw.classycraft.app.gui.swing.state.ZoomInState;
import raf.dsw.classycraft.app.gui.swing.state.ZoomOutState;
import raf.dsw.classycraft.app.gui.swing.view.MainFrame;
import raf.dsw.classycraft.app.observer.MouseFunction;

import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.geom.AffineTransform;
import java.awt.geom.NoninvertibleTransformException;
import java.awt.geom.Point2D;

public class MouseController implements MouseListener, MouseMotionListener {

        private PackageView packageView;

        private Point getWorldCoordinates(MouseEvent me){
            packageView = MainFrame.getInstance().getDesktopPanel();
            DijagramView currMapView = (DijagramView) packageView.getTabbedPane().getSelectedComponent();

            AffineTransform atInvert = null;
            try {
                atInvert = currMapView.getAffineTransform().createInverse();
            } catch(NoninvertibleTransformException | NullPointerException exception){
                System.err.print("Non invertible transformation");
            }

            Point2D pDest = atInvert.transform(new Point2D.Double(me.getX(), me.getY()), null);
            Point pDest2 = new Point();
            pDest2.x = (int) pDest.getX();
            pDest2.y = (int) pDest.getY();
            return pDest2;
        }
        @Override
        public void mousePressed(MouseEvent e) {
            Point point = getWorldCoordinates(e);
            packageView = MainFrame.getInstance().getDesktopPanel();
            System.out.println(e.getX() + " " + e.getY());
            packageView.getStateManager().getCurrState().misPritisnut(point, (DijagramView)packageView.getTabbedPane().getSelectedComponent(),packageView);

        }

        @Override
        public void mouseDragged(MouseEvent e) {
            Point point = getWorldCoordinates(e);
            packageView = MainFrame.getInstance().getDesktopPanel();
            //System.out.println("Mouse Dragged");
            packageView.getStateManager().getCurrState().misPovucen(point, (DijagramView)packageView.getTabbedPane().getSelectedComponent(),packageView);
        }

    @Override
    public void mouseMoved(MouseEvent e) {

    }

    @Override
        public void mouseReleased(MouseEvent e) {
            Point point = getWorldCoordinates(e);
            packageView = MainFrame.getInstance().getDesktopPanel();
            packageView.getStateManager().getCurrState().misOtpusten(point, (DijagramView)packageView.getTabbedPane().getSelectedComponent(),packageView);
        }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }

    public void mouseClicked(MouseEvent e) {
            Point point = getWorldCoordinates(e);
            packageView = MainFrame.getInstance().getDesktopPanel();
            packageView.getStateManager().getCurrState().misKliknut(point, (DijagramView)packageView.getTabbedPane().getSelectedComponent(),packageView);
        }
}
