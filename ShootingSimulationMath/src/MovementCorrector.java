// TODO go through and debug to see what is wrong try step by step because something is veeeeeery wrong!
public class MovementCorrector {
    double robotMovementAngle;
    double robotVelocity;
    double distanceFromHub;
    double robotFacingAngle;
    public MovementCorrector(double robotMovementAngle, double robotVelocity, double distanceFromHub, double robotFacingAngle) {
        this.robotMovementAngle = robotMovementAngle;
        this.robotVelocity = robotVelocity;
        this.distanceFromHub = distanceFromHub;
        this.robotFacingAngle = robotFacingAngle;
    }
    public double round(double number, int numberPlace) {
        int temp = (int) (number * Math.pow(numberPlace, 10));
        return temp / Math.pow(numberPlace, 10);
    }
    public double getDeltaAngle() {
        double newDistance = getNewDistance();
        double offset = getOffsetDistance();

        // Using law of cosines
        double fractionNumerator = Math.pow(offset, 2) - Math.pow(newDistance, 2) - Math.pow(distanceFromHub, 2);
        double fractionDenominator = -2 * newDistance * distanceFromHub;
        double fraction = round(fractionNumerator / fractionDenominator, 6);
        return Math.acos(fraction);
    }
    public double getNewDistance() {
        double xOffset = getXOffset();
        double yOffset = getYOffset();

        // Splitting up the distance from the hub assuming that the robot's position is (0, 0)
        // Into x and y components to get the current point of where the robot is shooting
        double currentYDistance = distanceFromHub * Math.cos(robotFacingAngle);
        double currentXDistance = distanceFromHub * Math.sin(robotFacingAngle);
        Point newPoint = new Point(currentXDistance + xOffset, currentYDistance + yOffset);

        // Distance from the robot to the new point of shooting- robot is at (0, 0)
        return getDistance(new Point(0, 0), newPoint);
    }
    public double getOffsetDistance() {
        double xOffset = getXOffset();
        double yOffset = getYOffset();
        // Getting the distance from the hub center (0, 0) to the points from the offset
        return getDistance(new Point(0, 0), new Point(xOffset, yOffset));
    }
    public double getXOffset() {
        double xVelocityComponent = robotVelocity * Math.sin(robotMovementAngle);
        // First number is in ft/s and second number is number of feet offset for the points; left is negative and right is positive
        // NOTE: these are in grid points, which right now we are assuming is 10ft, so it is an easy conversion
        Line xVelocityToOffset = new Line(new Point(0, 0), new Point(2, -.3));
        return xVelocityToOffset.getY(xVelocityComponent);
    }
    public double getYOffset() {
        double yVelocityComponent = robotVelocity * Math.cos(robotMovementAngle);

        // First number is in ft/s and second number is number of feet offset for the point negative is down and positive is up
        // NOTE: these are in grid points, which right now we are assuming is 10ft, so it is an easy conversion
        Line yVelocityToOffset = new Line(new Point(0, 0), new Point(2, -.3));

        return yVelocityToOffset.getY(yVelocityComponent);
    }
    public double getDistance(Point point1, Point point2) {
        return Math.sqrt(Math.pow(point1.x - point2.x, 2) + Math.pow(point1.y - point2.y, 2));
    }
}
