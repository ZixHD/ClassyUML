package raf.dsw.classycraft.app.logger;

import raf.dsw.classycraft.app.core.ApplicationFramework;
import raf.dsw.classycraft.app.gui.swing.view.MainFrame;
import raf.dsw.classycraft.app.messages.Message;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

public class FileLogger implements Logger {


    @Override
    public void loggMessage(Message message) {
        File file = null;
        FileWriter fw = null;
        PrintWriter pw = null;

        try {
            file = new File("src/main/resources/log.txt");
            fw = new FileWriter(file, true);
            pw = new PrintWriter(fw);

            pw.println(message);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                pw.close(); fw.close();
            } catch (IOException | NullPointerException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public void update(Object notification) {
        if(notification instanceof  Message)
            loggMessage((Message) notification);
    }
}
