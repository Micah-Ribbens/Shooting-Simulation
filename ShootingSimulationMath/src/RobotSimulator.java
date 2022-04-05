public class RobotSimulator {
    public static double getSpeed(int x, int y) {
        double distance = Math.sqrt(x * x + y * y);
        // Assumes it takes one second to go that far
        return distance;
    }

    public static double getRobotMovementAngle(int x, int y) {
        if (y == 0) {
            return y > 0 ? 180 : -180;
        }
        if (x == 0) {
            return 0;
        }
        return Math.atan(y / x);
    }
    /** @return the angle that the robot is facing in order to look at the hub*/
    public static double getRobotFacingAngle(int x, int y) {
        Point hubPosition = RobotSimulator.getHubPosition();
        int yDistanceFromHub = (int) hubPosition.y - y;
        int xDistanceFromHub = -x;

        return Math.atan(xDistanceFromHub / yDistanceFromHub);
    }

    public static double getDistanceFromHub(int x, int y) {
        Point hubPosition = RobotSimulator.getHubPosition();
        return Math.sqrt(Math.pow(hubPosition.x - x, 2) + Math.pow(hubPosition.y - y, 2));
    }

    public static Point getHubPosition() {
        DataReader dataGetter = new DataReader("data.txt");
        int yPointsFromCenter = dataGetter.getInt("x_points_from_center");
        int distanceFromHub = dataGetter.getInt("distance_from_hub");
        int hubHeight = dataGetter.getInt("hub_height");
       return new Point(0, yPointsFromCenter + distanceFromHub + hubHeight);
    }
}
