// BEGIN
package exercise;

public class Flat implements Home {
    private double area;
    private double balconyArea;
    private int floor;

    public Flat(double area, double balconyArea, int floor) {
        this.area = area;
        this.balconyArea = balconyArea;
        this.floor = floor;
    }

    public String toString() {
        return String.format("Квартира площадью %s метров на %s этаже", getArea(), floor);
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
        return (area + balconyArea);
    }

}
// END
