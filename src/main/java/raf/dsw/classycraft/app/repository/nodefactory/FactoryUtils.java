package raf.dsw.classycraft.app.repository.nodefactory;

import raf.dsw.classycraft.app.repository.composite.ClassyNode;
import raf.dsw.classycraft.app.repository.implementation.Project;
import raf.dsw.classycraft.app.repository.implementation.Package;
import raf.dsw.classycraft.app.repository.implementation.ProjectExplorer;

import javax.swing.*;

public class FactoryUtils {

    private static ClassyNodeFactory pack = new PackageFactory();
    private static ClassyNodeFactory project = new ProjectFactory();
    private static ClassyNodeFactory dijagram = new DiagramFactory();
    public static ClassyNodeFactory getFactory(ClassyNode selectedNode){

        if(selectedNode instanceof ProjectExplorer)
            return project;
        if(selectedNode instanceof Project) {
            return pack;

        }else if(selectedNode instanceof Package)
        {int result = JOptionPane.showOptionDialog(
                new JFrame(),
                "Choose an option:",
                "Option Dialog",
                JOptionPane.YES_NO_OPTION,
                JOptionPane.QUESTION_MESSAGE,
                null,
                new Object[]{"Dijagram", "Package"},
                "Dijagram"
        );
            if (result == JOptionPane.YES_OPTION) {
                //System.out.println("Dijagram option selected");
                return dijagram;
            } else if (result == JOptionPane.NO_OPTION) {
                //System.out.println("Package option selected");
                return pack;
            } else {
                System.out.println("Dialog closed without clicking any button");
            }}
        return null;
    }
}
