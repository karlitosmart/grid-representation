package gridsystem;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import javax.swing.JFrame;

/**
 * The class in charge of the visual output of the system
 * Uses the information calculated and accumlated in the grid class and visually
 * represnts the information in a clear and innotive way that can be understood 
 * by the user 
 * 
 * @author  Karl
 */
public class Output extends javax.swing.JPanel {
    /* the size of each cell in the visual representation */
    private int CELL_SIZE;
    /* the grid */
    private Grid grid;
    /* the size of the grid */
    private int outputSize;
    /* how many cells are within the grid */
    private int outputSpace;

    /*
     * Initialise a new Output
     * 
     * @ param grid the grid instance that will be representing
     */ 
    public Output(Grid grid) {
               
        this.grid = grid;
        setOutputSize();
        
        CELL_SIZE = 30 * grid.getGranduality(); // the size of the grid is dependant on the girds' granduality
        
        setPreferredSize(new Dimension(CELL_SIZE * outputSize , CELL_SIZE * outputSize));
        setFocusable(true);
        JFrame frame = new javax.swing.JFrame("Grid Representation (Output)");
        frame.setAlwaysOnTop(true);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.add(this);
        frame.pack();
        frame.setVisible(true);                
    }
    /*
     * Draws the output representation
     */ 
    @Override
    public void paintComponent(Graphics g){
        super.paintComponent(g);
        int x = 0;
        int y = 0;

        colour(g);

        while(y <= outputSize - 1){
            while (x <= outputSize - 1 ){ 
                drawSquares(g,x,(outputSize-y-1));
                x++;
            }
            y++;
            x = 0;
        }                                
        if(grid.getPoints().size() > 0){
            drawEquations(g);
        }
    }
    
    /*
     * method to colour the cells in the grid which are within the range 
     * of the inequality
     */ 
    public void colour(Graphics g){
    int x = 0;
    int y = 0;
    
    while (y <= outputSize - 1){
        while (x <= outputSize - 1){
            if(grid.getGranduality() == 1 ){
                if( grid.getGrid().get(((grid.getSize()*y+x))).getCoordinateContents() == true){ 
                    colourSquare(g,x,y); 
                }               
            } else if (grid.getGranduality() > 1) {
                int s = 0;
                int t = 0;
                // colours cells in the grid in terms of the grids granduality    
                while(s < grid.getGranduality()){
                        while (t < grid.getGranduality()){
                            if (grid.getGrid().get(((grid.getSize()*(y+s)+(x+t)))).getCoordinateContents() == true){
                                colourSquare(g,(x/grid.getGranduality()),(y/grid.getGranduality()));
                                s = grid.getGranduality() + 1;
                                t = grid.getGranduality() + 1;                    
                            }
                            t++;                    
                        }
                    t = 0;
                    s++;
                }
            }
            x = x + grid.getGranduality();
            }
        x = 0;
        y = y + grid.getGranduality();
    }
    }

    /*
     * Determines and sets the output size and space for the representation
     */ 
    public void setOutputSize(){
        outputSize = this.grid.getSize() / this.grid.getGranduality();
        outputSpace = outputSize * outputSize;
    }
    
    /*
     * draw the cell outline within the grid by drawing sqauares in paticular 
     * positions
     * 
     * @param x the x position of the square
     * @param y the y position of the square
     */ 
    public void drawSquares(Graphics g, int x, int y){       
        g.setColor(Color.BLACK);
        g.drawRect(x * CELL_SIZE, y * CELL_SIZE, CELL_SIZE, CELL_SIZE);       
    }
    
     /*
     * draw the filled cell within the grid by drawing coloured sqauares in paticular 
     * positions
     * 
     * @param x the x position of the coloured square
     * @param y the y position of the coloured square
     */ 
    public void colourSquare(Graphics g, int x, int y){        
        int z = outputSize-y-1;
        g.setColor(Color.MAGENTA);            
        g.fillRect(x * CELL_SIZE, z * CELL_SIZE, CELL_SIZE, CELL_SIZE);           
    }
    
    /*
     * Draws the position of the line in the grid
     */ 
    public void drawEquations(Graphics g){
        for (int point = 0; point < grid.getPoints().size() ; point++){
            int y1 = grid.getPoints().get(point).getFirstY();
            int y2 = grid.getPoints().get(point).getLastY();
            g.setColor(Color.BLUE);
            g.drawLine(grid.getPoints().get(point).getFirstX() * CELL_SIZE, 
            (grid.getSize() - y1) * CELL_SIZE, 
            grid.getPoints().get(point).getLastX() * CELL_SIZE, 
            (grid.getSize() - y2) * CELL_SIZE);
            }
        }       
  }
          
                      
