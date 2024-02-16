package raf.dsw.classycraft.app.gui.swing.tree.view;

import raf.dsw.classycraft.app.gui.swing.tree.model.ClassyTreeItem;
import raf.dsw.classycraft.app.repository.implementation.Package;
import raf.dsw.classycraft.app.repository.implementation.ProjectExplorer;

import javax.swing.*;
import javax.swing.tree.DefaultTreeCellRenderer;
import java.awt.*;
import java.net.URL;

public class ClassyTreeCellRenderer extends DefaultTreeCellRenderer {
    @Override
    public Component getTreeCellRendererComponent(JTree tree, Object value, boolean sel, boolean expanded, boolean leaf, int row, boolean hasFocus) {

        super.getTreeCellRendererComponent(tree, value, sel,expanded, leaf, row, hasFocus);
        URL imageURL = null;

        if (((ClassyTreeItem)value).getClassyNode() instanceof ProjectExplorer) {
            imageURL = getClass().getResource("/images/tdiagram.gif");
        }
        else if (((ClassyTreeItem)value).getClassyNode() instanceof Package) {
            imageURL = getClass().getResource("/images/tproject.gif");
        }

        Icon icon = null;
        if (imageURL != null)
            icon = new ImageIcon(imageURL);
        setIcon(icon);

        return this;
    }
}
