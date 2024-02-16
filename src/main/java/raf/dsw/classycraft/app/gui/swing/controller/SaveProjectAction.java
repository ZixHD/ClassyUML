package raf.dsw.classycraft.app.gui.swing.controller;

import raf.dsw.classycraft.app.core.ApplicationFramework;
import raf.dsw.classycraft.app.gui.swing.desktop.view.PackageView;
import raf.dsw.classycraft.app.gui.swing.view.MainFrame;
import raf.dsw.classycraft.app.repository.implementation.Project;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.io.File;


public class SaveProjectAction extends AbstractClassyAction{

    public SaveProjectAction(){
        putValue(NAME, "Save Project");
        putValue(SHORT_DESCRIPTION, "Save project");
    }


    @Override
    public void actionPerformed(ActionEvent e) {
        JFileChooser jfc = new JFileChooser();
        PackageView packageView = MainFrame.getInstance().getDesktopPanel();
        Project project = (Project) packageView.getTrenutniPackage();
        if(project == null) return;

        File projectFile = null;
        if(!project.isChanged()) return;
        project.setChanged(false);

        project.setParent(null);
        if(project.getFilepath() == null || project.getFilepath().trim().equals("")){
            if(jfc.showSaveDialog(MainFrame.getInstance())==JFileChooser.APPROVE_OPTION){
                projectFile = jfc.getSelectedFile();
                project.setFilePath(projectFile.getPath());
            }
        }

        ApplicationFramework.getInstance().getSerializer().saveProject(project);
    }
}
