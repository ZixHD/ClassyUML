package raf.dsw.classycraft.app.gui.swing.tree.view;

import raf.dsw.classycraft.app.gui.swing.tree.controller.ClassyTreeCellEditor;
import raf.dsw.classycraft.app.gui.swing.tree.controller.ClassyTreeSelectionListener;
import raf.dsw.classycraft.app.observer.ISubscriber;

import javax.swing.*;
import javax.swing.tree.DefaultTreeModel;
import java.awt.event.MouseListener;

public class ClassyTreeView extends JTree implements ISubscriber{

    public ClassyTreeView(DefaultTreeModel defaultTreeModel) {
        setModel(defaultTreeModel);

        ClassyTreeCellRenderer ruTreeCellRenderer = new ClassyTreeCellRenderer();
        addTreeSelectionListener(new ClassyTreeSelectionListener());
        setCellEditor(new ClassyTreeCellEditor(this, ruTreeCellRenderer));
        setCellRenderer(ruTreeCellRenderer);
        setEditable(true);
    }

    @Override
    public void update(Object notification) {
        SwingUtilities.updateComponentTreeUI(this);
    }
}
