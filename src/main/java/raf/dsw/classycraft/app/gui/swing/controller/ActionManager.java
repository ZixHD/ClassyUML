package raf.dsw.classycraft.app.gui.swing.controller;

import lombok.Getter;
import lombok.Setter;
import raf.dsw.classycraft.app.gui.swing.controller.toolbarActions.*;

@Setter
@Getter
public class ActionManager{

    private ExitAction exitAction;
    private AboutUsAction aboutUsAction;
    private NewProjectAction newProjectAction;
    private DeleteNodeAction deleteNodeAction;
    private ChangeAuthorAction changeAuthorAction;
    private NewClassAction newClassAction;
    private NewConnectionAction newAssociationAction;
    private DeleteElementAction deleteElementAction;
    private MoveElementAction moveElementAction;
    private SelectElementAction selectElementAction;
    private EditElementAction editElementAction;
    private ZoomInAction zoomInAction;
    private ZoomOutAction zoomOutAction;
    private PanAction panAction;
    private PictureAction pictureAction;
    private SaveTemplateAction saveTemplateAction;
    private SaveProjectAction saveProjectAction;

    private UndoAction undoAction;
    private RedoAction redoAction;

    public ActionManager(){
        initialiseActions();
    }


    private void initialiseActions(){
        exitAction = new ExitAction();
        aboutUsAction = new AboutUsAction();
        newProjectAction = new NewProjectAction();
        deleteNodeAction = new DeleteNodeAction();
        changeAuthorAction = new ChangeAuthorAction();

        newClassAction = new NewClassAction();
        newAssociationAction = new NewConnectionAction();
        selectElementAction = new SelectElementAction();
        moveElementAction = new MoveElementAction();
        deleteElementAction = new DeleteElementAction();
        editElementAction = new EditElementAction();
        zoomInAction = new ZoomInAction();
        zoomOutAction = new ZoomOutAction();
        panAction = new PanAction();
        undoAction = new UndoAction();
        redoAction = new RedoAction();
        pictureAction = new PictureAction();
        saveTemplateAction = new SaveTemplateAction();
        saveProjectAction = new SaveProjectAction();

    }



}