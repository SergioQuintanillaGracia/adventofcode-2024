import java.util.Scanner;

public class p7 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        long correctSum = 0;

        while (sc.hasNext()) {
            String line = sc.nextLine();
            String[] splitLine = line.split(": ");
            long correctRes = Long.parseLong(splitLine[0]);

            String[] lineDataStr = splitLine[1].split(" ");
            int[] lineData = new int[lineDataStr.length];

            // Extract the numbers of the line into `lineData`
            for (int i = 0; i < lineData.length; i++) {
                lineData[i] = Integer.parseInt(lineDataStr[i]);
            }

            if (isCorrect(correctRes, lineData)) {
                correctSum += correctRes;
            }
        }

        System.out.println(correctSum);
    }

    public static boolean isCorrect(long expected, int[] data) {
        // Create a bitmap that will represent whether a number in the ith position of the array
        // will be added (0) or multiplied (1) with the next one
        boolean[] map = new boolean[data.length - 1];

        while (true) {
            long currRes = data[0];

            for (int i = 1; i < data.length; i++) {
                if (map[i - 1]) {
                    currRes *= data[i];
                } else {
                    currRes += data[i];
                }
            }

            if (currRes == expected) return true;

            // If increasing the map leads to overflow, there are no more combinations to try, return false
            if (!binaryIncrease(map)) return false;
        }
    }

    /* Increase the binary value represented by a boolean map
    * Returns `false` if there is overflow */
    public static boolean binaryIncrease(boolean[] map) {
        // Ex: from 0000 to 0001
        //     from 0001 to 0010

        int i;

        for (i = map.length - 1; i >= 0; i--) {
            if (!map[i]) {
                // The current number is 0
                map[i] = true;
                break;
            } else {
                // The current number is 1
                map[i] = false;
            }
        }

        // Return false if there has been overflow
        return i >= 0;
    }
}
