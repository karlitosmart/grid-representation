package gridsystem;

/**
 * The representation of each line as it's information is applied to the grid 
 * 
 * @author Karl
 */
public class Line {
    private int xMultiple;
    private int yMultiple;
    private int leftSide;
    private int answer;
    private String inequality;
    private String sign;

    /*
     * Initialise a new Line
     * 
     * @param xMulitiple the x multiple of the line
     * @param sign the sign of the line
     * @param yMultiple the y multiple of the line 
     * @param inequality the inequality sign of the line
     * @param answer the constant in the line
     */
    public Line(int xMultiple, String sign, int yMultiple, String inequality, int answer ){
        this.xMultiple = xMultiple;
        this.sign = sign;
        this.yMultiple = yMultiple;
        this.inequality = inequality;
        this.answer = answer;
        leftSide = 0;
    }
    /*
     * caluation of the left side of the line in accordance to the x and y coordinates
     * 
     * @param x the x coordinate
     * @param y the y coordinate
     */ 
    public void calculateLeftSide(int x, int y){
        
        if(sign.equals("+")){
            leftSide = xMultiple*x + yMultiple*y;
        }else if (sign.equals("-")){
            leftSide = xMultiple*x - yMultiple*y;
        }     
    }
    
    /*
     * return if the coordinate is within the inequality range or not
     * 
     * @param x the x coordinate
     * @param y the y coordinate 
     */ 
    public boolean getInequality(int x, int y){
        calculateLeftSide(x,y);
        boolean z = false;
        
        if(inequality.equals("<=")){
           if(leftSide <= answer ){
               z = true;
           }
            //System.out.println(  'x + ' ++ );
        }else if(inequality.equals("<")){
            if(leftSide < answer ){
                   z = true;
               }
        }else if(inequality.equals(">=")){
            if(leftSide >= answer ){
                   z = true;
               }
        }else if(inequality.equals(">")) {
            if(leftSide > answer){
                   z = true;
               }
        }
        return z;
    }
    
    /*
     * determines if coordinate is within the inequality range in accordance with a special case
     * 
     * @param x the x coordinate
     * @param y the y coordinate 
     */  
    public boolean specialPoint(int x, int y){
        calculateLeftSide(x,y);
        boolean z = false;
        
        if(inequality.equals("<=") || inequality.equals("<")){
           if(leftSide < answer ){
               z = true;
           }
            //System.out.println(  'x + ' ++ );
        }else if(inequality.equals(">=") || inequality.equals(">")){
            if(leftSide > answer ){
                   z = true;
               }
        }
        return z;
    }
    
    /*
     * returns the constant
     */ 
    public int getAnswer(){
        return answer;
    }
    /*
     * returns the inequality
     */ 
    public String getInequality (){
        return inequality;
    }   
    
    /*
     * returns the sign
     */ 
    public String getSign() {
        return sign;
    }
    
    /* 
     * returns the x multiple
     */ 
    public int getXMultiple() {
        return xMultiple;
    }
    /*
     * returns the y multiple
     */ 
    public int getYMultiple() {
        return yMultiple;
    }
    /*
     * returns the left side of the line
     */ 
    public int getLeftSide() {
        return leftSide;
    }
}
