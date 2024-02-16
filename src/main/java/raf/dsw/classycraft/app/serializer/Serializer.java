package raf.dsw.classycraft.app.serializer;

import raf.dsw.classycraft.app.gui.swing.desktop.view.DijagramView;
import raf.dsw.classycraft.app.repository.implementation.Dijagram;
import raf.dsw.classycraft.app.repository.implementation.Project;

import java.io.File;

public interface Serializer {


    void saveProject(Project name);
    void saveAsProject(Project name);
    Project openProject(File file);
    void loadTemplate(File file);
    void saveTemplate(Dijagram dijagram, String templateName);
}
