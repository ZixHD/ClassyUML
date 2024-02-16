package raf.dsw.classycraft.app.core;

import raf.dsw.classycraft.app.observer.ISubscriber;

public interface Gui extends ISubscriber {
    void start();

    void disableUndoAction();
    void disableRedoAction();

    void enableUndoAction();
    void enableRedoAction();
}
