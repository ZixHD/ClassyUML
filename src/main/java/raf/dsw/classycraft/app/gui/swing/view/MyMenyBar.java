package raf.dsw.classycraft.app.gui.swing.view;

import raf.dsw.classycraft.app.gui.swing.controller.*;

import javax.swing.*;
import java.awt.event.KeyEvent;

public class MyMenyBar extends JMenuBar {

    public MyMenyBar(){
        JMenu fileMenu = new JMenu("File");
        fileMenu.setMnemonic(KeyEvent.VK_F);

        fileMenu.add(new NewProjectAction());
        fileMenu.add(new DeleteNodeAction());
        fileMenu.add(new ChangeAuthorAction());

        fileMenu.add(new JSeparator());

        ExitAction ea = new ExitAction();
        fileMenu.add(ea);


        JMenu editMenu = new JMenu("Edit");
        AboutUsAction npa = new AboutUsAction();
        editMenu.add(npa);
        this.add(fileMenu);
        this.add(editMenu);

    }

}
