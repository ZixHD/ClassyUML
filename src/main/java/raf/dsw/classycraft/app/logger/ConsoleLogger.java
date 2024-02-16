package raf.dsw.classycraft.app.logger;

import raf.dsw.classycraft.app.messages.Message;

public class ConsoleLogger implements Logger {
    @Override
    public void loggMessage(Message message) {
        System.out.println(message);
    }

    @Override
    public void update(Object notification) {
        if (notification instanceof Message)
            loggMessage((Message) notification);
    }
}
