package gridsystem;
import java.util.ArrayList;
/**
 * 
 * @author Karl
 */
public class Grid {
    
    private ArrayList<Coordinate> grid;
    private ArrayList<Line> line;
    private ArrayList<Point> points;
    private int granduality;
    private int size;
    private int space;
    private Coordinate coordinate;
    private boolean outputVisable = false; 

    /*
     * Initialise a new Grid
     */
    public Grid(){
        grid = new ArrayList<Coordinate>();
        line = new ArrayList<Line>();
        points = new ArrayList<Point>();
        this.granduality = 1;
    }
    
    /*
     * creates a new grid depending on the size and granduality
     * 
     * @param size the size of the grid
     * @param grad the graduality of the grid
     */
    public void createGrid(int size, int grand){       
        
        
        if(outputVisable == false){
            this.size = size;
            granduality = grand;
            createSpace();
            
            int x = 0;
            int y = 0;
                    
        for (int space2 = getSpace(); space2 >= 1 ; space2--) {
            while (y <= size - 1){    
                while (x <= size - 1){
                    //while (x >= 0){
                        coordinate = new Coordinate(size*y+x, false);
                        grid.add(coordinate);
                        //System.out.println( coordinate.getCoordinateNumber());
                        //space++;
                        //x--;
                        x++;
                    }
                    //y--;
                    y++;
                    x = 0;
                    //x = size - 1;
                }
            }
        outputVisable = true;
        System.out.println("size: " + size);
        }else if (outputVisable == true){
            System.out.println("A Grid Representation is already open");
            System.out.println("size: " + size);
            
        }
    }
    
    /*
     * Calculates the amount of blocks necessary for the grid
     */ 
    public void createSpace(){
        this.space = size*size;
    }
    
    /*
     * creates and sets the indiviual cells within the grid 
     * 
     * @param coordiateNumber the index number of teh cell in the grid
     * @param bool determines if the cell is within the inequality or not
     */ 
    public void setCoordinate(int coordiateNumber, boolean bool){
        Coordinate current = new Coordinate(coordiateNumber, bool);
        grid.set(coordiateNumber, current);
    }
    
    /*
     * Creates a new Line Class, adds it to the array list and applies it to the grid
     * 
     * @param xMulitiple the x multiple of the line
     * @param sign the sign of the line
     * @param yMultiple the y multiple of the line 
     * @param inequality the inequality sign of the line
     * @param answer the constant in the line
     */ 
    public void fillGrid(int xMultiple, String sign, int yMultiple, String inequality, int answer){
          Line newLine = new Line(xMultiple, sign, yMultiple, inequality, answer);
          calculatePoints(newLine);
          line.add(newLine);
          insertLine(line);
    }

    /*
     * Applies an array of Line classes on to the grid
     * 
     * @param line a list of Line
     */ 
    public void insertLine(ArrayList<Line> line){
        
        int x = size - 1;
        int y = size - 1;
       // int index = 0;
    
                while(y >= 0){
                while( x >= 0){
                    Coordinate current = grid.get((size*y+x));
                    if(line.size() == 0){
                        System.out.println("There are no lines");
                    }
                    if(line.size() == 1){
                         if (current.getCoordinateContents() == false){
                             for (Line l : line){
                         //System.out.println(l.getXMultiple() + "x " + l.getSign() + " " + 
                           //      l.getYMultiple() + "y " + l.getInequality() + " " + l.getAnswer());
                         
                        
                             if(l.getInequality(x, y) == true){
                           
                                 coordinate = new Coordinate(size*y+x, true);                                                    
                                grid.set((size*y+x), coordinate);
                             //   System.out.println(coordinate.getCoordinateNumber());
                               // System.out.println(coordinate.getCoordinateContents());
                        
                             } 
                             }
                             }
                        }if (line.size() > 1){
                            if (current.getCoordinateContents() == true){
                        
                            boolean t = false; 
                            for (Line l : line){
                                 if (l.getInequality(x, y) == true && t == false){
                                     t = true;
                                 } else if(l.getInequality(x, y) == false){
                                     if(l.specialPoint(x + 1, y) == true && t == false){
                                             t = true;                                          
                                     }else if(l.specialPoint(x, y + 1) == true && t == false){
                                             t = true;
                                     } if(l.specialPoint(x + 1, y) == false && l.specialPoint(x, y + 1) == false){
                                         if(t == true){
                                             t = false;
                                         }
                                     //t = false;
                                 }
                                 
                            }
                            if(t == false){
                                coordinate = new Coordinate(size*y+x, false);                        
                                grid.set((size*y+x), coordinate);
                    }
                        }
                            }
                        }
                    x--;
                }
                y--;

                x = size - 1;
        }
    }

