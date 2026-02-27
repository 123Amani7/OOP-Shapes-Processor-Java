package uml_shape_project;

import java.util.Date;

public abstract class Shape implements Drawable {
      
     private Date dateCreated; 
     private String color;
     
     public Shape()
     {
         this("Unknown");
     }
     
     public Shape(String color)
     {
         this.color = color;
         this.dateCreated = new Date();
     }
     
     public Date getDateCreated() 
     {
         return dateCreated;
     }
     
     public String getColor()
     {
         return color;
     }
     
     public abstract double getArea();
     public abstract double getPerimeter();
     
     
   public abstract String howtoDraw();

     
     public String toString()
     {
         return String.format("Shape Details : [Type : %s, Color: %s, Date Created: %s ]",
         
         this.getClass().getSimpleName(),
         this.getColor(),
         this.getDateCreated());
     }
      
}