package raf.dsw.classycraft.app.repository.composite;

import lombok.Getter;
import lombok.Setter;
import raf.dsw.classycraft.app.gui.swing.tree.model.ClassyTreeItem;
import raf.dsw.classycraft.app.observer.Notification;
import raf.dsw.classycraft.app.repository.implementation.Project;

import javax.swing.tree.TreeNode;
import java.util.ArrayList;
import java.util.List;
@Getter
@Setter
public abstract class ClassyNodeComposite extends ClassyNode{

    protected List<ClassyNode> listOfChildren;


    public ClassyNodeComposite(String name, ClassyNode parent){
        super(name,parent);
        this.listOfChildren= new ArrayList<>();
    }

    public abstract void addChild(ClassyNode child);


    public ClassyNode getChildByName(String name) {
        for (ClassyNode child: this.getListOfChildren()) {
            if (name.equals(child.getName())) {
                return child;
            }
        }
        return null;
    }
    public void removeChild(ClassyNode child){
        System.out.println("Brisem proj");
        if(child != null && !listOfChildren.isEmpty())
            listOfChildren.remove(child);
        child.notifySubscribers(Notification.REMOVE);
    }


    public boolean cotainsSameNameComponent(String name){
        for(ClassyNode child : listOfChildren) {
            if (child.getName().trim().equals(name.trim()))
                return true;
        }
        return false;
    }

    public String findAuthor(ClassyNode node) {
        if (node instanceof Project) {
            Project p = (Project)node;
            return p.getAutor();
        }
        ClassyNode parentNode = node.getParent();
        if (parentNode != null) {
            return findAuthor( parentNode);
        }
        return null;
    }



}
