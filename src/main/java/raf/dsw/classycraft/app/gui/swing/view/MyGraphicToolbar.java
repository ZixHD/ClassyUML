package raf.dsw.classycraft.app.gui.swing.view;

import com.sun.tools.javac.Main;
import lombok.Getter;
import lombok.Setter;
import raf.dsw.classycraft.app.gui.swing.state.State;

import javax.swing.*;
@Getter
@Setter
public class MyGraphicToolbar extends JToolBar {

    public MyGraphicToolbar() {
        super(VERTICAL);
        setFloatable(false);

        add(MainFrame.getInstance().getActionManager().getNewClassAction());
        add(MainFrame.getInstance().getActionManager().getNewAssociationAction());
        add(MainFrame.getInstance().getActionManager().getEditElementAction());
        add(MainFrame.getInstance().getActionManager().getSelectElementAction());
        add(MainFrame.getInstance().getActionManager().getMoveElementAction());
        add(MainFrame.getInstance().getActionManager().getDeleteElementAction());
        add(MainFrame.getInstance().getActionManager().getZoomInAction());
        add(MainFrame.getInstance().getActionManager().getZoomOutAction());
        add(MainFrame.getInstance().getActionManager().getPanAction());


    }
}
