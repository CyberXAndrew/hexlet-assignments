package exercise;

// BEGIN
public class Circle {
    private final double PI = 3.141592;
    private Point point;
    private int radius;
    public Circle(Point point, int radius) {
        this.point = point;
        this.radius = radius;
    }

    public int getRadius() {
        return radius;
    }
    public double getSquare() throws NegativeRadiusException {
        if (radius < 0) {
            throw new NegativeRadiusException("Радиус меньше нуля!");
        }
        return PI * (radius * radius);
    }
}
// END
