package raf.dsw.classycraft.app.gui.swing.controller;

import raf.dsw.classycraft.app.gui.swing.desktop.view.DijagramView;
import raf.dsw.classycraft.app.gui.swing.desktop.view.PackageView;
import raf.dsw.classycraft.app.gui.swing.view.MainFrame;
import raf.dsw.classycraft.app.repository.implementation.Package;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class PictureAction extends AbstractClassyAction{
    public PictureAction() {
        //putValue(ACCELERATOR_KEY, KeyStroke.getKeyStroke(KeyEvent.VK_H, ActionEvent.ALT_MASK));
        putValue(SMALL_ICON, loadIcon("/images/camera.png"));
        putValue(NAME, "Save picture");
        putValue(SHORT_DESCRIPTION, "Save map as picture");
    }


    @Override
    public void actionPerformed(ActionEvent e) {
        PackageView packageView = MainFrame.getInstance().getDesktopPanel();
        DijagramView dijagramView = packageView.getSelectedDiagramView();

        JFileChooser jfc = new JFileChooser();
        if(jfc.showSaveDialog(null) == JFileChooser.APPROVE_OPTION){
            File filePicture = jfc.getSelectedFile();

            BufferedImage image = dijagramView.createImage();
            System.out.println(image);

            try{
                ImageIO.write(image, "png", new File(filePicture.getAbsolutePath()));
            }catch(IOException ex){
                throw new RuntimeException(ex);
            }
        }
    }
}
