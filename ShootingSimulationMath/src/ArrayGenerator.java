import java.util.ArrayList;

public class ArrayGenerator {
    public static ArrayList<int[]> getGrid() {
        DataReader dataGetter = new DataReader("data.txt");
        ArrayList<int[]> grid = new ArrayList<>();
        int xPointsFromCenter = dataGetter.getInt("x_points_from_center");
        int yPointsFromCenter = dataGetter.getInt("y_points_from_center");
        int distanceToHubCenter = dataGetter.getInt("hub_height") + dataGetter.getInt("distance_from_hub");
        int xLength = xPointsFromCenter * 2 + 1;
        int yLength = yPointsFromCenter * 2 + 1;

        for (int x = 0; x < xLength; x++) {

            for (int y = 0; y < yLength; y++) {
                grid.add(new int[] {x, y + distanceToHubCenter});
            }
        }
        return grid;
    }
}
