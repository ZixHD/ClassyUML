package raf.dsw.classycraft.app.gui.swing.controller;

import raf.dsw.classycraft.app.core.ApplicationFramework;
import raf.dsw.classycraft.app.gui.swing.view.MainFrame;
import raf.dsw.classycraft.app.repository.implementation.Project;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.io.File;

public class OpenProjectAction extends AbstractClassyAction{

    public OpenProjectAction(){
        putValue(NAME, "Open Project");
        putValue(SHORT_DESCRIPTION, "Open project");
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        JFileChooser jfc = new JFileChooser();

        if(jfc.showOpenDialog(MainFrame.getInstance()) == JFileChooser.APPROVE_OPTION){
            try{
                File file = jfc.getSelectedFile();
                Project p = ApplicationFramework.getInstance().getSerializer().openProject(file);
                MainFrame.getInstance().getClassyTree().loadProject(p);
            }catch(Exception ex){
                throw new RuntimeException(ex);
            }
        }
    }
}
