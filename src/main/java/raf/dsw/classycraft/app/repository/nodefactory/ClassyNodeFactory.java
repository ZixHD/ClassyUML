package raf.dsw.classycraft.app.repository.nodefactory;

import raf.dsw.classycraft.app.repository.composite.ClassyNode;

public abstract class ClassyNodeFactory implements IClassyNodeFactory{
    @Override
    public ClassyNode getNode(ClassyNode selectedNode){
        return createNode(selectedNode);
    }

    public abstract ClassyNode createNode(ClassyNode selectedNode);

}
