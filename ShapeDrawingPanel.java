package uml_shape_project;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JPanel;

public class ShapeDrawingPanel extends JPanel {

    private List<Drawable> shapes = new ArrayList<>();
    private final int MAX_SHAPES_PER_ROW = 5; 
    private final int HORIZONTAL_OFFSET = 280;
    private final int VERTICAL_OFFSET = 300; 

    public ShapeDrawingPanel(Drawable[] shapesArray) {
        setShapes(shapesArray);
    }
    
    public void setShapes(Drawable[] shapesArray) {
        shapes.clear();
        for (Drawable d : shapesArray) if (d != null) shapes.add(d);
        repaint(); 
    }

    
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        
        int horizonY = (int)(getHeight() * 0.75);
        g2.setPaint(new GradientPaint(0, 0, new Color(173, 216, 230), 0, horizonY, new Color(135, 206, 250)));
        g2.fillRect(0, 0, getWidth(), horizonY);
        g2.setColor(new Color(102, 153, 0)); 
        g2.fillRect(0, horizonY, getWidth(), getHeight() - horizonY);

       
        g2.setColor(Color.WHITE);
        for(int i=0; i<5; i++) drawCloud(g2, 50 + i*320, 40 + (i%2)*30, 80, 40);

        List<Circle> circles = new ArrayList<>();
        List<Cube> cubes = new ArrayList<>();
        for (Drawable d : shapes) {
            if (d instanceof Circle) circles.add((Circle) d);
            else if (d instanceof Cube) cubes.add((Cube) d);
        }

      
        for (int i = 0; i < circles.size() && i < 5; i++) {
            drawShape(g2, circles.get(i), 10 + i * HORIZONTAL_OFFSET, 50);
        }

        
        for (int i = 0; i < cubes.size() && i < 5; i++) {
            drawShape(g2, cubes.get(i), 10 + i * HORIZONTAL_OFFSET, 50 + VERTICAL_OFFSET);
        }
    }

    private void drawCloud(Graphics2D g2, int x, int y, int w, int h) {
        g2.fillOval(x, y, w, h); g2.fillOval(x+20, y-15, w, h); g2.fillOval(x+40, y, w, h);
    }

    private void drawShape(Graphics2D g2, Drawable drawable, int x, int y) {
        g2.setFont(new Font("SansSerif", Font.BOLD, 16));
        g2.setColor(Color.BLACK);

        if (drawable instanceof Circle) {
            Circle circle = (Circle) drawable;
            int diameter = (int) (circle.getRaduis() * 7); 
            int shape_x = x + 100 - diameter / 2;
            int shape_y = y + 50;

            g2.drawString(String.format("Circle (R: %.2f)", circle.getRaduis()), x + 50, y);
            
         
            g2.setColor(new Color(0, 0, 0, 50));
            g2.fillOval(shape_x + 5, shape_y + diameter, diameter, 10);

          
            GradientPaint gp = new GradientPaint(shape_x, shape_y, Color.CYAN, shape_x + diameter, shape_y + diameter, Color.BLUE);
            g2.setPaint(gp);
            g2.fillOval(shape_x, shape_y, diameter, diameter);
            g2.setColor(Color.BLACK);
            g2.drawOval(shape_x, shape_y, diameter, diameter);

        } else if (drawable instanceof Cube) {
            Cube cube = (Cube) drawable;
            int side = (int) (cube.getSide() * 1.5);
            int offset = side / 3;
            int shape_x = x + 100 - (side + offset) / 2;
            int shape_y = y + 50;

            g2.drawString(String.format("Cube (S: %.2f)", cube.getSide()), x + 50, y);
            
            
            g2.setColor(new Color(0, 0, 0, 70));
            g2.fillOval(shape_x + 5, shape_y + side + offset / 2, side, 10);

         
            g2.setColor(new Color(255, 100, 100)); 
            int[] xTop = { shape_x, shape_x + side, shape_x + side + offset, shape_x + offset };
            int[] yTop = { shape_y, shape_y, shape_y - offset, shape_y - offset };
            g2.fillPolygon(xTop, yTop, 4);

           
            g2.setColor(new Color(180, 0, 0)); 
            int[] xSide = { shape_x + side, shape_x + side + offset, shape_x + side + offset, shape_x + side };
            int[] ySide = { shape_y, shape_y - offset, shape_y + side - offset, shape_y + side };
            g2.fillPolygon(xSide, ySide, 4);

          
            g2.setColor(Color.RED);
            g2.fillRect(shape_x, shape_y, side, side);

        
            g2.setColor(Color.BLACK);
            g2.drawRect(shape_x, shape_y, side, side);
            g2.drawPolygon(xTop, yTop, 4);
            g2.drawPolygon(xSide, ySide, 4);
        }
    }
}