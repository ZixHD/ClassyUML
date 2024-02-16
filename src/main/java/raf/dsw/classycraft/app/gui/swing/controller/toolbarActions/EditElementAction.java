package raf.dsw.classycraft.app.gui.swing.controller.toolbarActions;

import raf.dsw.classycraft.app.gui.swing.controller.AbstractClassyAction;
import raf.dsw.classycraft.app.gui.swing.state.StateManager;
import raf.dsw.classycraft.app.gui.swing.view.MainFrame;

import java.awt.event.ActionEvent;

public class EditElementAction extends AbstractClassyAction {

    public EditElementAction() {
        putValue(SMALL_ICON, loadIcon("/images/edit.png"));
        putValue(NAME, "Edit Content");
        putValue(SHORT_DESCRIPTION, "Edit Content");
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        StateManager stateManager = MainFrame.getInstance().getDesktopPanel().getStateManager();
        stateManager.setEditState();
    }
}
