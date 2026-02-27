package uml_shape_project;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;
import java.util.List;
import java.util.ArrayList;

public class TestDriver {

    private static final String INPUT_FILE = "input.txt";
    private static final String OUTPUT_FILE = "sumAreas.txt";

    public static void main(String[] args) {
        ShapeGUI.startGUI();
    }
    
    public static String readInputFile(List<Drawable> shapesList) {
        shapesList.clear();
        StringBuilder log = new StringBuilder();
        int arraySize = 0;

        log.append("--- Starting file read: ").append(INPUT_FILE).append(" ---\n");

        try (Scanner fileScanner = new Scanner(new File(INPUT_FILE))) {
            if (fileScanner.hasNextInt()) {
                arraySize = fileScanner.nextInt();
                
                
                if (arraySize < 0) {
                    log.append("Error: Array size cannot be negative (Found: ").append(arraySize).append(")\n");
                    return log.toString();
                }
                
                log.append("Array Size: ").append(arraySize).append("\n");
                fileScanner.nextLine();
            } else {
                log.append("Error: Array size not found in the first line.\n");
                return log.toString();
            }
            
            for (int i = 0; i < arraySize; i++){
                if (fileScanner.hasNextLine()) {
                    String line = fileScanner.nextLine().trim();
                    String[] parts = line.split(" ");
                    if (parts.length < 2) continue;
                    
                    String shapeType = parts[0].toLowerCase();
                    double dimension = Double.parseDouble(parts[1]);

                 
                    if (dimension < 0) {
                        log.append("Error at shape ").append(i+1).append(": Dimension cannot be negative (Found: ").append(dimension).append("). Skipping shape.\n");
                        continue;
                    }

                    Drawable newShape = null;
                    if (shapeType.equals("circle")) {
                        newShape = new Circle("Blue", dimension);
                    } else if(shapeType.equals("cube")) {
                        newShape = new Cube("Red", dimension);
                    }
                    
                    if (newShape != null) {
                        shapesList.add(newShape);
                        log.append("Created: ").append(newShape.toString()).append("\n");
                    }
                }
            }
        } catch (FileNotFoundException | NumberFormatException e){
            log.append("\nError: ").append(e.getMessage()).append("\n");
        }
        
        log.append("--- File read finished. ").append(shapesList.size()).append(" shapes loaded. ---\n");
        return log.toString();
    }

    public static String calculateAreasAndVolumes(List<Drawable> shapesList) {
        StringBuilder log = new StringBuilder();
        double totalArea = 0.0;
        double totalVolume = 0.0;
        log.append("\n--- Starting area and volume summation ---\n");
        
        for (Drawable item : shapesList) {
            if (item instanceof Shape) {
                Shape shape = (Shape) item;
                double area = shape.getArea();
                totalArea += area;
                log.append(String.format("Area of %s: %.2f\n", shape.getClass().getSimpleName(), area));
                if (item instanceof ThreeDShape) {
                    totalVolume += ((ThreeDShape) item).getVolume();
                }
            }
        }
        log.append(String.format("\nTotal Area: %.2f\nTotal Volume: %.2f\n", totalArea, totalVolume));
        
        try(PrintWriter printWriter = new PrintWriter(new FileWriter(OUTPUT_FILE))){
            printWriter.printf("%.2f\n", totalArea);
            log.append("Total area written to ").append(OUTPUT_FILE).append("\n");
        } catch (IOException e) {
            log.append("Error writing to file: ").append(e.getMessage()).append("\n");
        }
        return log.toString();
    }
}