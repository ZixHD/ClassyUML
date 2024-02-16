package raf.dsw.classycraft.app.repository.nodefactory;

import raf.dsw.classycraft.app.repository.composite.ClassyNode;
import raf.dsw.classycraft.app.repository.implementation.Dijagram;

public class DiagramFactory extends ClassyNodeFactory{

    private static int diagramNo = 1;

    @Override
    public ClassyNode createNode(ClassyNode selectedNode) {
        return new Dijagram("Dijagram " + diagramNo++, selectedNode);
    }
}
