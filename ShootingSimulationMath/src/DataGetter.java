import Utils.Vector;

import java.util.ArrayList;

public class DataGetter {
        static DataReader dataGetter = new DataReader("data.txt");
        static int xPointsFromCenter = dataGetter.getInt("x_points_from_center");
        static int yPointsFromCenter = dataGetter.getInt("y_points_from_center");
    public static String getNewData(int x, int y, int testNumber) {
        // Givens
        RobotSimulator robotSimulator = new RobotSimulator();
        int deltaY = (robotSimulator.distanceToHubCenter + DataGetter.yPointsFromCenter) - y;
        int deltaX = x - DataGetter.xPointsFromCenter;

        double robotAngle = robotSimulator.getRobotMovementAngle(deltaX, deltaY);
        double robotVelocity = robotSimulator.getSpeed(deltaX, deltaY);
        double distanceFromHub = robotSimulator.getDistanceFromHub(x, y);
        double robotFacingAngle = robotSimulator.getRobotFacingAngle(x, y);

        MovementCorrector movementCorrector = new MovementCorrector(robotAngle, robotVelocity, distanceFromHub, x, y);
        double deltaAngle = movementCorrector.getDeltaAngle();
        double newDistance = movementCorrector.getNewDistance();


        Vector between = movementCorrector.getBetweenVector();
        Point endPoint = new Point(0, 0);
        Point startPoint = new Point(x, y);
        String lineStart = "test_number"+testNumber;

        deltaAngle = Math.toDegrees(deltaAngle);
        String endPointLine = lineStart+"end_point:["+between.getX()+","+between.getY()+",]"+"\n";
        String startPointLine = lineStart+"start_point:["+startPoint.x+","+startPoint.y+",]"+"\n";
        String startDistanceLine = lineStart+"start_distance:"+distanceFromHub+"\n";
        String deltaAngleLine = lineStart+"delta_angle:"+deltaAngle+"\n";
        String endDistanceLine = lineStart+"end_distance:"+newDistance+"\n";

        return endDistanceLine+startDistanceLine+deltaAngleLine+endPointLine+startPointLine;
    }
}
