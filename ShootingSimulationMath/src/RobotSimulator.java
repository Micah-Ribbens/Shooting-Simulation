public class RobotSimulator {
    DataReader dataGetter = new DataReader("data.txt");
    int yPointsFromCenter = dataGetter.getInt("y_points_from_center");
    int xPointsFromCenter = dataGetter.getInt("x_points_from_center");
    int distanceFromHub = dataGetter.getInt("distance_from_hub");
    int hubRadius = dataGetter.getInt("hub_height");
    int distanceToHubCenter = distanceFromHub + hubRadius;

    public double getSpeed(int deltaX, int deltaY) {
        boolean isNegative;
        if (deltaX == 0) {
            isNegative = deltaY < 0;
        }
        else if (deltaY == 0) {
            isNegative = deltaX < 0;
        }
        else {
            isNegative = (deltaX / deltaY) < 0;
        }
        double distance = Math.sqrt(deltaX * deltaX + deltaY * deltaY);
        // Assumes it takes one second to go that far
        return isNegative ? -distance : distance;
    }

    public double getRobotMovementAngle(int deltaX, int deltaY) {
        if (deltaX == 0) {
            return deltaY > 0 ? 90 : -90;
        }
        return Math.atan(deltaY / deltaX);
    }
    /** @return the angle that the robot is facing in order to look at the hub*/
    public double getRobotFacingAngle(int x, int y) {
        // Not neeeded for now
        return 0.0;
//        Point hubPosition = getHubPosition();
//        int yDistanceFromHub = (int) hubPosition.y - y;
//        int xDistanceFromHub = x - xPointsFromCenter;
//
//        return Math.atan(xDistanceFromHub / yDistanceFromHub);
    }

    public double getDistanceFromHub(int x, int y) {
        return Math.sqrt(Math.pow(x, 2) + Math.pow(y, 2));
    }

    public Point getHubPosition() {
       return new Point(0, 0);
    }
}
