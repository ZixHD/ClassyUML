package raf.dsw.classycraft.app.gui.swing.commands;

import raf.dsw.classycraft.app.core.ApplicationFramework;

import java.util.ArrayList;
import java.util.List;

public class CommandManager {
    private List<AbstractCommand> commands = new ArrayList<>();
    private int currCommand = 0;

    //Dodaje novu komandu na stek i poziva izvršavanje komande
    public void addCommand(AbstractCommand command){
        while(currCommand < commands.size())
            commands.remove(currCommand);
        commands.add(command);
        doCommand();
    }

    //Metoda koja poziva izvršavanje konkretne komande
    public void doCommand(){
        if(currCommand < commands.size()){
            commands.get(currCommand++).doCommand();
            ApplicationFramework.getInstance().getGui().enableUndoAction();
        }
    }

    //Metoda koja poziva redo konkretne komande
    public void undoCommand(){
        if(currCommand > 0){
            ApplicationFramework.getInstance().getGui().enableRedoAction();
            commands.get(--currCommand).undoCommand();
        }
    }
}
