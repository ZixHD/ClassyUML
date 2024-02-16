package raf.dsw.classycraft.app.gui.swing.state;

import raf.dsw.classycraft.app.gui.swing.commands.NewClassCommand;
import raf.dsw.classycraft.app.gui.swing.desktop.view.DijagramView;
import raf.dsw.classycraft.app.gui.swing.desktop.view.PackageView;

import java.awt.*;

public class NewClassState extends State{

    @Override
    public void misKliknut(Point e, DijagramView dijagramView, PackageView packageView) {

        NewClassCommand ncc = new NewClassCommand(e, packageView, dijagramView);
        dijagramView.getCommandManager().addCommand(ncc);
    }

    @Override
    public void misOtpusten(Point e, DijagramView dijagramView, PackageView packageView) {
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