
import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.Scanner;

/** Gets the data from a JSON file with helper methods*/
public class DataReader {
    private HashMap<String, String> nameToData = new HashMap<>();

    public DataReader(String fileName) {
        String base_dir = "C:\\Users\\mdrib\\Downloads\\Robotics\\ShootingSimulation\\";
        File file = new File(base_dir + fileName);
        Scanner scanner = null;
        try {
            scanner = new Scanner(file);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        while (scanner.hasNextLine()) {
            String line = scanner.nextLine();
            String delimeter = ":";

//          Not all lines in a JSON file have key to value data
            if (!line.contains(delimeter)) continue;

            int startIndex = line.indexOf(delimeter);

//          Name is from the start to where the delimeter begins and the data is from where the delimeter ends to the end of the line
//          Stripping the data to delete unnecessary spaces before and after the data
            String name = line.substring(0, startIndex).strip();
            String data = line.substring(startIndex + delimeter.length()).strip();

            nameToData.put(name, data);
        }
    }

    public int getInt(String key) {
        String data = nameToData.get(key);
        return Integer.parseInt(data);
    }
    public int[] getIntArray(String key) {
        String[] stringArray = getStringArray(key);
        int[] intArray = new int[stringArray.length];
        int count = 0;

        for (var item : stringArray) {
            intArray[count++] = Integer.parseInt(item);
        }
        return intArray;
    }

    public String[] getStringArray(String key) {
        char[] characters = nameToData.get(key).toCharArray();

//      There are 2 spaces in the beginning that shouldn't be counted
        String[] array = new String[getCount(characters, ' ') - 2];
        int numberAdded = 0;
        String currentItem = "";
//      The first two and last two character's of characters can be ignored
        for (var i = 3; i < characters.length - 1; i++) {
            char character = characters[i];
            if (character == ' ') {
                String cleanedItem = currentItem.strip();
                array[numberAdded++] = cleanedItem;
                currentItem = "";
            }
            else {
                currentItem += character;
            }
        }
        return array;
    }
    private int getCount(char[] characters, char countedCh) {
        int count = 0;
        for (var character : characters) {
            if (character == countedCh) {
                count++;
            }
        }
        return count;
    }
    private String deleteAll(String string, String deletedChs) {
        String newString = "";
        for (var ch : string.toCharArray()) {
            if (!deletedChs.contains(ch + "")) {
                newString += ch;
            }
        }
        return newString;
    }
}
