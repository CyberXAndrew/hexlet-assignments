// BEGIN
package exercise;

public class Cottage implements Home {
    private double area;
    private int floorCount;

    public Cottage(double area, int floorCount) {
        this.area = area;
        this.floorCount = floorCount;
    }

    public String toString() {
        return String.format("%s этажный коттедж площадью %s метров", floorCount, getArea());
    }

    public int compareTo(Home another) {
        int returnIndex = -2;
        if ((another.getArea()) == (getArea())) {
            returnIndex = 0;
        } else if ((another.getArea()) < (getArea())) {
            returnIndex = -1;
        } else if ((another.getArea()) > (getArea())) {
            returnIndex = 1;
        }
        return returnIndex;
    }

    public double getArea() {
        return area;
    }
}
// END
