package raf.dsw.classycraft.app.gui.swing.controller;

import raf.dsw.classycraft.app.core.ApplicationFramework;
import raf.dsw.classycraft.app.gui.swing.tree.model.ClassyTreeItem;
import raf.dsw.classycraft.app.gui.swing.view.MainFrame;
import raf.dsw.classycraft.app.messages.IllegalEvent;
import raf.dsw.classycraft.app.repository.composite.ClassyNode;
import raf.dsw.classycraft.app.repository.implementation.Project;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;

public class ChangeAuthorAction extends AbstractClassyAction{

    public ChangeAuthorAction(){
        //putValue(ACCELERATOR_KEY, KeyStroke.getKeyStroke(KeyEvent.VK_A, ActionEvent.ALT_MASK));
        putValue(SMALL_ICON, loadIcon("/images/author.png"));
        putValue(NAME, "Add Author");
        putValue(SHORT_DESCRIPTION, "Add Author");
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        ClassyTreeItem selectedWrapper = MainFrame.getInstance().getClassyTree().getSelectedNode();
        if(selectedWrapper == null){
            ApplicationFramework.getInstance().getMessageGenerator().createMessage(IllegalEvent.NODE_NOT_SELECTED);
            return;
        }

        ClassyNode selected = selectedWrapper.getClassyNode();
        if(!(selected instanceof Project)){
            ApplicationFramework.getInstance().getMessageGenerator().createMessage(IllegalEvent.ONLY_PROJECT_CAN_HAVE_AUTHOR);
            return;
        }

        String newAuthor = JOptionPane.showInputDialog(new JFrame(), "Ime autora: ", "Dodaj ime autora", JOptionPane.PLAIN_MESSAGE);
        System.out.println("Nov Autor je: " + newAuthor);
        if(newAuthor.trim().equals("")){
            ApplicationFramework.getInstance().getMessageGenerator().createMessage(IllegalEvent.AUTHOR_CANNOT_BE_EMPTY);
            return;
        }
        ((Project)selected).setAutor(newAuthor);
    }
}
