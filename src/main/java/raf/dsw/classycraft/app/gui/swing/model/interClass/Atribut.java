package raf.dsw.classycraft.app.gui.swing.model.interClass;

public class Atribut extends ClassContent{

    private String naziv;
    private String visibility;
    private String type;

    public Atribut(String naziv, String visibility, String type){
        this.visibility = visibility;
        this.naziv = naziv;
        this.type = type;
    }

    @Override
    public String toString() {
        return this.visibility + this.naziv + ":" + this.type;
    }
}
