package app_model;

public interface DisplayObject{
    public void accept(Visitor v);
    public boolean contains(int x, int y);
}
