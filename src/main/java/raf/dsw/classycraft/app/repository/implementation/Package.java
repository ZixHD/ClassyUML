package raf.dsw.classycraft.app.repository.implementation;

import lombok.Getter;
import lombok.Setter;
import raf.dsw.classycraft.app.gui.swing.tree.model.ClassyTreeItem;
import raf.dsw.classycraft.app.observer.Notification;
import raf.dsw.classycraft.app.repository.composite.ClassyNode;
import raf.dsw.classycraft.app.repository.composite.ClassyNodeComposite;

import javax.swing.*;
import javax.swing.tree.TreeNode;

@Getter
@Setter
public class Package extends ClassyNodeComposite{

    protected String filepath;
    protected boolean changed = true;

    public Package(String name, ClassyNode parent) {
        super(name, parent);
    }

    @Override
    public void addChild(ClassyNode child) {
        if(child != null && child instanceof Dijagram) {
            Dijagram newDijagram = (Dijagram) child;
            if(!listOfChildren.contains(newDijagram)) {
                listOfChildren.add(child);

            }
        }

        this.notifySubscribers(Notification.NEW);
        this.changed = true;
    }


    public void setFilePath(String filePath) {

        this.filepath = filePath;
    }

    @Override
    public void setName(String name){
        super.setName(name);
        this.changed = true;
        notifySubscribers(Notification.RENAME);
    }


}
