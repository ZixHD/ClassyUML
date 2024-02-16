package raf.dsw.classycraft.app.repository.nodefactory;

import raf.dsw.classycraft.app.repository.composite.ClassyNode;
import raf.dsw.classycraft.app.repository.implementation.Package;
import raf.dsw.classycraft.app.repository.implementation.Project;

public class ProjectFactory extends ClassyNodeFactory{

    private static int projectNo = 1;

    @Override
    public ClassyNode createNode(ClassyNode selectedNode) {
        return new Project("Project " + projectNo++, selectedNode, "author");
    }
}