    /*
     * calculates the visual representation of the lines inserted to the grid
     * 
     * @param line the line which would be visually represented
     */ 
    public void calculatePoints (Line line){
        int x1 = 0; int y1 = 0;
        int x2 = 0; int y2 = 0;
        if(line.getXMultiple() == -1 && line.getYMultiple() == 1 && line.getAnswer() == 0) {
            x1 = 0;
            y1 = 0;
            
            x2 = getSize();
            y2 = getSize();
            
            Point newpoint = new Point(x1,y1,x2,y2);
            points.add(newpoint);
        
        } else if(line.getXMultiple() == 0){
            x1 = 0;
            y1 = line.getAnswer() / line.getYMultiple();
            
            x2 = getSize();
            y2 = line.getAnswer() / line.getYMultiple();
            
            //Point newpoint = new Point(x1,y1,x2,y2);
            //points.add(newpoint);
        
        } else if(line.getYMultiple() == 0){
            y1 = 0;
            x1 = line.getAnswer() / line.getXMultiple();
            
            y2 = getSize();
            x2 = line.getAnswer() / line.getXMultiple();
            
            //Point newpoint = new Point(x1,y1,x2,y2);
            //points.add(newpoint);
            
            
        }else if(line.getXMultiple() != 0 && line.getYMultiple() != 0){
        
            x1 = 0;
            y1 = line.getAnswer() / line.getYMultiple();
            y2 = 0;
            x2 = line.getAnswer() / line.getXMultiple();

            if(y1 < 0){
                x1 = getSize();
                y1 = (line.getAnswer() - line.getXMultiple()*x1) / line.getYMultiple();
            }
            if(x2 < 0){
                y2 = getSize();
                x2 = (line.getAnswer() - line.getYMultiple()*y2) / line.getXMultiple();
            }

            //Point newpoint = new Point(x1,y1,x2,y2);
            //points.add(newpoint);
        }    
        
        x1 = x1 / granduality;
        y1 = y1 / granduality;
        
        x2 = x2 / granduality;
        y1 = y1 / granduality;
                
        Point newPoint = new Point(x1,y1,x2,y2);
        points.add(newPoint);
        
    }
    
    /*
     * counts the amount of cells which are within the inequalities' range in accordance to 
     * the specific graduality
     * 
     * @param grad  the graduality specified 
     */ 
    public int countCells(int grad){
        int x = 0;
        int y = 0;
        int cellCount = 0;
        
        
        while (y <= getSize() - 1){
            while(x <= getSize() - 1){
                if(grad == 1){
                    Coordinate current = grid.get((size*y+x));
                    if (current.getCoordinateContents() == true){
                        cellCount++;                        
                    }
                } else if(grad > 1){
                    int a = 0;
                    int b = 0;
                    while(b <= grad - 1){
                        while (a <= grad - 1){
                            Coordinate current = grid.get(size*(y+b)+(x+a));
                            if (current.getCoordinateContents() == true){
                            cellCount++;
                            b = grad + 1;
                            a = grad + 1;        
                            }else{
                            a++;
                            }
                        }
                        if (a == grad + 1){
                            b = grad + 1;
                        }else{
                        b++;
                        a = 0;
                        }
                    }
                }
               // System.out.println("Wag1" + cellCount);
            x = x + grad;
            }
            x = 0;
            y = y + grad;
        }
        //System.out.print(cellCount);
      
        return cellCount;
    }
    
    /*
     * resets the grid to before any inequalties  are applied
     */
    public void reset(){
        int x = 0;
        int y = 0;
                    
        for (int space2 = getSpace(); space2 >= 1 ; space2--) {
            while (y <= size - 1){    
                while (x <= size - 1){
                    //while (x >= 0){
                    if(grid.get(size*y+x).getCoordinateContents() == false){     
                        coordinate = new Coordinate(size*y+x, false);
                        grid.set(size*y+x, coordinate);
                        //System.out.println( coordinate.getCoordinateNumber());
                    }
                        //space++;
                        //x--;
                        x++;
                    }
                    //y--;
                    y++;
                    x = 0;
                    //x = size - 1;
                }
            }
       line.clear();
       points.clear();
    }
    
    /*
     * returns if the grid is empty or not
     */ 
    public boolean getGridBoolean(){
        return grid.isEmpty();
    }
    /*
     * returns the list of cells representing the grid
     */ 
    public ArrayList<Coordinate> getGrid(){
        return grid;    
    }
    /*
     * returns the number of cells in the grid
     */ 
    public int getSpace(){
        return space;
    }
    /*
     * returns the maximum size of x and y in the grid
     */  
    public int getSize(){
        return size;
    }
    /*
     * returns the Graduality of the grid
     */ 
    public int getGranduality(){
        return granduality;
    }
    
    /*
     * returns the coordinate
     */ 
    public Coordinate getCoordinate(){
        return coordinate;
    }       

    /*
     * returns the list of points that represents the visual 
     * representation of lines in the grid
     */ 
    public ArrayList<Point> getPoints(){
        return points;
    }
    /*
     * returns the list of lines
     */ 
    public ArrayList<Line> getLineArray() {
        return line;
    }    
    
}
