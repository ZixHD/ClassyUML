package raf.dsw.classycraft.app.gui.swing.controller.toolbarActions;

import raf.dsw.classycraft.app.gui.swing.controller.AbstractClassyAction;
import raf.dsw.classycraft.app.gui.swing.state.StateManager;
import raf.dsw.classycraft.app.gui.swing.view.MainFrame;

import java.awt.event.ActionEvent;

public class ZoomInAction extends AbstractClassyAction {

    public ZoomInAction(){
        putValue(SMALL_ICON, loadIcon("/images/zoomIn.png"));
        putValue(NAME, "Zoom In");
        putValue(SHORT_DESCRIPTION, "Zoom In");
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        StateManager stateManager = MainFrame.getInstance().getDesktopPanel().getStateManager();
        stateManager.setZoomInState();
    }
}

