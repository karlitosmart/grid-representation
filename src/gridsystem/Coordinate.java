package gridsystem;

/**
 * The Representation of each cell within the grid, containing the gird number 
 * and if the contents of the cell is within the inequality range or not
 * 
 * @author Karl
 */
public class Coordinate {
    
    /* the grid number of the cell within the grid */
    private int number;
    /* the boolean that determines if the cell is within the inequality range */
    private boolean contents;

    /*
     * Initialise a new Coordinate
     * 
     * @param number the grid number
     * @param bool the boolea contents
     */ 
    public Coordinate(int number, boolean bool){
        this.number = number;
        contents = bool;
    }
   
    /*
     * Returns the coordinate number of the cell
     */ 
    public int getCoordinateNumber(){
        return number;
    }
    /*
     * Returns the contents boolean of the cell
     */     
    public boolean getCoordinateContents(){
        return contents;
    }
    
            
}
