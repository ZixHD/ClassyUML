package raf.dsw.classycraft.app.gui.swing.controller.toolbarActions;

import raf.dsw.classycraft.app.gui.swing.controller.AbstractClassyAction;
import raf.dsw.classycraft.app.gui.swing.state.StateManager;
import raf.dsw.classycraft.app.gui.swing.view.MainFrame;

import java.awt.event.ActionEvent;

public class SelectElementAction extends AbstractClassyAction {

    public SelectElementAction(){
        putValue(SMALL_ICON, loadIcon("/images/selectElement.png"));
        putValue(NAME, "Select Element");
        putValue(SHORT_DESCRIPTION, "Select Element");
    }
    @Override
    public void actionPerformed(ActionEvent e) {

        StateManager stateManager = MainFrame.getInstance().getDesktopPanel().getStateManager();
        stateManager.setSelectState();
    }
}
