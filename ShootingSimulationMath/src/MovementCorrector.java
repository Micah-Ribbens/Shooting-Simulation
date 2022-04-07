import Utils.LinearInterpolatedTable;
import Utils.Vector;

import java.awt.*;
import java.util.function.Supplier;

// TODO go through and debug to see what is wrong try step by step because something is veeeeeery wrong!
public class MovementCorrector {
    private double robotMovementAngle;
    private double robotVelocity;
    private double distanceFromHub;
//    private double robotFacingAngle;
    private LinearInterpolatedTable toHubShotTime;
    double gridPointToFeet = 4;
    int x;
    int y;


    public MovementCorrector(double robotMovementAngle, double robotVelocity, double distanceFromHub, int x, int y) {
        this.robotMovementAngle = robotMovementAngle;
        this.robotVelocity = robotVelocity;
        this.distanceFromHub = distanceFromHub;
        this.x = x;
        this.y = y;
//        this.robotFacingAngle = robotFacingAngle;
        toHubShotTime = new LinearInterpolatedTable(1);
        toHubShotTime.addPoint(8 / gridPointToFeet, 1);
        toHubShotTime.addPoint(21 / gridPointToFeet, 1.5);
    }
    public double getNewDistance() {
        return getBetweenVector().getMagnitude();
    }
    public Vector getBetweenVector() {
        double timeToHub = toHubShotTime.get(distanceFromHub, 1);
        Vector velocityVector = new Vector(Math.cos(robotMovementAngle) * robotVelocity, Math.sin(robotMovementAngle) * robotVelocity);
        Vector phantomHubPos = velocityVector.scale(-timeToHub);

        RobotSimulator robotSimulator = new RobotSimulator();
        Vector hubPosition = new Vector(x - robotSimulator.xPointsFromCenter, -y);

        return new Vector(hubPosition, phantomHubPos);
    }
    public double getDeltaAngle() {
        double timeToHub = toHubShotTime.get(distanceFromHub, 1);
        Vector velocityVector = new Vector(Math.cos(robotMovementAngle) * robotVelocity, Math.sin(robotMovementAngle) * robotVelocity).scale(-1);
        Vector phantomHubPos = velocityVector.clone().scale(-timeToHub);

        RobotSimulator robotSimulator = new RobotSimulator();
        Vector hubPosition = new Vector(x - robotSimulator.xPointsFromCenter, -y);
        return phantomHubPos.getTheta() - hubPosition.getTheta();
    }
}
