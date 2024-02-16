package raf.dsw.classycraft.app.observer;

import java.util.List;

public interface IPublisher {


    void addSubscriber(ISubscriber subscriber);
    void removeSubscriber(ISubscriber subscriber);
    void notifySubscribers(Object notification);
}
