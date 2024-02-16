package raf.dsw.classycraft.app.gui.swing.state;

import lombok.Getter;
import lombok.Setter;
import raf.dsw.classycraft.app.gui.swing.desktop.view.DijagramView;
import raf.dsw.classycraft.app.gui.swing.desktop.view.PackageView;
import raf.dsw.classycraft.app.gui.swing.view.MainFrame;
@Getter
@Setter
public class StateManager {

    private NewClassState newClassState;
    private NewConnectionState newConnectionState;
    private DeleteState deleteState;
    private SelectState selectState;
    private MoveState moveState;
    private EditState editState;
    private ZoomInState zoomInState;
    private ZoomOutState zoomOutState;
    private PanState panState;

    private State currState;


    public StateManager() {
        newClassState = new NewClassState();
        newConnectionState = new NewConnectionState();
        deleteState = new DeleteState();
        selectState = new SelectState();
        moveState = new MoveState();
        editState = new EditState();
        panState = new PanState();
        zoomInState = new ZoomInState();
        zoomOutState = new ZoomOutState();
        currState = newClassState;
    }

    public void setPanState() {
        currState = panState;
        System.out.println(currState);
    }

    public void setNewClassState(){
        currState = newClassState;
        System.out.println(currState);
        //removeSelection();
    }

    public void setNewConnectionState(){
        currState = newConnectionState;
        System.out.println(currState);
        removeSelection();
    }

    public void setDeleteState(){
        currState = deleteState;
        System.out.println(currState);
    }

    public void setSelectState(){
        currState = selectState;
        System.out.println(currState);
    }

    public void setMoveState() {
        currState = moveState;
        System.out.println(currState);
    }

    public void setEditState() {
        currState = editState;
        System.out.println(currState);
    }

    private void removeSelection(){
       DijagramView dv =  MainFrame.getInstance().getDesktopPanel().getSelectedDiagramView();
       dv.deselect();
    }
    public void setZoomInState() {
        currState = zoomInState;
        System.out.println(currState);
    }

    public void setZoomOutState() {
        currState = zoomOutState;
        System.out.println(currState);
    }




}
