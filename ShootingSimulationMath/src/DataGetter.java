import java.util.ArrayList;

public class DataGetter {
    public static String getNewData(int x, int y, int testNumber) {
        // Givens
        double robotAngle = RobotSimulator.getRobotMovementAngle(x, y);
        double robotVelocity = RobotSimulator.getSpeed(x, y);
        double distanceFromHub = RobotSimulator.getDistanceFromHub(x, y);
        double robotFacingAngle = RobotSimulator.getRobotFacingAngle(x, y);

        MovementCorrector movementCorrector = new MovementCorrector(robotAngle, robotVelocity, distanceFromHub, robotFacingAngle);
        double deltaAngle = movementCorrector.getDeltaAngle();
        double newDistance = movementCorrector.getNewDistance();

        DataReader dataGetter = new DataReader("data.txt");
        int xPointsFromCenter = dataGetter.getInt("x_points_from_center");
        int yPointsFromCenter = dataGetter.getInt("y_points_from_center");
        // TODO get help with finding these! Right now it ain't working
        double deltaX =  xPointsFromCenter + movementCorrector.getXOffset();
        double deltaY = -movementCorrector.getYOffset();

        Point endPoint = new Point(deltaX, deltaY);
        Point startPoint = new Point(DataGetter.convertToGUIX(x), DataGetter.convertToGUIY(y));
        String lineStart = "test_number"+testNumber;

        deltaAngle = Math.toDegrees(deltaAngle);
        String endPointLine = lineStart+"end_point:["+endPoint.x+","+endPoint.y+",]"+"\n";
        String startPointLine = lineStart+"start_point:["+startPoint.x+","+startPoint.y+",]"+"\n";
        String startDistanceLine = lineStart+"start_distance:"+distanceFromHub+"\n";
        String deltaAngleLine = lineStart+"delta_angle:"+deltaAngle+"\n";
        String endDistanceLine = lineStart+"end_distance:"+newDistance+"\n";

        return endDistanceLine+startDistanceLine+deltaAngleLine+endPointLine+startPointLine;
    }
    // TODO constantly update these with accordance to array generator
    public static double convertToGUIX(double x) {
        DataReader dataGetter = new DataReader("data.txt");
        int xPointsFromCenter = dataGetter.getInt("x_points_from_center");
        return x + xPointsFromCenter;
    }
    public static double convertToGUIY(double y) {
        DataReader dataGetter = new DataReader("data.txt");
        int yPointsFromCenter = dataGetter.getInt("y_points_from_center");
        return (y - yPointsFromCenter) * -1;
    }
}
