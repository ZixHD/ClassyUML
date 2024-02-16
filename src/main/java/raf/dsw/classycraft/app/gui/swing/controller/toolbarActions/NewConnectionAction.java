package raf.dsw.classycraft.app.gui.swing.controller.toolbarActions;

import raf.dsw.classycraft.app.gui.swing.controller.AbstractClassyAction;
import raf.dsw.classycraft.app.gui.swing.state.StateManager;
import raf.dsw.classycraft.app.gui.swing.view.MainFrame;

import java.awt.event.ActionEvent;

public class NewConnectionAction extends AbstractClassyAction {

    public NewConnectionAction(){
        putValue(SMALL_ICON, loadIcon("/images/newConnection.png"));
        putValue(NAME, "New Connection");
        putValue(SHORT_DESCRIPTION, "New Connection");
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        StateManager stateManager = MainFrame.getInstance().getDesktopPanel().getStateManager();
        stateManager.setNewConnectionState();
    }
}
