package raf.dsw.classycraft.app.gui.swing.desktop.view;

import lombok.Getter;
import lombok.Setter;
import raf.dsw.classycraft.app.gui.swing.commands.CommandManager;
import raf.dsw.classycraft.app.gui.swing.desktop.controller.MouseController;
import raf.dsw.classycraft.app.gui.swing.model.DijagramElement;
import raf.dsw.classycraft.app.gui.swing.model.InterClass;
import raf.dsw.classycraft.app.gui.swing.model.connection.Agregation;
import raf.dsw.classycraft.app.gui.swing.model.connection.Composition;
import raf.dsw.classycraft.app.gui.swing.model.connection.Dependency;
import raf.dsw.classycraft.app.gui.swing.model.connection.Generalisation;
import raf.dsw.classycraft.app.gui.swing.painters.*;
import raf.dsw.classycraft.app.gui.swing.painters.connectionPainter.AgregationPainter;
import raf.dsw.classycraft.app.gui.swing.painters.connectionPainter.CompositionPainter;
import raf.dsw.classycraft.app.gui.swing.painters.connectionPainter.DependencyPainter;
import raf.dsw.classycraft.app.gui.swing.painters.connectionPainter.GeneralisationPainter;
import raf.dsw.classycraft.app.gui.swing.tree.ClassyTreeImplementation;
import raf.dsw.classycraft.app.gui.swing.tree.model.ClassyTreeItem;
import raf.dsw.classycraft.app.gui.swing.view.MainFrame;
import raf.dsw.classycraft.app.observer.ISubscriber;
import raf.dsw.classycraft.app.observer.Notification;
import raf.dsw.classycraft.app.repository.composite.ClassyNode;
import raf.dsw.classycraft.app.repository.implementation.Dijagram;

