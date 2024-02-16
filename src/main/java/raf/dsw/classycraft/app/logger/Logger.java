package raf.dsw.classycraft.app.logger;

import raf.dsw.classycraft.app.messages.Message;
import raf.dsw.classycraft.app.observer.ISubscriber;

public interface Logger extends ISubscriber {

    void loggMessage(Message message);

    void update(Object notification);
}
