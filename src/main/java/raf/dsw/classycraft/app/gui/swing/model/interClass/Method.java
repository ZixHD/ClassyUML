package raf.dsw.classycraft.app.gui.swing.model.interClass;

public class Method extends ClassContent{

    private String naziv;
    private String visibility;

    public Method(String naziv,String visibility){
        this.naziv = naziv;
        this.visibility = visibility;
    }

    @Override
    public String toString() {
        return visibility + naziv + "()";
    }
}
