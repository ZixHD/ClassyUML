package raf.dsw.classycraft.app.gui.swing.controller.toolbarActions;

import raf.dsw.classycraft.app.gui.swing.controller.AbstractClassyAction;
import raf.dsw.classycraft.app.gui.swing.state.StateManager;
import raf.dsw.classycraft.app.gui.swing.view.MainFrame;

import java.awt.event.ActionEvent;

public class PanAction extends AbstractClassyAction {
    public PanAction(){
        putValue(SMALL_ICON, loadIcon("/images/pan.png"));
        putValue(NAME, "Pan Action");
        putValue(SHORT_DESCRIPTION, "Pan Action");
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        StateManager stateManager = MainFrame.getInstance().getDesktopPanel().getStateManager();
        stateManager.setPanState();
    }
}
