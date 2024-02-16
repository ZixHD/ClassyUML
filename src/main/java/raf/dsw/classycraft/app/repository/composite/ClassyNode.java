package raf.dsw.classycraft.app.repository.composite;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import raf.dsw.classycraft.app.observer.IPublisher;
import raf.dsw.classycraft.app.observer.ISubscriber;
import raf.dsw.classycraft.app.observer.Notification;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public abstract class ClassyNode implements IPublisher{
    private String name;
    private ClassyNode parent;
    private List<ISubscriber> subs;

    public ClassyNode(String name,ClassyNode parent){
        this.name=name;
        this.parent=parent;
    }


    @Override
    public boolean equals(Object obj){
        if(obj!=null && obj instanceof ClassyNode){
            ClassyNode Obj1 = (ClassyNode) obj;
            return this.getName().equals(Obj1.getName());
        }
        return false;
    }
    public void addSubscriber(ISubscriber sub) {
        if(sub == null) return;
        if(subs == null) subs = new ArrayList<>();
        if(subs.contains(sub)) return;
        subs.add(sub);
    }

    @Override
    public void removeSubscriber(ISubscriber sub) {
        if(sub == null || this.subs == null || !this.subs.contains(sub))
            return;
        this.subs.remove(sub);
    }

    @Override
    public void notifySubscribers(Object notification) {
        System.out.println("Notifajujem Subscribere od " + this);
        if(notification == null || subs == null || subs.isEmpty()) return;

        //Iterator jer kad removujemo subscribera iz liste -> ConcurrentModifiactionException
        Iterator<ISubscriber> it = subs.iterator();
        while(it.hasNext()){
            ISubscriber s = it.next();
            s.update(notification);
            if(notification.equals(Notification.REMOVE)) it.remove();
        }
    }


    public void setName(String name){
        this.name = name;
    }

    public void setParent(ClassyNode parent) {
        this.parent = parent;
    }
}
