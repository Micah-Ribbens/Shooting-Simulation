import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

// TODO correct new distance math; possibly also correct finding the angles because that also could be off
public class Main {

    public static void main(String[] args) throws IOException {
        ArrayList<int[]> grid = ArrayGenerator.getGrid();
        RobotSimulator rs = new RobotSimulator();
        String data = "numberOfTests:"+grid.size()+"\n";
        data += "hubPoint:["+rs.xPointsFromCenter+",0,]\n";
        data += "robotStart:["+rs.xPointsFromCenter+","+(rs.yPointsFromCenter + rs.distanceToHubCenter)+",]\n";

        for (int i = 0; i < grid.size(); i++) {
            if (grid.get(i)[0] == 0 && grid.get(i)[1] == 0) {
                data += "skipped_index:"+i+"\n";
            }
            data += DataGetter.getNewData(grid.get(i)[0], grid.get(i)[1], i + 1);
        }
        FileWriter fileWriter = new FileWriter("C:\\Users\\mdrib\\Downloads\\Robotics\\ShootingSimulation\\ShootingSimulationGUI\\data.txt");
        fileWriter.write(data);
        fileWriter.close();
    }
}
