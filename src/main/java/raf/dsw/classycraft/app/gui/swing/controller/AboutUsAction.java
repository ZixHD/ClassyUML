package raf.dsw.classycraft.app.gui.swing.controller;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

public class AboutUsAction extends AbstractClassyAction {

    public AboutUsAction() {
        putValue(SMALL_ICON, loadIcon("/images/aboutUs.png"));
        putValue(NAME, "About us");
        putValue(SHORT_DESCRIPTION, "About us");
    }

    public void addPictures() {
        JFrame frame = new JFrame("About us");


        JPanel panel1 = new JPanel();
        panel1.setLayout(new FlowLayout());
        JLabel label1 = new JLabel("Bojovic Lazar RN 135/22");
        panel1.add(label1);
        ImageIcon icon1 = new ImageIcon("src/main/resources/images/Lazar.PNG");
        JLabel imageLabel1 = new JLabel();
        imageLabel1.setIcon(icon1);
        panel1.add(imageLabel1);

        JPanel panel2 = new JPanel();
        panel2.setLayout(new FlowLayout());
        JLabel label2 = new JLabel("Teodor Jakovljevic RN 96/22");
        panel2.add(label2);
        ImageIcon icon2 = new ImageIcon("src/main/resources/images/Teodor.jpg");
        JLabel imageLabel2 = new JLabel();
        imageLabel2.setIcon(icon2);
        panel2.add(imageLabel2);

        frame.add(panel1);
        frame.add(panel2, BorderLayout.SOUTH);

        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setSize(500, 500);
        frame.setVisible(true);

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        addPictures();
    }
}

