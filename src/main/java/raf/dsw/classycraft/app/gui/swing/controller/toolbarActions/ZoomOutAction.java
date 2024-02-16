package raf.dsw.classycraft.app.gui.swing.controller.toolbarActions;

import raf.dsw.classycraft.app.gui.swing.controller.AbstractClassyAction;
import raf.dsw.classycraft.app.gui.swing.state.StateManager;
import raf.dsw.classycraft.app.gui.swing.view.MainFrame;

import java.awt.event.ActionEvent;

public class ZoomOutAction extends AbstractClassyAction {

    public ZoomOutAction(){
        putValue(SMALL_ICON, loadIcon("/images/zoomOut.png"));
        putValue(NAME, "Zoom Out");
        putValue(SHORT_DESCRIPTION, "Zoom Out");
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        StateManager stateManager = MainFrame.getInstance().getDesktopPanel().getStateManager();
        stateManager.setZoomOutState();
    }
}
