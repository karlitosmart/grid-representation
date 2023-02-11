/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package gridsystem;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Reads a file of inequalities and produces a table of results containing how 
 * many cells the inequalites covered in accordance to its graduality 
 * 
 * Inequalites interpreted are in the format: x + y >= a
 *
 * @author Karl
 */
public class ResultsHandler {
    
    private Grid grid;
    private static final String EXAMPLE_FILE = "examples.txt";
    private ArrayList resultStorage;
    private int counter = 0;

    /*
     * Initialise a new ResultHandler
     */
    public ResultsHandler (){

        System.out.println("Creating Grid");
        grid = new Grid();
        grid.createGrid(64, 1);
        System.out.println("Grid Created...");

        resultStorage = new ArrayList();

    }

    /*
     * reads a txt file containing inequalities
     */ 
    public void result() throws IOException {
        String lineCount = "Done";
        Scanner reader = null;

        try {
            System.out.println("Checking....");
            reader = new Scanner (new BufferedReader(new FileReader(EXAMPLE_FILE)));

            while (reader.hasNextLine() == true){
                processLine(reader.nextLine());
                grid.reset(); 
                reader.nextLine();
            }       

        } catch (FileNotFoundException e) {            
            System.out.println("Unable to find the file: " + EXAMPLE_FILE);
        } catch (IOException e) {
            System.out.println("Error Encountered reading the file: " + EXAMPLE_FILE);
        } finally {
            reader.close();
            writer();
        }
        System.out.print(lineCount);     
    }

    /*
     * processes each line within the file
     */ 
    public void processLine(String line){
        int x = 0; 
        int y = 0; 
        int answer = 0;

        int count = 0;
        line.trim();

        if(line.contains("pair")){
            String thing = "hi";
        } else if (line.contains("[") && line.contains("]")) {
            String re3=".*?";	// Non-greedy match on filler
            String re4="(-?[0-9]*)";	// Any Single Character 3

            Pattern p = Pattern.compile(re3+re4+re3,Pattern.CASE_INSENSITIVE | Pattern.DOTALL);
            Matcher m = p.matcher(line); 

            while (m.find()){                    
                String c1 = m.group();

                if (count == 0 && c1.hashCode() != 0 ){
                    x = Integer.parseInt(c1);
                    count++;
                }else if (count == 1 && c1.hashCode() != 0){
                    y = Integer.parseInt(c1);
                    count++;
                } else if(count == 2 && c1.hashCode() != 0){
                    answer = Integer.parseInt(c1);
                    count++;
                } 

                if(count == 3) {
                    grid.fillGrid(x, "+", y, ">=", answer);
                    count = 0;
                }

            }
            resultStorage.add(grid.countCells(1));
            resultStorage.add(grid.countCells(2));
            resultStorage.add(grid.countCells(4));
            resultStorage.add(grid.countCells(8));
            resultStorage.add(grid.countCells(16));
            resultStorage.add(grid.countCells(32));
            resultStorage.add("\n");
            counter = 0;
        } else {
            if(counter == 0){              
                resultStorage.add(line);
                counter++;
            } else {
                resultStorage.add("\n");
                resultStorage.add(line);
                counter++;
            }
        }
    }

    /* 
     * writes results of processed txt file in a seperate txt file
     */ 
    public void writer() throws IOException{

        String top = "Ex No" + "\t" + "1" + "\t" + "2" + "\t" + "4" + "\t" + "8" + "\t" + "16" + "\t" + "32";
        String thing = "----------------------------------------------------------------";
        String newLine = "\n";

        FileWriter writer = null; 
        writer = new FileWriter("results.txt");
        try {                       
            writer.write(top);
            writer.write(newLine);
            writer.write(thing);
            writer.write(newLine);

            for(Object item : resultStorage){

                if(item != "\n"){
                    writer.write(item.toString());                
                    writer.write("\t");
                } else {
                    writer.write(item.toString());
                }
            }
        } catch (IOException e){
            System.out.println("bakkka!!!");
        } finally {
            writer.close();
        }
    }

}
