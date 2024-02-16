package raf.dsw.classycraft.app.gui.swing.controller.toolbarActions;

import raf.dsw.classycraft.app.gui.swing.controller.AbstractClassyAction;
import raf.dsw.classycraft.app.gui.swing.state.StateManager;
import raf.dsw.classycraft.app.gui.swing.view.MainFrame;

import java.awt.event.ActionEvent;

public class NewClassAction extends AbstractClassyAction {
    public NewClassAction(){
        putValue(SMALL_ICON, loadIcon("/images/newClass.png"));
        putValue(NAME, "New Class");
        putValue(SHORT_DESCRIPTION, "New Class");
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        StateManager stateManager = MainFrame.getInstance().getDesktopPanel().getStateManager();
        stateManager.setNewClassState();

    }
}
