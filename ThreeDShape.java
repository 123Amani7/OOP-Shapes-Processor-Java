package uml_shape_project;

public abstract class ThreeDShape extends Shape {
    
    public ThreeDShape() {
        super();
    }
    
    public ThreeDShape(String color) {
        super(color);
    }
    
    public abstract double getVolume();
}