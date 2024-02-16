package raf.dsw.classycraft.app.repository;

import lombok.Getter;
import lombok.Setter;
import raf.dsw.classycraft.app.core.ClassyRepository;
import raf.dsw.classycraft.app.repository.composite.ClassyNode;
import raf.dsw.classycraft.app.repository.composite.ClassyNodeComposite;
import raf.dsw.classycraft.app.repository.implementation.ProjectExplorer;
import raf.dsw.classycraft.app.repository.nodefactory.IClassyNodeFactory;
@Getter
@Setter
public class ClassyRepositoryImplementation implements ClassyRepository {
    private ProjectExplorer projectExplorer;
    private IClassyNodeFactory iClassyNodeFactory;
    public ClassyRepositoryImplementation(){
        projectExplorer = new ProjectExplorer("Nas Project Explorer");
    }
    public void addChild(ClassyNodeComposite parent, ClassyNode child) {

    }
}
