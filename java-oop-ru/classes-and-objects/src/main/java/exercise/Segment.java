package exercise;

// BEGIN
public class Segment {
    private Point begin;
    private Point end;

    public Segment(Point begin, Point end) {
        this.begin = begin;
        this.end = end;
    }

    public Point getBeginPoint() {
        return begin;
    }
    public Point getEndPoint() {
        return end;
    }
    public Point getMidPoint() {
        int xPoint = (this.begin.getX() + this.end.getX()) / 2;
        int yPoint = (this.begin.getY() + this.end.getY()) / 2;
        return new Point(xPoint, yPoint);
    }
}
// END
