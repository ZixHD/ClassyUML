package raf.dsw.classycraft.app.gui.swing.model.interClass;

import lombok.Getter;
import lombok.Setter;
import raf.dsw.classycraft.app.gui.swing.model.InterClass;
import raf.dsw.classycraft.app.observer.Notification;
import raf.dsw.classycraft.app.repository.composite.ClassyNode;

import java.util.ArrayList;
import java.util.List;
@Getter
@Setter
public class Enum extends InterClass {


    private int x, y;
    private int width, height;

    private int visibility;
    List<String> listaEnuma = new ArrayList<>();


    public Enum(String name, ClassyNode parent, int x, int y) {
        super(name, parent, x, y);
        this.x = x;
        this.y = y;
    }


    private void addEnumElement(String temp){
        if(listaEnuma.isEmpty()){
            listaEnuma = new ArrayList<>();
        }
        if(!listaEnuma.contains(temp)){
            listaEnuma.add(temp);
        }
    }

    private void deleteEnumElement(String temp){
        if(listaEnuma.contains((temp))){
            listaEnuma.remove(temp);
        }
    }

    public void setWidthAndHeight(int width, int height){
        this.width = width;
        this.height = height;
        changedIt();
    }

    public void setX(int x) {
        this.x = x;
        getParent().notifySubscribers(Notification.REPAINT);
        changedIt();
    }

    public void setY(int y) {
        this.y = y;
        getParent().notifySubscribers(Notification.REPAINT);
        changedIt();
    }

}
