package raf.dsw.classycraft.app.gui.swing.commands;

import raf.dsw.classycraft.app.gui.swing.desktop.view.DijagramView;
import raf.dsw.classycraft.app.gui.swing.desktop.view.PackageView;
import raf.dsw.classycraft.app.gui.swing.model.Connection;
import raf.dsw.classycraft.app.gui.swing.model.InterClass;
import raf.dsw.classycraft.app.gui.swing.model.interClass.Enum;
import raf.dsw.classycraft.app.gui.swing.model.interClass.Interface;
import raf.dsw.classycraft.app.gui.swing.model.interClass.Klasa;
import raf.dsw.classycraft.app.gui.swing.painters.*;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;


public class  NewClassCommand extends AbstractCommand {

    private PackageView packageView;
    private DijagramView dijagramView;
    private ElementPainter thisTopicPainter;
    private List<ElementPainter> assToTopic;
    private Point topicPoint;

    public NewClassCommand(Point topicPoint, PackageView projectView, DijagramView dijagramView) {
        this.topicPoint = topicPoint;
        this.packageView = projectView;
        this.dijagramView = dijagramView;
        assToTopic = new ArrayList<>();
        this.topicPoint = topicPoint;
    }

    //Dodaje jedan Topic
    @Override
    public void doCommand() {
        //AKO NIJE PRVI PUT
        if (thisTopicPainter == null) {
//                currDijagram.addChild(thisTopicPainter.getElement());
//                packageView.addPainterForCurrent(thisTopicPainter);
//                addAssociations();
            ArrayList<ElementPainter> selected = (ArrayList<ElementPainter>) dijagramView.getSelectedPainters();

            InterClass temp = null;
            ElementPainter tempPainter = null;
            if (selected.size() == 1) {
                if(selected.get(0).getElement() instanceof Klasa) {
                    Klasa klasa = (Klasa) selected.get(0).getElement();
                    temp = new Klasa(klasa.getName(), klasa.getParent(), topicPoint.x,topicPoint.y);
                    tempPainter = new KlasaPainter(temp);
                }else if(selected.get(0).getElement() instanceof Enum){
                    Enum en = (Enum) selected.get(0).getElement();
                    temp = new Enum(en.getName(), en.getParent(), topicPoint.x,topicPoint.y);
                    tempPainter = new EnumPainter(temp);
                }else if(selected.get(0).getElement() instanceof Interface){
                    Interface inf = (Interface)selected.get(0).getElement();
                    temp = new Interface(inf.getName(), inf.getParent(), topicPoint.x,topicPoint.y);
                    tempPainter = new InterfacePainter(temp);
                }



                    tempPainter.setChecked(false);
                    selected.remove(selected.get(0));
                    //dijagramView.getDig().addChild(painter.getElement());
                    dijagramView.addPainter(tempPainter);

            } else {
                JPanel panel = new JPanel(new GridLayout(1, 1));
                JLabel label = new JLabel("Unesite naziv: ");
                JTextField field = new JTextField(10);
                panel.add(label);
                panel.add(field);

                System.out.println("Bureeeek");
                //novi interclass -> rectangle
                String[] options = {"Class", "Enum", "Interface"};
                int choice = JOptionPane.showOptionDialog(
                        dijagramView,
                        panel,

                        "Create Element",
                        JOptionPane.YES_NO_CANCEL_OPTION,
                        JOptionPane.QUESTION_MESSAGE,
                        null,
                        options,
                        options[0]
                );

// Check the user's choice
                if (choice != JOptionPane.CLOSED_OPTION) {
                    // User made a selection
                    String selectedOption = options[choice];

                    Random r = new Random();
                    //int index = packageView.getTabbedPane().getSelectedIndex();
                    //DijagramView dijagramView = packageView.getTabs().get(index);
                    int broj = r.nextInt(100) + 1;

                    // Create the appropriate element based on the user's choice


                    if (field.getText().isEmpty()) {
                        JOptionPane.showMessageDialog(dijagramView, "Naziv ne moÅ¾e biti prazan.", "Upozorenje", JOptionPane.WARNING_MESSAGE);
                        return;  // Don't proceed further
                    }
                    switch (selectedOption) {
                        case "Interface":
                            Interface interf = new Interface("Interface " + broj, dijagramView.getDig(), topicPoint.x, topicPoint.y);
                            thisTopicPainter = new InterfacePainter(interf);
                            dijagramView.addPainter(thisTopicPainter);
                            if (field.getText() != null) {
                                interf.setName(field.getText());

                            }
                           // dijagramView.getDig().addChild(interf);
                            //MainFrame.getInstance().getClassyTree().addChild(dijagramView.getSelectedDiagram(), interf);

                            break;
                        case "Enum":
                            Enum en = new Enum("Enum " + broj, dijagramView.getDig(), topicPoint.x, topicPoint.y);
                            thisTopicPainter = new EnumPainter(en);
                            dijagramView.addPainter(thisTopicPainter);
                            if (field.getText() != null) {
                                en.setName(field.getText());

                            }
                            //dijagramView.getDig().addChild(en);
                           // MainFrame.getInstance().getClassyTree().addChild(dijagramView.getSelectedDiagram(), en);
                            break;
                        case "Class":
                            Klasa klasa = new Klasa("Class " + broj, dijagramView.getDig(), topicPoint.x, topicPoint.y);
                            thisTopicPainter = new KlasaPainter(klasa);
                            dijagramView.addPainter(thisTopicPainter);
                            if (field.getText() != null) {
                                klasa.setName(field.getText());

                            }
                           // dijagramView.getDig().getListOfChildren().add(klasa);
                            //MainFrame.getInstance().getClassyTree().addChild(dijagramView.getSelectedDiagram(), klasa);
                            break;
                    }
                }

                // Add the element to the DijagramView and create the corresponding painter


                System.out.println("Lista Paintera =>");
                System.out.println(dijagramView.getPainters());
                System.out.println("Lista Elemenata");
                System.out.println(dijagramView.getDig().getListOfChildren());
            }
        }


    }

    //Brise jedan Topic
    @Override
    public void undoCommand() {
        assToTopic = findAssocToTopic();
        for (ElementPainter p : assToTopic)
            dijagramView.deletePainterForCurrent(p);
        dijagramView.deletePainterForCurrent(thisTopicPainter);
    }

    private List<ElementPainter> findAssocToTopic() {
        List<ElementPainter> assToTopic = new ArrayList<>();
        List<ElementPainter> currMapViewPainters = dijagramView.getPainters();
        for (ElementPainter p : currMapViewPainters) {
            if (p instanceof ConnectionPainter) {
                Connection a = (Connection) p.getElement();
                Klasa t = (Klasa) thisTopicPainter.getElement();
                if (a.getFrom().equals(t) || a.getTo().equals(t))
                    assToTopic.add(p);
            }
        }
        return assToTopic;
    }
        private void addAssociations() {
            for (ElementPainter p : assToTopic) {
                Connection a = (Connection) p.getElement();
                if (dijagramView.getDig().getListOfChildren().contains(a.getFrom()) && dijagramView.getDig().getListOfChildren().contains(a.getTo()))
                    dijagramView.addPainter(p);
            }
    }

}
