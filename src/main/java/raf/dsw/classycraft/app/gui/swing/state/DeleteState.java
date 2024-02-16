package raf.dsw.classycraft.app.gui.swing.state;

import raf.dsw.classycraft.app.core.ApplicationFramework;
import raf.dsw.classycraft.app.gui.swing.commands.DeleteCommand;
import raf.dsw.classycraft.app.gui.swing.desktop.view.DijagramView;
import raf.dsw.classycraft.app.gui.swing.desktop.view.PackageView;
import raf.dsw.classycraft.app.gui.swing.painters.ElementPainter;
import raf.dsw.classycraft.app.messages.IllegalEvent;

import java.awt.*;
import java.util.List;

public class DeleteState extends State{
    @Override
    public void misKliknut(Point e, DijagramView dijagramView, PackageView packageView) {
        List<ElementPainter> selected = dijagramView.getSelectedPainters();
        System.out.println(selected);
        if (selected.isEmpty()) {
            ElementPainter sp = getClicked(e, dijagramView);
            if (sp == null) {
                ApplicationFramework.getInstance().getMessageGenerator().createMessage(IllegalEvent.NODE_NOT_SELECTED);
                return;
            }
        }
        dijagramView.getCommandManager().addCommand(new DeleteCommand(dijagramView));

        dijagramView.deselect();

    }
    private ElementPainter getClicked(Point e, DijagramView dijagramView){
        for(ElementPainter p : dijagramView.getPainters()){
            if(p.elementAt(e.x, e.y))
                return p;
        }
        return null;
    }

    @Override
    public void misOtpusten(Point e, DijagramView dijagramView, PackageView packageView) {

    }

    @Override
    public void misPovucen(Point e, DijagramView dijagramView, PackageView packageView) {

    }

    @Override
    public void misPritisnut(Point e, DijagramView dijagramView, PackageView packageView) {

    }

}
