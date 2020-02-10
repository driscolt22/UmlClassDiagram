package app_model;

import java.util.Vector;

public class AppModel{

    private Vector<AppListener> listeners;

    public AppModel(){
        listeners = new Vector<AppListener>();
    }

    public void addListener(AppListener l)
    {
        listeners.add(l);
    }

    public void removeListener(AppListener l)
    {
        listeners.remove(l);
    }

    private void notifyListeners()
    {
        for (AppListener l : listeners) {
            l.update();
        }
    }
}
