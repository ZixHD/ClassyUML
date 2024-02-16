package raf.dsw.classycraft.app.gui.swing.model;

import lombok.Getter;
import lombok.Setter;
import raf.dsw.classycraft.app.observer.Notification;
import raf.dsw.classycraft.app.repository.composite.ClassyNode;

import java.awt.*;
@Getter
@Setter
public abstract class InterClass extends DijagramElement {
   // private String visibility;
   private int x, y;
    private int width, height;

    private int opacity;

    public InterClass(String name, ClassyNode parent, int x, int y) {
        super(name, parent);
        this.x = x;
        this.y = y;
    }
   
    public void setWidthAndHeight(int width, int height){
        this.width = width;
        this.height = height;
        changedIt();
    }
    public Point getCenter(){
        return new Point(x+(width/2), y+(height/2));
    }

    public void setX(int x) {
        this.x = x;
        //getParent().notifySubscribers(Notification.REPAINT);
        changedIt();
    }

    public void setY(int y) {
        this.y = y;
        //getParent().notifySubscribers(Notification.REPAINT);
        changedIt();
    }
}
