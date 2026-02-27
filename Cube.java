package uml_shape_project;

public class Cube extends ThreeDShape {
    private double side;

    public Cube(double s) { this("Unknown", s); }

    public Cube(String c, double s) {
        super(c);
        setSide(s); 
    }

    public double getSide() { return side; }

    public void setSide(double s) {
        
        this.side = (s < 0) ? 0 : s;
    }

    public double getArea() { return 6 * side * side; }
    public double getPerimeter() { return 12 * side; }
    public double getVolume() { return side * side * side; }
    public String howtoDraw() { return "Drawing a cube with side length " + side + "."; }

    public String toString() {
        return String.format("%s, [Side=%.2f, Volume=%.2f]", super.toString(), side, getVolume());
    }
}