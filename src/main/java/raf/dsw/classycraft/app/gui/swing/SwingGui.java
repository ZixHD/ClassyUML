package raf.dsw.classycraft.app.gui.swing;

import raf.dsw.classycraft.app.core.Gui;
import raf.dsw.classycraft.app.gui.swing.view.MainFrame;
import raf.dsw.classycraft.app.messages.Message;

import javax.swing.*;

public class SwingGui implements Gui {

    private MainFrame instance;
    public SwingGui(){

    }

    @Override
    public void start() {
        instance = MainFrame.getInstance();
        this.disableUndoAction();
        this.disableRedoAction();
        instance.setVisible(true);

    }

    @Override
    public void update(Object notification) {
        ImageIcon error = new ImageIcon(getClass().getResource("/images/error.png"));
        JFrame errorFrame = new JFrame();
        errorFrame.setAlwaysOnTop(true);
        JOptionPane.showMessageDialog(errorFrame, ((Message)notification).getText(), "Error", 0, error);

    }

    @Override
    public void enableUndoAction() {
        MainFrame.getInstance().getActionManager().getUndoAction().setEnabled(true);
    }

    @Override
    public void disableUndoAction() {
        MainFrame.getInstance().getActionManager().getUndoAction().setEnabled(false);
    }

    @Override
    public void disableRedoAction() {
        MainFrame.getInstance().getActionManager().getRedoAction().setEnabled(false);
    }
    @Override
    public void enableRedoAction() {
        MainFrame.getInstance().getActionManager().getRedoAction().setEnabled(true);
    }
}
