package raf.dsw.classycraft.app.gui.swing.state;

import raf.dsw.classycraft.app.gui.swing.desktop.view.DijagramView;
import raf.dsw.classycraft.app.gui.swing.desktop.view.PackageView;
import raf.dsw.classycraft.app.gui.swing.model.interClass.*;
import raf.dsw.classycraft.app.gui.swing.model.interClass.Enum;
import raf.dsw.classycraft.app.gui.swing.painters.ElementPainter;
import raf.dsw.classycraft.app.gui.swing.painters.EnumPainter;
import raf.dsw.classycraft.app.gui.swing.painters.KlasaPainter;
import raf.dsw.classycraft.app.gui.swing.painters.InterfacePainter;

import javax.swing.*;
import java.awt.*;
import java.util.List;
import java.util.Objects;

public class EditState extends State{
    @Override
    public void misKliknut(Point e, DijagramView dijagramView, PackageView packageView) {

        List< ElementPainter> currentListaPaintera = dijagramView.getPainters();
        for(ElementPainter painter : currentListaPaintera) {
            if(painter instanceof InterfacePainter) {
                InterfacePanel(e, painter, dijagramView);
            }
            else if(painter instanceof KlasaPainter){
                ClassPanel(e, painter, dijagramView);
            }
            else if(painter instanceof EnumPainter){
                EnumPanel(e, painter, dijagramView);
            }
        }
    }

    public void ClassPanel(Point e, ElementPainter painter, DijagramView dijagramView){

        if(painter instanceof KlasaPainter && painter.elementAt(e.x,e.y)){
            Klasa klasa = (Klasa) painter.getElement();
            List<ClassContent> content = klasa.getListaContenta();
            JPanel panel = new JPanel();

            JPanel panel1 = new JPanel(new FlowLayout());
            JComboBox<String> comboBox1 = new JComboBox<>(new String[]{"+", "-", "#"});
            JComboBox<String> type1 = new JComboBox<>(new String[]{"String", "Integer", "float", "char", "bool"});
            JTextField field1 = new JTextField(20);
            panel1.add(comboBox1);
            panel1.add(type1);
            panel1.add(field1);

            JPanel panel2 = new JPanel(new FlowLayout());
            JComboBox<String> comboBox2 = new JComboBox<>(new String[]{"+", "-", "#"});
            JTextField field2 = new JTextField(20);
            System.out.println(klasa.getListaContenta().toString());
            Object[] temp = klasa.getListaContenta().toArray();
            JList<Object> novaLista = new JList<>(temp);
            panel1.add(new JScrollPane(novaLista));

            novaLista.addListSelectionListener(e1 -> {
                if (!e1.getValueIsAdjusting()) {
                    ClassContent selectedContent = (ClassContent) novaLista.getSelectedValue();
                    if (selectedContent != null) {
                        String newName = JOptionPane.showInputDialog("Enter new name:");
                        if (newName != null) {
                            if (selectedContent instanceof Atribut) {
                                Atribut a = (Atribut) selectedContent;
                                a.setNaziv(newName);
                            } else if (selectedContent instanceof Method) {
                                Method m = (Method) selectedContent;
                                m.setNaziv(newName);
                            }

                            // Update the JList's model to reflect the changes
                            // DefaultListModel<ClassContent> model = (ListModel<ClassContent>) novaLista.getModel();
                            int selectedIndex = novaLista.getSelectedIndex();
                            // model.setElementAt(selectedContent, selectedIndex);
                        }
                    }
                }
            });




            panel2.add(comboBox2);
            panel2.add(field2);
            // panel2.add(lista);
            panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
            panel.add(panel1);
            panel.add(panel2);

            int choice = JOptionPane.showConfirmDialog(
                    dijagramView,
                    panel
            );

            if (choice == JOptionPane.YES_OPTION) {

                Atribut atribut = new Atribut(field1.getText(), Objects.requireNonNull(comboBox1.getSelectedItem()).toString(), Objects.requireNonNull(type1.getSelectedItem()).toString());
                Method metoda = new Method(field2.getText(), Objects.requireNonNull(comboBox2.getSelectedItem()).toString());


                if(!field1.getText().isEmpty()){
                    content.add(atribut);
                }

                if(!field2.getText().isEmpty()) {
                    content.add(metoda);
                }

                dijagramView.repaint();

            }


        }else {
            // Display a different message if no InterClassPainter is found
            //  ApplicationFramework.getInstance().getMessageGenerator().createMessage(IllegalEvent.CLASS_MUST_HAVE_A_NAME);
        }
    }

    public void EnumPanel(Point e, ElementPainter painter, DijagramView dijagramView){

        if(painter instanceof EnumPainter && painter.elementAt(e.x, e.y)){
            Enum en = (Enum) painter.getElement();
            List<String> enumi = en.getListaEnuma();
            JPanel panel = new JPanel(new FlowLayout());
            JTextField field = new JTextField(20);
            panel.add(field);

            int choice = JOptionPane.showConfirmDialog(
                    dijagramView,
                    panel
            );

            if (choice == JOptionPane.YES_OPTION) {

                enumi.add(field.getText());
                dijagramView.repaint();

            }
        }else {
            // Display a different message if no InterClassPainter is found
            // ApplicationFramework.getInstance().getMessageGenerator().createMessage(IllegalEvent.ENUM_MUST_HAVE_A_NAME);
        }
    }

    public void InterfacePanel(Point e, ElementPainter painter, DijagramView dijagramView) {

        if (painter instanceof InterfacePainter && painter.elementAt(e.x, e.y)) {
            Interface inf = (Interface) painter.getElement();
            List<Method> metode = inf.getMetode();
            JPanel panel = new JPanel(new FlowLayout());
            JComboBox<String> comboBox = new JComboBox<>(new String[]{"+", "-", "#"});
            JTextField field = new JTextField(20);
            panel.add(comboBox);
            panel.add(field);
            int choice = JOptionPane.showConfirmDialog(
                    dijagramView,
                    panel
            );

            if (choice == JOptionPane.YES_OPTION) {

                Method method = new Method(field.getText(), Objects.requireNonNull(comboBox.getSelectedItem()).toString());
                metode.add(method);
                System.out.println(metode);
                dijagramView.repaint();

            }


        }else {
            // Display a different message if no InterClassPainter is found
            // ApplicationFramework.getInstance().getMessageGenerator().createMessage(IllegalEvent.INTERFACE_MUST_HAVE_A_NAME);
        }
    }


    @Override
    public void misOtpusten(Point e, DijagramView dijagramVieww, PackageView packageView) {
        //NE KORISTIMO
    }

    @Override
    public void misPovucen(Point e, DijagramView dijagramView, PackageView packageView) {
        //NE KORISTIMO
    }

    @Override
    public void misPritisnut(Point e, DijagramView dijagramView, PackageView packageView) {
        //NE KORISTIMO
    }
}
