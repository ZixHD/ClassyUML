package raf.dsw.classycraft.app.gui.swing.controller;

import raf.dsw.classycraft.app.core.ApplicationFramework;
import raf.dsw.classycraft.app.gui.swing.tree.model.ClassyTreeItem;
import raf.dsw.classycraft.app.gui.swing.view.MainFrame;
import raf.dsw.classycraft.app.messages.IllegalEvent;
import raf.dsw.classycraft.app.repository.composite.ClassyNode;
import raf.dsw.classycraft.app.repository.composite.ClassyNodeComposite;
import raf.dsw.classycraft.app.repository.implementation.ProjectExplorer;

import java.awt.event.ActionEvent;

public class DeleteNodeAction extends AbstractClassyAction{
    public DeleteNodeAction(){
        //putValue(ACCELERATOR_KEY, KeyStroke.getKeyStroke(KeyEvent.VK_F4, ActionEvent.ALT_MASK));
        putValue(SMALL_ICON, loadIcon("/images/deleteFolder.png"));
        putValue(NAME, "DeleteFolder");
        putValue(SHORT_DESCRIPTION, "Delete Folder");
    }


    @Override
    public void actionPerformed(ActionEvent e) {
        ClassyTreeItem selectedWrapper = MainFrame.getInstance().getClassyTree().getSelectedNode();
        if(selectedWrapper == null){
            ApplicationFramework.getInstance().getMessageGenerator().createMessage(IllegalEvent.NODE_NOT_SELECTED);
            return;
        }

        ClassyNode selected = selectedWrapper.getClassyNode();
        if(selected instanceof ProjectExplorer) {
            ApplicationFramework.getInstance().getMessageGenerator().createMessage(IllegalEvent.CANNOT_DELETE_PROJECTEXPLORER);
            return;
        }

        ((ClassyNodeComposite)selected.getParent()).removeChild(selected);
        MainFrame.getInstance().getClassyTree().removeChild(MainFrame.getInstance().getClassyTree().getSelectedNode());
    }

}
