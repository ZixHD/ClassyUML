package raf.dsw.classycraft.app.gui.swing.view;

import lombok.Getter;
import lombok.Setter;
import raf.dsw.classycraft.app.gui.swing.desktop.view.DijagramView;
import raf.dsw.classycraft.app.gui.swing.desktop.view.PackageView;
import raf.dsw.classycraft.app.gui.swing.tree.model.ClassyTreeItem;
import raf.dsw.classycraft.app.observer.ISubscriber;
import raf.dsw.classycraft.app.observer.Notification;
import raf.dsw.classycraft.app.repository.composite.ClassyNode;
import raf.dsw.classycraft.app.repository.implementation.Dijagram;
import raf.dsw.classycraft.app.repository.implementation.Package;
import raf.dsw.classycraft.app.repository.implementation.Project;

import javax.swing.*;
import javax.swing.event.TreeSelectionEvent;
import javax.swing.event.TreeSelectionListener;
import javax.swing.tree.TreePath;
import java.awt.event.MouseListener;
import java.lang.invoke.CallSite;
import java.util.ArrayList;

@Getter
@Setter
public class TabbedPanel extends JTabbedPane implements TreeSelectionListener,ISubscriber {
    private Dijagram trenutniDijagram;
    private Package trenutniPackage;


    public TabbedPanel(){
        trenutniPackage = null;
        trenutniDijagram = null;
    }
    public void openTabs(Package pckg)
    {
        removeAll();
        for (ClassyNode child : pckg.getListOfChildren())
        {
            if (child instanceof Dijagram) {
                var diagram = (Dijagram) child;
                this.add(diagram.getName(), new DijagramView((Dijagram)child));
            }
        }
        setListeners(pckg);
    }

    /**
     * Removes listeners from old package and adds them to new package
     * Sets selectedPackage to new package
     */
    private void setListeners(Package newPackage)
    {
        // Remove listeners from old package
        if(trenutniPackage != null)
        {
            trenutniPackage.removeSubscriber(this);
        }

        // Add listeners to new package
        trenutniPackage = newPackage;
//        trenutniPackage.addSubscriber(this);
    }

    /**
     * Called when a Diagram in selected package changes or a package changes
     */
    @Override
    public void update(Object notification) {

        trenutniPackage = getTrenutniPackage();
        System.out.println("Update za TabbedPanel => " + notification);
//        if(notification.equals(Notification.RENAME)){
//            for(ClassyNode child : trenutniPackage.getListOfChildren()){
//                if(trenutniDijagram.equals(child)){
//                    //  child.setName();
//                }
//            }
//        }

        if(notification.equals(Notification.NEW)){
            ArrayList<ClassyNode> lista = (ArrayList<ClassyNode>) trenutniPackage.getListOfChildren();
            if(!lista.isEmpty()){
                openTabs(trenutniPackage);
            }

            //lista.add((ClassyNode) new Dijagram());
        }
    }

    @Override
    public void valueChanged(TreeSelectionEvent e) {
        TreePath path = e.getPath();
        ClassyTreeItem treeItemSelected = (ClassyTreeItem)path.getLastPathComponent();
        ClassyNode node = treeItemSelected.getClassyNode();
        if(node instanceof Dijagram)
        {
            selectNewDijagram((Dijagram) node);
        }
    }
    private void selectNewDijagram(Dijagram newDijagram)
    {
        if(trenutniDijagram != null)
            trenutniDijagram.removeSubscriber(this);

        setTrenutniDijagram(newDijagram);

        trenutniDijagram = newDijagram;
        trenutniDijagram.addSubscriber(this);
    }

    public Package getTrenutniPackage() {
        ClassyNode cn = MainFrame.getInstance().getClassyTree().getSelectedNode().getClassyNode();
        if(cn instanceof Package)
            return (Package)cn;
        else
            return (Package)(cn.getParent());
    }
}
