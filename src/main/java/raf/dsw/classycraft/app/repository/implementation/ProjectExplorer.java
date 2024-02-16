package raf.dsw.classycraft.app.repository.implementation;

import raf.dsw.classycraft.app.observer.Notification;
import raf.dsw.classycraft.app.repository.composite.ClassyNode;
import raf.dsw.classycraft.app.repository.composite.ClassyNodeComposite;

public class ProjectExplorer extends ClassyNodeComposite {
    public ProjectExplorer(String name) {
        super(name, null);
    }

    @Override
    public void addChild(ClassyNode child) {
        if (child != null &&  child instanceof Project){
            Project newProject = (Project) child;
            if (!this.getListOfChildren().contains(newProject)){
                this.getListOfChildren().add(newProject);
            }
        }
//        this.notifySubscribers(Notification.NEW);
    }

}
