package raf.dsw.classycraft.app.gui.swing.model;

import lombok.Getter;
import lombok.Setter;
import raf.dsw.classycraft.app.observer.Notification;
import raf.dsw.classycraft.app.repository.composite.ClassyNode;
import raf.dsw.classycraft.app.repository.implementation.Dijagram;
@Getter
@Setter

public abstract class DijagramElement extends ClassyNode {

    private int strokeWidth;
    private int colourInside;
    private int colourOutline;
    private boolean selected;

    public DijagramElement(String name, ClassyNode parent) {
        super(name, parent);
        strokeWidth = 2;            //2 pixels
        colourInside = 0xffffff;          //interior = white
        colourOutline = 0x000000;
    }

    public void setColourInside(String color){
        this.colourInside = Integer.decode(color);
        getParent().notifySubscribers(Notification.REPAINT);
        changedIt();
    }
    public void setColourOutline(String color) {
        this.colourOutline = Integer.decode(color);
        getParent().notifySubscribers(Notification.REPAINT);
        changedIt();
    }

    public void setSelected(boolean selected) {
        this.selected = selected;
        getParent().notifySubscribers(Notification.REPAINT);
    }

    @Override
    public void setName(String name){
        super.setName(name);
        changedIt();
    }

    public void changedIt(){
        ((Dijagram)getParent()).changedIt();
    }

    @Override
    public boolean equals(Object obj) {
        if(!(obj instanceof DijagramElement)) return false;
        DijagramElement e = (DijagramElement) obj;
        return this.getName().equals(e.getName()) && this.getParent().equals(e.getParent());
    }


}
