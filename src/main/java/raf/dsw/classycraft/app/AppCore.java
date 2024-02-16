package raf.dsw.classycraft.app;

import raf.dsw.classycraft.app.core.ApplicationFramework;
import raf.dsw.classycraft.app.core.ClassyRepository;
import raf.dsw.classycraft.app.core.Gui;
import raf.dsw.classycraft.app.gui.swing.SwingGui;
import raf.dsw.classycraft.app.logger.FileLogger;
import raf.dsw.classycraft.app.logger.Logger;
import raf.dsw.classycraft.app.logger.LoggerFactory;
import raf.dsw.classycraft.app.logger.MessageGenerator;
import raf.dsw.classycraft.app.messages.MessageGeneratorImplementation;
import raf.dsw.classycraft.app.repository.ClassyRepositoryImplementation;
import raf.dsw.classycraft.app.serializer.GsonSerializer;
import raf.dsw.classycraft.app.serializer.Serializer;

public class AppCore {
    public static void main(String[] args) {
        ApplicationFramework appCore = ApplicationFramework.getInstance();
        Gui gui = new SwingGui();
        ClassyRepository classyRepository = new ClassyRepositoryImplementation();
        MessageGenerator messageGenerator = new MessageGeneratorImplementation();
        LoggerFactory logger = new LoggerFactory();
        Serializer serializer = new GsonSerializer();


        //Promeni u Console/File za log type//
        Logger log = logger.createLogger("Console");
        appCore.initialise(gui, classyRepository, log, messageGenerator, serializer);
        messageGenerator.addSubscriber(log);
        appCore.run();
    }
}