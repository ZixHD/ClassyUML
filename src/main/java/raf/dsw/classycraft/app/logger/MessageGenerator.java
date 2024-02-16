package raf.dsw.classycraft.app.logger;

import raf.dsw.classycraft.app.messages.IllegalEvent;
import raf.dsw.classycraft.app.observer.IPublisher;

public interface  MessageGenerator extends IPublisher {

    void createMessage(IllegalEvent illegalEvent);
}
