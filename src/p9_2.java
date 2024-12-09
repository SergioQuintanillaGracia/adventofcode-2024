import java.util.ArrayList;
import java.util.Scanner;

public class p9_2 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        String input = sc.nextLine();
        ArrayList<Short> data = expandLine(input);

        for (int i = data.size() - 1; i >= 0; i--) {
            // Move i to the first position to the left that is not empty
            while (data.get(i) == -1) i--;

            // Get the size of the block with that number
            short num = data.get(i);
            int endPos = i;
            int startPos = data.indexOf(num);
            i = startPos;
            int numSize = endPos - startPos + 1;

            // Try to find a place to put it
            int newPos = sequenceIndex(data, startPos, -1, numSize);

            if (newPos != -1) {
                // newPos is the starting position of a block where the number fits
                // Replace the positions originally occupied by the number by spaces
                for (int j = startPos; j <= endPos; j++) {
                    data.set(j, (short) -1);
                }

                // Place the number in the found space section
                for (int j = newPos; j < newPos + numSize; j++) {
                    data.set(j, num);
                }
            }
        }

        long checksum = 0;

        // Calculate the checksum of the current data array
        for (int i = 0; i < data.size(); i++) {
            if (data.get(i) != -1) {
                checksum += data.get(i) * i;
            }
        }

        System.out.println(checksum);
    }

    /* Returns the index of the first position of a value that appears "times" times in the array, or -1 if it does
    not */
    public static int sequenceIndex(ArrayList<Short> data, int limitPos, int x, int times) {
        int count = 0;
        for (int i = 0; i < limitPos; i++) {
            if (data.get(i) == x) {
                count++;
                if (count >= times) return i - times + 1;
            } else {
                count = 0;
            }
        }

        return -1;
    }

    public static ArrayList<Short> expandLine(String input) {
        // Return a short array containing the expanded line (where arr[i] corresponds to the ID of a file (if there
        // is one), or to -1 if it's empty space)
        ArrayList<Short> arr = new ArrayList<>();

        boolean isFile = true;
        short fileI = 0;
        for (char c : input.toCharArray()) {
            if (isFile) {
                for (int i = 0; i < Integer.parseInt(Character.toString(c)); i++) {
                    // Repeat the file index as many times as the char indicates
                    arr.add(fileI);
                }
                fileI++;
            } else {
                for (int i = 0; i < Integer.parseInt(Character.toString(c)); i++) {
                    // Add spaces as many times as the char indicates
                    arr.add((short) -1);
                }
            }

            isFile = !isFile;
        }

        return arr;
    }
}
