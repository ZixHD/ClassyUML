package raf.dsw.classycraft.app.repository.implementation;

import lombok.Getter;
import lombok.Setter;
import raf.dsw.classycraft.app.gui.swing.model.DijagramElement;
import raf.dsw.classycraft.app.gui.swing.painters.ElementPainter;
import raf.dsw.classycraft.app.observer.ISubscriber;
import raf.dsw.classycraft.app.observer.Notification;
import raf.dsw.classycraft.app.repository.composite.ClassyNode;
import raf.dsw.classycraft.app.repository.composite.ClassyNodeComposite;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class Dijagram extends ClassyNodeComposite {

    public Package parent;
    public Dijagram(String name, ClassyNode parent) {
        super(name, parent);
        this.parent = (Package) parent;
    }


    @Override
    public void addChild(ClassyNode child) {
        if(child instanceof DijagramElement){
            DijagramElement newElement = (DijagramElement) child;
            if(!listOfChildren.contains(newElement)){
                listOfChildren.add(child);
            }
        }

        this.notifySubscribers(Notification.NEW);
        //getParent().notifySubscribers(Notification.REPAINT);
        //hangedIt();
    }
    //public Dijagram getLastChild(){return null;}

    @Override
    public void setName(String name){
        super.setName(name);
        this.notifySubscribers(Notification.RENAME);
        //changedIt();
    }

    public void changedIt(){
        ((Package)getParent()).setChanged(true);
    }

    @Override
    public boolean equals(Object obj) {
        if(!(obj instanceof  Dijagram)) return false;
        Dijagram dijagram = (Dijagram) obj;
        return dijagram.getName().equals(this.getName());
    }

}
