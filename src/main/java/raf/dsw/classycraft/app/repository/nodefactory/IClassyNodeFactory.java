package raf.dsw.classycraft.app.repository.nodefactory;

import raf.dsw.classycraft.app.repository.composite.ClassyNode;

public interface IClassyNodeFactory {

    ClassyNode getNode(ClassyNode selectedNode);
}
