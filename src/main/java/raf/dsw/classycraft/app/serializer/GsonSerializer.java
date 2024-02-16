package raf.dsw.classycraft.app.serializer;

import raf.dsw.classycraft.app.gui.swing.desktop.view.PackageView;
import raf.dsw.classycraft.app.gui.swing.view.MainFrame;
import raf.dsw.classycraft.app.repository.composite.ClassyNode;
import raf.dsw.classycraft.app.repository.implementation.Dijagram;
import raf.dsw.classycraft.app.repository.implementation.Project;

import javax.swing.*;
import javax.swing.tree.TreePath;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class GsonSerializer implements Serializer {
    @Override
    public void saveProject(Project name) {

    }

    @Override
    public void saveAsProject(Project name) {

    }

    @Override
    public Project openProject(File file) {
       return null;
    }

    @Override
    public void loadTemplate(File file) {

    }

    @Override
    public void saveTemplate(Dijagram dijagram, String templateName) {

    }
}
