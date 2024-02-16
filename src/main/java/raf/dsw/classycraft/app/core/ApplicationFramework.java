package raf.dsw.classycraft.app.core;

import lombok.Getter;
import lombok.Setter;
import raf.dsw.classycraft.app.gui.swing.view.MainFrame;
import raf.dsw.classycraft.app.logger.Logger;
import raf.dsw.classycraft.app.logger.LoggerFactory;
import raf.dsw.classycraft.app.logger.MessageGenerator;
import raf.dsw.classycraft.app.serializer.Serializer;

@Getter
@Setter
public class ApplicationFramework {

    protected Gui gui;
    protected ClassyRepository classyRepository;
    LoggerFactory loggerFact;
    Logger logger;
    MessageGenerator messageGenerator;
    protected Serializer serializer;
    public void run(){
        this.gui.start();
    }

    public void initialise(Gui gui, ClassyRepository mapRepository, Logger logger, MessageGenerator messageGenerator, Serializer serializer)
    {
        this.gui = gui;
        this.classyRepository = mapRepository;
        this.loggerFact = new LoggerFactory();
        this.messageGenerator = messageGenerator;
        this.serializer = serializer;

        //Ovde menjas consolu/file logger
        this.logger = logger;

        messageGenerator.addSubscriber(logger);
        messageGenerator.addSubscriber(gui);
    }




    // Singleton
    private static ApplicationFramework instance;

    private ApplicationFramework(){

    }

    public static ApplicationFramework getInstance(){
        if(instance==null){
            instance = new ApplicationFramework();
        }
        return instance;
    }
}
