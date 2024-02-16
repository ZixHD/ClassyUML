package raf.dsw.classycraft.app.gui.swing.controller;


import raf.dsw.classycraft.app.core.ApplicationFramework;
import raf.dsw.classycraft.app.gui.swing.desktop.view.DijagramView;
import raf.dsw.classycraft.app.gui.swing.desktop.view.PackageView;
import raf.dsw.classycraft.app.gui.swing.view.MainFrame;
import raf.dsw.classycraft.app.messages.IllegalEvent;

import javax.swing.*;
import java.awt.event.ActionEvent;

public class SaveTemplateAction extends AbstractClassyAction{

    public SaveTemplateAction(){
        putValue(NAME, "Save Template");
        putValue(SHORT_DESCRIPTION, "Save Template");
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        PackageView packageView = MainFrame.getInstance().getDesktopPanel();
        DijagramView dijagramView = packageView.getSelectedDiagramView();
        if(dijagramView == null){
            ApplicationFramework.getInstance().getMessageGenerator().createMessage(IllegalEvent.NODE_NOT_SELECTED);
            return;
        }

        String templateName = JOptionPane.showInputDialog(new JFrame(), "Unesite ime za template", "Template Name", JOptionPane.PLAIN_MESSAGE);
        if(templateName == null || templateName.trim().equals("")){
            ApplicationFramework.getInstance().getMessageGenerator().createMessage(IllegalEvent.NAME_CANNOT_BE_EMPTY);
            return;
        }

        ApplicationFramework.getInstance().getSerializer().saveTemplate(dijagramView.getDig(), templateName);
    }
}
