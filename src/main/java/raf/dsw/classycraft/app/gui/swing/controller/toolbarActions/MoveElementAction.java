package raf.dsw.classycraft.app.gui.swing.controller.toolbarActions;

import raf.dsw.classycraft.app.gui.swing.controller.AbstractClassyAction;
import raf.dsw.classycraft.app.gui.swing.state.StateManager;
import raf.dsw.classycraft.app.gui.swing.view.MainFrame;

import java.awt.event.ActionEvent;

public class MoveElementAction extends AbstractClassyAction {
    public MoveElementAction(){
        putValue(SMALL_ICON, loadIcon("/images/moveElement.png"));
        putValue(NAME, "Move Element");
        putValue(SHORT_DESCRIPTION, "Move Element");
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        StateManager stateManager = MainFrame.getInstance().getDesktopPanel().getStateManager();
        stateManager.setMoveState();
    }
}