import javax.lang.model.element.Element;
import javax.swing.*;
import java.awt.*;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class DijagramView extends JPanel implements ISubscriber{

    private Dijagram dig;
    private List<ElementPainter> painters;
    private AffineTransform affineTransform = new AffineTransform();
    private double scaling = 1.0;
    private double translateX = 0.0;
    private double translateY = 0.0;

    private ClassyTreeItem selectedDiagram;
    List<ElementPainter> selectedPainters;

    MouseController mouseController;

    TempArrowPainter tempArrowPainter = null;

    private CommandManager commandManager;

    public void deselect(){
        for(ElementPainter p : selectedPainters)
            p.setSelected(false);
        selectedPainters.clear();
    }

    public DijagramView(Dijagram dig){
        this.dig = dig;
        painters = generatePaintersForCurrentDijagram(dig);
        selectedDiagram = MainFrame.getInstance().getClassyTree().getSelectedNode().getListofchildren().get(MainFrame.getInstance().getClassyTree().getSelectedNode().getListofchildren().size()-1);

        selectedPainters = new ArrayList<>();
        commandManager = new CommandManager();


        this.setLayout(new BoxLayout(this,BoxLayout.Y_AXIS));
        this.setPreferredSize(new Dimension(800, 1000));
        this.setVisible(true);

        addMouseListener(new MouseController());
        addMouseMotionListener(new MouseController());
        //repaint();
    }

    public ClassyTreeItem getSelectedDiagram() {
        return selectedDiagram;
    }

    public void addPainter(ElementPainter painter){
        painters.add(painter);
        dig.addChild(painter.getElement());
        ((ClassyTreeImplementation)MainFrame.getInstance().getClassyTree()).addToTree(dig, null);
        repaint();
    }

    public void removePainter(ElementPainter painter){
        if(painter.getElement() == null) {
            repaint();
            return;
        }

        System.out.println("wtf");
        painters.remove(painter);
        selectedPainters.remove(painter);
        repaint();
    }


    public void zoomIn(){
        double newScaling = scaling * 1.2;
        if(newScaling >= 5) newScaling = 5;
        this.scaling = newScaling;
        setupTransformation(newScaling);
    }

    public void zoomOut(){
        double newScaling = scaling * 0.8;
        if(newScaling <= 0.2) newScaling = 0.2;
        this.scaling = newScaling;
        setupTransformation(newScaling);
    }

    public void scroll(double translateX, double translateY){
        this.translateX += translateX;
        this.translateY += translateY;
        setupTransformation(scaling);
    }

    private void setupTransformation(double scaling){
        affineTransform.setToIdentity();
        affineTransform.translate(translateX, translateY);
        affineTransform.scale(scaling, scaling);
        repaint();
    }
    public List<ElementPainter> generatePaintersForCurrentDijagram(Dijagram dijagram){
        ArrayList<ElementPainter> ep = new ArrayList<>();
        //System.out.println("Evo liste elemenata dijagrama " + dijagram.getListOfChildren());
        if(dijagram.getListOfChildren().isEmpty())
            return ep;
        for(ClassyNode element : dijagram.getListOfChildren()) {
            if (element instanceof InterClass) {
                ep.add((new KlasaPainter((DijagramElement) element)));
            }
        }
        for(ClassyNode element : dijagram.getListOfChildren()){
            if(element instanceof Agregation){
                ep.add((new AgregationPainter((DijagramElement) element)));
            }
            if(element instanceof Generalisation){
                ep.add((new GeneralisationPainter((DijagramElement) element)));
            }
            if(element instanceof Dependency){
                ep.add((new DependencyPainter((DijagramElement) element)));
            }
            if(element instanceof Composition){
                ep.add((new CompositionPainter((DijagramElement) element)));
            }
        }
        return ep;
    }

    @Override
    public void update(Object notification) {

        SwingUtilities.updateComponentTreeUI(this);    //updates the tree on the left (just in case here as well)
        System.out.println("Update za DijagramView => " + notification);
        System.out.println("Dijagram elementi: " + dig.getListOfChildren() + " Dijagram Painteri " + painters);
        //u zavisnosti od toga koja akcija je prosledjena
        PackageView ourProjectView = MainFrame.getInstance().getDesktopPanel();
        int indexOfOurMap = ourProjectView.getTabbedPane().indexOfComponent(this);

        if(notification.equals(Notification.REMOVE)){
            ourProjectView.getTabs().remove(this);
            ourProjectView.getTabbedPane().remove(indexOfOurMap);
        }
        else if(notification.equals(Notification.RENAME)){
            ourProjectView.getTabbedPane().setTitleAt(indexOfOurMap, dig.getName());
        }

        ourProjectView.revalidate();
        ourProjectView.repaint();
    }


    public void paintComponent(Graphics g){
        super.paintComponent(g);
        System.out.println("Lista Elemenata: " + dig.getListOfChildren());
        System.out.println("Lista Paintera: " + painters);
        System.out.println("Lista SelektPaintera: " + selectedPainters);

        Graphics2D g2D = (Graphics2D) g.create();
        System.out.println("BOJIM");
        if(tempArrowPainter != null)
            tempArrowPainter.draw(g2D);
        g2D.transform(affineTransform);
        if(!painters.isEmpty()) {
            for(ElementPainter p : painters){
                if(p instanceof KlasaPainter)  p.draw(g2D);
                else if(p instanceof EnumPainter)  p.draw(g2D);
                else if(p instanceof InterfacePainter) p.draw(g2D);
            }
            for(ElementPainter p : painters) {                                  //1. crtamo asocijacije da bi bile ispod pojmova
                if (p instanceof ConnectionPainter) p.draw(g2D);
                else if(p instanceof LassoPainter) p.draw(g2D);
            }
        }
        g2D.dispose();
    }

    public void deletePainterForCurrent(ElementPainter painter){
        if(painter.getElement() == null) {
            //System.out.println("WTF");
            repaint();
            return;
        }

        painters.remove(painter);
        selectedPainters.remove(painter);
        dig.removeChild(painter.getElement());
      ((ClassyTreeImplementation)MainFrame.getInstance().getClassyTree()).deleteInTree(painter.getElement());
        repaint();
    }

//    public void addPainterForCurrent(ElementPainter painter){
//        painters.add(painter);
//
//
//        repaint();
//    }

    public void setDig(Dijagram dig){
        this.dig = dig;
        repaint();
    }

    public BufferedImage createImage() {
        // Nadji validne koordinate
        // min ti je koordinata pojma najblizeg 0,0
        int minY = 1000;
        int minX = 1000;
        int maxY = 0;
        int maxX = 0;
        for (ElementPainter p: this.getPainters()){
            DijagramElement elem = p.getElement();
            if (elem instanceof InterClass){
                System.out.println(((InterClass) elem).getX() + " " + ((InterClass) elem).getY());
                if ( maxX < ((InterClass) elem).getX()) maxX = (int) ((InterClass) elem).getX();
                if ( maxY < ((InterClass) elem).getY()) maxY = (int) ((InterClass) elem).getY();
                if ( minX > ((InterClass) elem).getX()) minX = (int) ((InterClass) elem).getX();
                if ( minY > ((InterClass) elem).getY()) minY = (int) ((InterClass) elem).getY();
            }
        }
        int desiredWidth = 620;
        int desiredHeight = 360;

        BufferedImage bufferedImage = new BufferedImage(desiredWidth, desiredHeight, BufferedImage.TYPE_INT_RGB);
        Graphics2D g = bufferedImage.createGraphics();
        this.paint(g);
        return bufferedImage;
    }

}
