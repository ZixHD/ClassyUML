package raf.dsw.classycraft.app.gui.swing.controller.toolbarActions;

import raf.dsw.classycraft.app.gui.swing.controller.AbstractClassyAction;
import raf.dsw.classycraft.app.gui.swing.desktop.view.DijagramView;
import raf.dsw.classycraft.app.gui.swing.desktop.view.PackageView;
import raf.dsw.classycraft.app.gui.swing.view.MainFrame;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;

public class UndoAction extends AbstractClassyAction {
    public UndoAction(){
        putValue(ACCELERATOR_KEY, KeyStroke.getKeyStroke(KeyEvent.VK_Z, ActionEvent.CTRL_MASK));
        putValue(MNEMONIC_KEY, KeyEvent.VK_U);
        putValue(SMALL_ICON, loadIcon("/images/Undo.png"));
        putValue(NAME, "Undo");
        putValue(SHORT_DESCRIPTION, "Undo");
    }

    public void actionPerformed(ActionEvent e) {
        PackageView currPV = MainFrame.getInstance().getDesktopPanel();
        DijagramView currMV = (DijagramView) currPV.getTabbedPane().getSelectedComponent();
        currMV.getCommandManager().undoCommand();
    }
}
