package raf.dsw.classycraft.app.gui.swing.state;

import raf.dsw.classycraft.app.core.ApplicationFramework;
import raf.dsw.classycraft.app.gui.swing.desktop.view.DijagramView;
import raf.dsw.classycraft.app.gui.swing.desktop.view.PackageView;
import raf.dsw.classycraft.app.gui.swing.model.Connection;
import raf.dsw.classycraft.app.gui.swing.model.InterClass;
import raf.dsw.classycraft.app.gui.swing.model.connection.Agregation;
import raf.dsw.classycraft.app.gui.swing.model.connection.Composition;
import raf.dsw.classycraft.app.gui.swing.model.connection.Dependency;
import raf.dsw.classycraft.app.gui.swing.model.connection.Generalisation;
import raf.dsw.classycraft.app.gui.swing.painters.ConnectionPainter;
import raf.dsw.classycraft.app.gui.swing.painters.ElementPainter;
import raf.dsw.classycraft.app.gui.swing.painters.KlasaPainter;
import raf.dsw.classycraft.app.gui.swing.painters.TempArrowPainter;
import raf.dsw.classycraft.app.gui.swing.painters.connectionPainter.AgregationPainter;
import raf.dsw.classycraft.app.gui.swing.painters.connectionPainter.CompositionPainter;
import raf.dsw.classycraft.app.gui.swing.painters.connectionPainter.DependencyPainter;
import raf.dsw.classycraft.app.gui.swing.painters.connectionPainter.GeneralisationPainter;
import raf.dsw.classycraft.app.messages.IllegalEvent;

import javax.swing.*;
import java.awt.*;
import java.util.List;

public class NewConnectionState extends State {

    private ConnectionPainter cp = null;
    private InterClass from;
    private InterClass to;



    @Override
    public void misKliknut(Point e, DijagramView dijagramView, PackageView packageView) {
        //NE KORISTIMO
    }

    @Override
    public void misPritisnut(Point e, DijagramView dijagramView, PackageView packageView) {
        List<ElementPainter> currentListaPaintera = dijagramView.getPainters();

        //Ako zapocinjemo vezu u pojmu: pravimo novu vezu, painter za tu vezu - i dodajemo ga u spisak paintera za trenutnu mapu
        for (ElementPainter painter : currentListaPaintera) {
            if (painter instanceof KlasaPainter && painter.elementAt(e.x, e.y)) {
                from = (InterClass) painter.getElement();
                dijagramView.setTempArrowPainter(new TempArrowPainter(null, e.x, e.y));
                return;
            }
        }

        ApplicationFramework.getInstance().getMessageGenerator().createMessage(IllegalEvent.ASSOCIATION_MUST_BEGIN_IN_TOPIC);
    }

    @Override
    public void misPovucen(Point e, DijagramView dijagramView, PackageView packageView) {
        //nas trenutni painter za asocijaciju
        dijagramView.getTempArrowPainter().updateEndPos(e.x, e.y);
        dijagramView.repaint();
    }

    @Override
    public void misOtpusten(Point e, DijagramView dijagramView, PackageView packageView) {
        //dijagramView.getPainters().remove((dijagramView.getPainters().size() - 1));
        dijagramView.setTempArrowPainter(null);
        List<ElementPainter> currentListaPaintera = dijagramView.getPainters();
        for (ElementPainter painter : currentListaPaintera) {

            //Ako zavrsavamo vezu u pojmu
            if (painter instanceof KlasaPainter && painter.elementAt(e.x, e.y)) {
                ElementPainter ep = null;
                Connection connection = null;
                to = (InterClass) painter.getElement();

                JPanel panel = new JPanel(new FlowLayout());
                JComboBox<String> comboBox = new JComboBox<>(new String[]{"Agregation", "Composition", "Dependency", "Generalisation"});
                panel.add(comboBox);
                int choice = JOptionPane.showConfirmDialog(
                        dijagramView,
                        panel

                );
                if (choice == JOptionPane.YES_OPTION) {
                    switch (comboBox.getSelectedItem().toString()) {
                        case "Agregation":
                            connection = new Agregation(from,to);
                            ep = new AgregationPainter(connection);
                            break;
                        case "Composition":
                            connection = new Composition(from,to);
                            ep = new CompositionPainter(connection);
                            break;
                        case "Dependency":
                            connection = new Dependency(from,to);
                            ep = new DependencyPainter(connection);
                            break;
                        case "Generalisation":
                            connection = new Generalisation(from,to);
                            ep = new GeneralisationPainter(connection);
                            break;
                    }
                }
                doAssociation(from, to, dijagramView, connection, ep);
                return;
            }
        }

        ApplicationFramework.getInstance().getMessageGenerator().createMessage(IllegalEvent.ASSOCIATION_MUST_END_IN_TOPIC);
    }

    public void doAssociation(InterClass from, InterClass to, DijagramView dijagramView, Connection connection, ElementPainter ep) {

        if (cp == null) {
            System.out.println("From: " + from.getName() + " to: " + to.getName());
            //ako vec postoji takva veza
            if (dijagramView.getPainters().contains(cp)) {
                ApplicationFramework.getInstance().getMessageGenerator().createMessage(IllegalEvent.ASSOCIATION_ALREADY_EXISTS);

                return;
            }

            //Ako veza ide iz pojma u isti pojam
            if (connection.getFrom().equals(connection.getTo())) {
                ApplicationFramework.getInstance().getMessageGenerator().createMessage(IllegalEvent.ASSOCIATION_INTO_ITSELF);

                return;
            }
            dijagramView.getDig().addChild(connection);
            dijagramView.addPainter(ep);
        }


    }
}