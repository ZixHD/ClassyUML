package raf.dsw.classycraft.app.gui.swing.view;

import javax.swing.*;

public class MyToolBar extends JToolBar {
    public MyToolBar(){
        super(HORIZONTAL);
        setFloatable(false);

        this.add(MainFrame.getInstance().getActionManager().getNewProjectAction());
        this.add(MainFrame.getInstance().getActionManager().getDeleteNodeAction());
        this.add(MainFrame.getInstance().getActionManager().getChangeAuthorAction());
        this.add(MainFrame.getInstance().getActionManager().getPictureAction());
        this.add(new JSeparator());
        this.add(MainFrame.getInstance().getActionManager().getUndoAction());
        this.add(MainFrame.getInstance().getActionManager().getRedoAction());
        this.add(MainFrame.getInstance().getActionManager().getAboutUsAction());
        this.add(MainFrame.getInstance().getActionManager().getExitAction());
    }
}
