package raf.dsw.classycraft.app.gui.swing.controller;

import javax.swing.*;
import java.net.URL;

public abstract class AbstractClassyAction extends AbstractAction {

    public Icon loadIcon(String filename){

        URL imageURL = getClass().getResource(filename);
        Icon icon = null;

        if(imageURL != null){
            icon = new ImageIcon(imageURL);
        }
        else{
            System.out.println("Resource not found: " + filename);
        }

        return icon;
    }
}
