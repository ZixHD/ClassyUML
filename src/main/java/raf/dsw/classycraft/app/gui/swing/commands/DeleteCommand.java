package raf.dsw.classycraft.app.gui.swing.commands;

import com.sun.nio.sctp.Association;
import raf.dsw.classycraft.app.gui.swing.desktop.view.DijagramView;
import raf.dsw.classycraft.app.gui.swing.model.Connection;
import raf.dsw.classycraft.app.gui.swing.model.DijagramElement;
import raf.dsw.classycraft.app.gui.swing.model.InterClass;
import raf.dsw.classycraft.app.gui.swing.painters.ConnectionPainter;
import raf.dsw.classycraft.app.gui.swing.painters.ElementPainter;

import javax.swing.text.Element;
import java.util.ArrayList;
import java.util.List;

public class DeleteCommand extends AbstractCommand{


    private DijagramView dv;
    private List<ElementPainter> izbrisani;
    public DeleteCommand(DijagramView dijagramView) {
        this.dv = dijagramView;
        izbrisani = new ArrayList<>();
    }

    @Override
    public void doCommand() {

            List<ElementPainter> deletedTopics = new ArrayList<>();
            for(ElementPainter p : dv.getSelectedPainters()){
                deletedTopics.add(p);
                izbrisani.add(p);
            }

            for(ElementPainter p : deletedTopics){
                dv.deletePainterForCurrent(p);

            }

    }

    public void deleteAssToClass(DijagramElement e){
        InterClass t = (InterClass) e;
        List<ElementPainter> paintersForCurr = dv.getPainters();
        List<ElementPainter> assocToDelete = new ArrayList<>();

        for(ElementPainter p : paintersForCurr) {
            if (p instanceof ConnectionPainter) {
              Connection a = (Connection) p.getElement();
                if (a.getFrom().equals(t) || a.getTo().equals(t)) {
                    izbrisani.add(p);
                    assocToDelete.add(p);
                }
            }
        }
    }

    @Override
    public void undoCommand() {

    }


}
