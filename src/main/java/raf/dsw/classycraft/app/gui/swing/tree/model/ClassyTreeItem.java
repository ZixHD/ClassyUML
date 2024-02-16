package raf.dsw.classycraft.app.gui.swing.tree.model;

import lombok.Getter;
import lombok.Setter;
import raf.dsw.classycraft.app.repository.composite.ClassyNode;

import javax.swing.tree.DefaultMutableTreeNode;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class ClassyTreeItem extends DefaultMutableTreeNode {

    private ClassyNode classyNode;
    private List<ClassyTreeItem> listofchildren = new ArrayList<>();

    public ClassyTreeItem(ClassyNode nodeModel) {
        this.classyNode = nodeModel;
    }

    @Override
    public String toString() {
        return classyNode.getName();
    }

}
