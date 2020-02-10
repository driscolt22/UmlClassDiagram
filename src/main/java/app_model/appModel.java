package app_model;

import java.util.vector;

public class AppModel{

    private vector<AppListener> listeners;

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
