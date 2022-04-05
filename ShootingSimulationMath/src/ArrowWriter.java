public class ArrowWriter {
    public static double getDeltaAngle(double robotAngle, double robotVelocity, double distanceFromHub) {
        double newDistance = getNewDistance(robotAngle, robotVelocity, distanceFromHub);
        double offset = getOffset(robotAngle, robotVelocity);

        // Using law of cosines
        double fractionNumerator = Math.pow(offset, 2) - Math.pow(newDistance, 2) - Math.pow(distanceFromHub, 2);
        double fractionDenominator = -2 * newDistance * distanceFromHub;
        return Math.acos(fractionNumerator / fractionDenominator);
    }
    public static double getNewDistance(double robotAngle, double robotVelocity, double distanceFromHub) {

        return distanceFromHub + getOffset(robotAngle, robotVelocity);
    }
    public static double getOffset(double robotAngle, double robotVelocity) {
        double xVelocityComponent = robotVelocity * Math.sin(robotAngle);
        double yVelocityComponent = robotVelocity + Math.cos(robotAngle);

        // First number is in ft/s and second number is number of feet offset for the points
        // Negative is left and positive is right; negative is down and positive is up
        Line xVelocityToOffset = new Line(new Point(0, 0), new Point(20, -3));
        Line yVelocityToOffset = new Line(new Point(0, 0), new Point(20, -3));

        double xOffset = xVelocityToOffset.getY(xVelocityComponent);
        double yOffset = yVelocityToOffset.getY(yVelocityComponent);

        return Math.sqrt(Math.pow(xOffset, 2) + Math.pow(yOffset, 2));
    }
}
