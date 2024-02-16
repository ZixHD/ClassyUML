package raf.dsw.classycraft.app.gui.swing.desktop.view;

import lombok.Getter;
import lombok.Setter;
import raf.dsw.classycraft.app.gui.swing.painters.ElementPainter;
import raf.dsw.classycraft.app.gui.swing.state.StateManager;
import raf.dsw.classycraft.app.gui.swing.tree.ClassyTreeImplementation;
import raf.dsw.classycraft.app.gui.swing.tree.model.ClassyTreeItem;
import raf.dsw.classycraft.app.gui.swing.view.MainFrame;
import raf.dsw.classycraft.app.observer.ISubscriber;
import raf.dsw.classycraft.app.observer.Notification;
import raf.dsw.classycraft.app.repository.composite.ClassyNode;
import raf.dsw.classycraft.app.repository.composite.ClassyNodeComposite;
import raf.dsw.classycraft.app.repository.implementation.Dijagram;
import raf.dsw.classycraft.app.repository.implementation.Package;
import raf.dsw.classycraft.app.repository.implementation.Project;

import javax.swing.*;
import javax.swing.event.TreeSelectionEvent;
import javax.swing.event.TreeSelectionListener;
import javax.swing.tree.TreePath;
import java.awt.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Getter
@Setter
public class PackageView extends JPanel implements TreeSelectionListener,ISubscriber {

    private JLabel lPackageName = new JLabel();
    private JLabel lAuthor = new JLabel();
    private String packageName = "";
    private String author = "";
    private ClassyNode trenutniPackage = null;
    private JTabbedPane tabbedPane;
    private List<DijagramView> tabs = new ArrayList<>();
    private StateManager stateManager;

    public PackageView() {
        stateManager = new StateManager();
        tabbedPane = new JTabbedPane();
        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        add(lPackageName);
        add(lAuthor);
        add(tabbedPane);
    }


    public void init(ClassyNodeComposite selectedPackage){
        System.out.println("Lista dece trenutnog package-a => " + selectedPackage.getListOfChildren());
        if(this.trenutniPackage != null) this.trenutniPackage.removeSubscriber(this);
        selectedPackage.addSubscriber(this);

        //PRVO BRISEMO SVE PRETHODNE TABOVE DA NAM SE NE BI DODAVALI NA STARE
        tabbedPane.removeAll();
        tabs.clear();

        //POSTAVLJAMO PROJEKAT, NASLOV I AUTORA PROJEKTA
        this.trenutniPackage = selectedPackage;
        this.packageName = selectedPackage.getName();
        this.author = ((Package)trenutniPackage).findAuthor(selectedPackage);
        this.lPackageName.setText(packageName);
        this.lAuthor.setText(author);

        //POPUNJAVAMO LISTU DIJAGRAMA TJ TABOVA I SUBSCRIBUJEMO TAJ TAB NA DIJAGRAM, DIJAGRAM DODAJEMO KAO KLJUC AKO JE NEMA
        for (ClassyNode child : selectedPackage.getListOfChildren()) {
            if (child instanceof Dijagram) {
                DijagramView tab =  new DijagramView((Dijagram) child);
                child.addSubscriber(tab);
                tabs.add(tab);
            }
        }

        //ZA SVAKI DIJAGRAM IZ LISTE DIJAGRAMA PRAVIMO TAB
        for(DijagramView tab : tabs){
            tab.setLayout(new GridLayout(1, 1));
            tabbedPane.addTab(tab.getDig().getName(), null, tab);
        }
    }
    @Override
    public void update(Object notification) {

        SwingUtilities.updateComponentTreeUI(this);    //updates the tree on the left (just in case here as well)
        System.out.println("Update za PackageView => " + notification);
        //u zavisnosti od toga koja akcija je prosledjena
        if(notification.equals(Notification.ADD_AUTHOR)){
            this.author = ((Project)trenutniPackage).getAutor();
            this.lAuthor.setText(author);
        }
        else if(notification.equals(Notification.RENAME)){
            this.packageName = trenutniPackage.getName();
            this.lPackageName.setText(packageName);
        }
        else if(notification.equals(Notification.NEW)){
            ClassyNodeComposite mpcProject = (ClassyNodeComposite) trenutniPackage;
            ClassyNode newMindMap = mpcProject.getListOfChildren().get(mpcProject.getListOfChildren().size() - 1);         //dohvatamo novu mapu

            DijagramView tab = new DijagramView((Dijagram) newMindMap);
            newMindMap.addSubscriber(tab);
            tabs.add(tab);                      //novu mapu dodajemo u listu tabs

            JComponent panelForMap = new JPanel();
            panelForMap.setLayout(new GridLayout(1, 1));
            tabbedPane.addTab(tab.getDig().getName(), null, tab);   //pravimo prikaz za novu mpu -> JTab
        }
        else if(notification.equals(Notification.REMOVE)) {
            this.trenutniPackage = null;
            packageName = "";   author = "";
            lPackageName.setText(packageName);  lAuthor.setText(author);
            tabs.clear();  getTabbedPane().removeAll();
        }
        else if(notification.equals(Notification.REPAINT)) {
            //
        }
        this.revalidate();
        this.repaint();

        }
//
//    public void deselect(){
//        for(Painter p : selectedComponents)
//           // p.setSelected(false);
//        selectedComponents.clear();
//    }

    @Override
    public void valueChanged(TreeSelectionEvent e) {
        TreePath path = e.getPath();
        ClassyTreeItem treeItemSelected = (ClassyTreeItem)path.getLastPathComponent();
        var node = treeItemSelected.getClassyNode();
        if(node instanceof Project)
        {
            selectNewProject((Project) node);
        }
//        else
//        {
//            var project = getParentProject(node);
//            if(project != null)
//                selectNewProject(project);
//        }
    }
    private void selectNewProject(Project newPackage)
    {
        if(trenutniPackage != null)
            trenutniPackage.removeSubscriber(this);

        setTrenutniPackage(newPackage);

        trenutniPackage = newPackage;
        trenutniPackage.addSubscriber(this);
    }

    /**
     * Finds the parent project of the selected node
     */
    private Project getParentProject(ClassyNode node)
    {
        while(node != null && !(node instanceof Project))
            node = node.getParent();

        if(node == null)
            return null;
        else
            return (Project) node;
    }

    public DijagramView getSelectedDiagramView()
    {
        var selected = tabbedPane.getSelectedComponent();
        if(selected instanceof DijagramView)
            return (DijagramView) selected;
        else
            return null;
    }



    /**
     * Removes listeners from old package and adds them to new package
     * Sets selectedPackage to new package
     * Removes listener from old parent project and adds it to new parent project
     */

}
