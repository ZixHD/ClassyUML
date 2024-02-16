package raf.dsw.classycraft.app.gui.swing.tree;

import raf.dsw.classycraft.app.gui.swing.tree.model.ClassyTreeItem;
import raf.dsw.classycraft.app.gui.swing.tree.view.ClassyTreeView;
import raf.dsw.classycraft.app.repository.composite.ClassyNode;
import raf.dsw.classycraft.app.repository.implementation.Project;
import raf.dsw.classycraft.app.repository.implementation.ProjectExplorer;

public interface ClassyTree {
    ClassyTreeView generateTree(ProjectExplorer projectExplorer);
    void addChild(ClassyTreeItem parent, ClassyNode child);
    void removeChild(ClassyTreeItem child);
    ClassyTreeItem getSelectedNode();

    void loadProject(Project node);
}
