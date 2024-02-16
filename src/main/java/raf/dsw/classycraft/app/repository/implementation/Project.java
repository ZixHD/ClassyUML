package raf.dsw.classycraft.app.repository.implementation;

import lombok.Getter;
import lombok.Setter;
import raf.dsw.classycraft.app.observer.Notification;
import raf.dsw.classycraft.app.repository.composite.ClassyNode;
import raf.dsw.classycraft.app.repository.composite.ClassyNodeComposite;
@Getter
@Setter
public class Project extends ClassyNodeComposite {
    protected String filepath;
    protected boolean changed = true;
    private String autor;

    public Project(String name, ClassyNode parent, String author) {
        super(name, parent);
        this.autor = author;
    }
    public void setAutor(String autor){
        this.autor = autor;
        this.notifySubscribers(Notification.ADD_AUTHOR);
        this.changed = true;
    }

    @Override
    public void addChild(ClassyNode child) {
        if(child != null && child instanceof Package) {
            Package newPackage = (Package) child;
            if(!listOfChildren.contains(newPackage))
                listOfChildren.add(child);
        }
        //this.notifySubscribers(Notification.NEW);
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
