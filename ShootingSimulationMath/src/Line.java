public class Line {
    Point startPoint;
    Point endPoint;
    double slope;
    double yIntercept;
    public Line(Point startPoint, Point endPoint) {
        this.startPoint = startPoint;
        this.endPoint = endPoint;

        slope = (startPoint.y - endPoint.y) / (startPoint.x - endPoint.x);
        yIntercept = startPoint.y - slope * startPoint.x;
    }

    public double getY(double x) {
        return slope * x + yIntercept;
    }

}
