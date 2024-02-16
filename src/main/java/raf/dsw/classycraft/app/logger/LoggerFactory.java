package raf.dsw.classycraft.app.logger;

public class LoggerFactory {
    public LoggerFactory(){}
    public Logger createLogger(String type){
        if(type.equals("Console"))
            return new ConsoleLogger();
        else
            return new FileLogger();
    }
}
