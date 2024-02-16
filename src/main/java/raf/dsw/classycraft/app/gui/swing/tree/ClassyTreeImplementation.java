package raf.dsw.classycraft.app.gui.swing.tree;

import lombok.Getter;
import lombok.Setter;
import raf.dsw.classycraft.app.gui.swing.tree.model.ClassyTreeItem;
import raf.dsw.classycraft.app.gui.swing.tree.view.ClassyTreeView;
import raf.dsw.classycraft.app.gui.swing.view.MainFrame;
import raf.dsw.classycraft.app.repository.composite.ClassyNode;
import raf.dsw.classycraft.app.repository.composite.ClassyNodeComposite;
import raf.dsw.classycraft.app.repository.implementation.Dijagram;
import raf.dsw.classycraft.app.repository.implementation.Package;
import raf.dsw.classycraft.app.repository.implementation.Project;
import raf.dsw.classycraft.app.repository.implementation.ProjectExplorer;
import raf.dsw.classycraft.app.repository.nodefactory.ClassyNodeFactory;
import raf.dsw.classycraft.app.repository.nodefactory.FactoryUtils;

import javax.swing.*;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;
import javax.swing.tree.TreeNode;
import javax.swing.tree.TreePath;
import java.awt.*;
import java.util.Enumeration;

@Getter
@Setter
public class ClassyTreeImplementation implements ClassyTree{
    private ClassyTreeView treeView;
    private DefaultTreeModel treeModel;
    private ClassyTreeItem root;



    @Override
    public ClassyTreeView generateTree(ProjectExplorer projectExplorer) {
        ClassyTreeItem root = new ClassyTreeItem(projectExplorer);
        this.root = root;
        treeModel = new DefaultTreeModel(root);
        treeView = new ClassyTreeView(treeModel);
        return treeView;
    }

    @Override
    public void addChild(ClassyTreeItem parent, ClassyNode childForTemplate) {
       // System.out.println("Child for template" + childForTemplate + " Parent: " + parent);
        if (!(parent.getClassyNode() instanceof ClassyNodeComposite))
            return;
        if(parent.getClassyNode() instanceof Dijagram) {
            int size = ((Dijagram) parent.getClassyNode()).getListOfChildren().size();
            ClassyNode lastChild = ((Dijagram) parent.getClassyNode()).getListOfChildren().get(size - 1);
            ClassyTreeItem newChildWrapper = new ClassyTreeItem(lastChild);
            parent.add(newChildWrapper);
            //parent.getChildren().add(newChildWrapper);
        } else{
            if (childForTemplate != null) {
                ClassyTreeItem childWrapper = new ClassyTreeItem(childForTemplate);
                parent.add(childWrapper);
                parent.getListofchildren().add(childWrapper);
                ((ClassyNodeComposite) parent.getClassyNode()).addChild(childForTemplate);
                return;
            }
            ClassyNode child = createChild(parent.getClassyNode());
            if(child == null) return;
            ClassyTreeItem childWrapper = new ClassyTreeItem(child);
            parent.add(childWrapper);
            parent.getListofchildren().add(childWrapper);
            ((ClassyNodeComposite) parent.getClassyNode()).addChild(child);
        }


        treeView.expandPath(treeView.getSelectionPath());
        SwingUtilities.updateComponentTreeUI(treeView);
    }
    @Override
    public void removeChild(ClassyTreeItem child) {
        System.out.println("Brisem Fajl ==> " + child.getClassyNode().getName());
        ClassyNodeComposite parentOfSelected = null;
        if(child.getClassyNode().getParent() instanceof ClassyNodeComposite) {
            parentOfSelected = (ClassyNodeComposite) child.getClassyNode().getParent();
            parentOfSelected.removeChild(child.getClassyNode());                           //izbrisano iz MapNoda
        }

        if(parentOfSelected == null) return;
        SwingUtilities.updateComponentTreeUI(treeView);
        DefaultMutableTreeNode selectedTreeNode = (DefaultMutableTreeNode) treeView.getSelectionPath().getLastPathComponent();
        treeModel.removeNodeFromParent(selectedTreeNode);
    }

    @Override
    public void loadProject(Project node) {
        ClassyTreeItem loadProject = new ClassyTreeItem(node);
        root.add(loadProject);

        ((ClassyNodeComposite) root.getClassyNode()).addChild(node);
        treeView.expandPath(treeView.getSelectionPath());
        SwingUtilities.updateComponentTreeUI(treeView);
    }

    @Override
    public ClassyTreeItem getSelectedNode() {return (ClassyTreeItem) treeView.getLastSelectedPathComponent();}

    private ClassyNode createChild(ClassyNode parent) {
        ClassyNodeFactory nf = FactoryUtils.getFactory(parent);
        return nf.getNode(parent);
    }

    public void deleteInTree(ClassyNode child){
        //trazimo put to trenutne mape, selektujemo trenutnu mapu -> dodajemo wrapper u tree -> trigeruje se repaint drveta
        ClassyTree ourTree = MainFrame.getInstance().getClassyTree();
        ClassyTreeItem root = ((ClassyTreeImplementation)ourTree).getRoot();
        TreePath pathToCurrMap = ((ClassyTreeImplementation)MainFrame.getInstance().getClassyTree()).findPathToNode(root, child);

        ((ClassyTreeImplementation)ourTree).getTreeView().setSelectionPath(pathToCurrMap);
        System.out.println("TRee " + ourTree.getSelectedNode());
        ourTree.removeChild(ourTree.getSelectedNode());
    }
    public TreePath findPathToNode(ClassyTreeItem root, ClassyNode currMapNode) {
        //pretrazivanje stabla rekurzivno
        Enumeration<TreeNode> element = root.depthFirstEnumeration();
        while (element.hasMoreElements()) {
            ClassyTreeItem node = (ClassyTreeItem) element.nextElement();
            if (node.getClassyNode().equals(currMapNode)) {
                return new TreePath(node.getPath());
            }
        }
        return null;
    }
    public void addToTree(ClassyNode parent, ClassyNode child){
        //trazimo put to trenutne mape, selektujemo trenutnu mapu -> dodajemo wrapper u tree -> trigeruje se repaint drveta
        ClassyTree ourTree = MainFrame.getInstance().getClassyTree();
        ClassyTreeItem root = ((ClassyTreeImplementation)ourTree).getRoot();
        TreePath pathToCurrMap = ((ClassyTreeImplementation)MainFrame.getInstance().getClassyTree()).findPathToNode(root, parent);

        ((ClassyTreeImplementation)ourTree).getTreeView().setSelectionPath(pathToCurrMap);
        ourTree.addChild(ourTree.getSelectedNode(), child);
    }
}
