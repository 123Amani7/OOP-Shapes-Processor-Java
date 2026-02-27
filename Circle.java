package uml_shape_project;

public class Circle extends Shape {
    private double raduis;

    public Circle(double r) { this("Unknown", r); }

    public Circle(String c, double r) {
        super(c);
        setRaduis(r);
    }

    public double getRaduis() { return raduis; }

    public void setRaduis(double r) {
        this.raduis = (r < 0) ? 0 : r;
    }

    public double getArea() { return Math.PI * raduis * raduis; }
    public double getPerimeter() { return 2 * Math.PI * raduis; }
    public String howtoDraw(){ return "Drawing a circle with radius " + raduis + "."; }
    
    public String toString() {
        return String.format("%s, [Radius=%.2f, Area=%.2f]", super.toString(), raduis, getArea());
    }
}