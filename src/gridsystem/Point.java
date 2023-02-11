package gridsystem;

/**
 * The Class which gives a Representation of an equation line within in the Grid
 * 
 * @author Karl
 */
public class Point {

    /* one set of coordinates that represents the line in the grid */
    private int firstX;
    private int firstY;
    /* the other set of coordinates that represent the line in the grid */    
    private int lastX;
    private int lastY;

    /*
     * Initialise a new Point
     * 
     * @param fx the first x coordinate of the line
     * @param fy the first y coordinate of the line
     * @param lx the last x coordinate of the line
     * @param ly the last y coordinate of the line
     */
    
    public Point(int fx, int fy, int lx, int ly){
        // sets the parameters to the desinated fields
        this.firstX = fx;
        this.firstY = fy;
        this.lastX = lx;
        this.lastY = ly;
    }

    /*
     * Returns the first x coordinate of the Line
     */ 
    public int getFirstX(){
        return firstX;
    }

    /*
     * Returns the first y coordinate of the Line
     */
    public int getFirstY(){
        return firstY;
    }

    /*
     * Returns the last x coordinate of the Line
     */
    public int getLastX() {
        return lastX;
    }

    /*
     * Returns the last y coordinate of the Line
     */
    public int getLastY() {
        return lastY;
    }

}
