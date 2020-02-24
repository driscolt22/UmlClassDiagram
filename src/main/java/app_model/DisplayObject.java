package app_model;

/**
 * Represents an object inside the appmodel.
 */
public interface DisplayObject{
    /**
     * @param v an display object vistor
     */
    public void accept(Visitor v);
    /**
     * @param  x
     * @param  y
     * @return   Checks if the given x,y is inside this
     */
    public boolean contains(int x, int y);
}
