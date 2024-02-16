package raf.dsw.classycraft.app.gui.swing.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public abstract class Connection extends DijagramElement {

    private InterClass from;
    private double xFrom, yFrom;
    private InterClass to;
    private double xTo, yTo;



    public Connection(InterClass from, InterClass to){
        super(from.getName() + "->" + to.getName(), from.getParent());
        setFrom(from);
        setTo(to);
    }

    public void setFrom(InterClass fromTopic){
        this.from = fromTopic;
        xFrom = from.getX() + (from.getWidth() / 2);
        yFrom = from.getY() + (from.getHeight() / 2);
        //changedIt();
    }

    public void setToPoint(){
        if(from.getCenter().getY() > yTo || from.getCenter().getY()< yTo+to.getHeight()) {
            System.out.println("Provera za levu stranu" + to.getX() + " " + from.getX());
            if (to.getCenter().getX() > from.getCenter().getX()) {
                System.out.println("Leva Strana");
                xTo = to.getX();  // Change this line to set xTo to the center directly
                yTo = to.getCenter().getY();
            } else {
                System.out.println("Desna Strana");
                xTo = to.getX() + to.getWidth();  // Change this line to set xTo to the center directly
                yTo = to.getCenter().getY();
            }
        }
        else{
            if (to.getCenter().getY() > from.getCenter().getY()) {
                System.out.println("Gornja Strana");
                xTo = to.getCenter().getX();
                yTo = to.getY();  // Change this line to set yTo to the center directly
            } else {
                System.out.println("Donja Strana");
                xTo = to.getCenter().getX();
                yTo = to.getY() + to.getHeight();  // Change this line to set yTo to the center directly
            }
        }

        changedIt();
    }
    public void setTo(InterClass toTopic){

        this.to = toTopic;
        xTo = to.getCenter().getX();
        yTo = to.getCenter().getY();
    }

    //treba nam kada pomeramo topic da preracunamo nove pocetne ili krajnje koordinate
    public void recalculateCoordinates(){
        if(to == null) return;
        xFrom = from.getX() + (from.getWidth() / 2);
        yFrom = from.getY() + (from.getHeight() / 2);
        setToPoint();
        //changedIt();
    }

    @Override
    public boolean equals(Object obj) {
        if(!(obj instanceof Connection)) return false;
        Connection a = (Connection) obj;
        if(this.to == null)
            return this.from.equals(a.from);
        return this.from.equals(a.from) && this.to.equals(a.to) ||
                this.from.equals(a.to) && this.to.equals(a.from);
    }

}
