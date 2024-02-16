package raf.dsw.classycraft.app.gui.swing.controller;

import raf.dsw.classycraft.app.core.ApplicationFramework;
import raf.dsw.classycraft.app.gui.swing.tree.model.ClassyTreeItem;
import raf.dsw.classycraft.app.gui.swing.view.MainFrame;
import raf.dsw.classycraft.app.messages.IllegalEvent;
import raf.dsw.classycraft.app.repository.composite.ClassyNode;
import raf.dsw.classycraft.app.repository.implementation.Dijagram;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;

public class NewProjectAction extends AbstractClassyAction{


    public NewProjectAction(){
        //putValue(ACCELERATOR_KEY, KeyStroke.getKeyStroke(KeyEvent.VK_F4, ActionEvent.ALT_MASK));
        putValue(SMALL_ICON, loadIcon("/images/newFolder.png"));
        putValue(NAME, "NewFolder");
        putValue(SHORT_DESCRIPTION, "New Folder");
    }


    @Override
    public void actionPerformed(ActionEvent e) {
        ClassyTreeItem selected = MainFrame.getInstance().getClassyTree().getSelectedNode();
        if(selected == null){
            ApplicationFramework.getInstance().getMessageGenerator().createMessage(IllegalEvent.NODE_NOT_SELECTED);
            return;
        }
        ClassyNode s = selected.getClassyNode();
        if(s instanceof Dijagram){
            ApplicationFramework.getInstance().getMessageGenerator().createMessage(IllegalEvent.CANNOT_ADD_CHILD);
            return;
        }

        MainFrame.getInstance().getClassyTree().addChild(selected,null);
    }

}
