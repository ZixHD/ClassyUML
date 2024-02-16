package raf.dsw.classycraft.app.gui.swing.tree.controller;

import raf.dsw.classycraft.app.gui.swing.tree.model.ClassyTreeItem;
import raf.dsw.classycraft.app.gui.swing.view.MainFrame;
import raf.dsw.classycraft.app.gui.swing.desktop.view.PackageView;
import raf.dsw.classycraft.app.repository.composite.ClassyNode;
import raf.dsw.classycraft.app.repository.composite.ClassyNodeComposite;
import raf.dsw.classycraft.app.repository.implementation.Package;

import javax.swing.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class MouseEventDemo implements MouseListener {
    private JTree myTree;

    public MouseEventDemo(JTree tree){
        this.myTree = tree;
    }
    @Override
    public void mouseClicked(MouseEvent e) {

    }

    @Override
    public void mousePressed(MouseEvent e) {
        int selRow = myTree.getRowForLocation(e.getX(), e.getY());              //pozicija selektovanog u tree
        if(selRow != -1 && e.getClickCount() == 2){
            System.out.println("Dupli Klik!");
            ClassyTreeItem selectedWrapper = (ClassyTreeItem) myTree.getLastSelectedPathComponent();
            ClassyNodeComposite selectedNode = (ClassyNodeComposite) selectedWrapper.getClassyNode();

            //ako je dvoklik na package -> stvaramo ProjectView (sa JTabbedPane) sa desne strane

            PackageView pv = MainFrame.getInstance().getDesktopPanel();
            if(selectedNode instanceof Package){
                pv.init(selectedNode);
            }
            SwingUtilities.updateComponentTreeUI(MainFrame.getInstance());
        }
    }

    @Override
    public void mouseReleased(MouseEvent e) {

    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }
}
