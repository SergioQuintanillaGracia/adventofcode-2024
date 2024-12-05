import java.util.ArrayList;
import java.util.Scanner;

public class p5_2 {
    static ArrayList<Integer[]> rules = new ArrayList<>();

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        // Read rules
        while (sc.hasNext()) {
            String line = sc.nextLine();
            if (line.isEmpty()) break;

            String[] stringRule = line.split("[|]");
            Integer[] rule = new Integer[]{Integer.parseInt(stringRule[0]), Integer.parseInt(stringRule[1])};
            rules.add(rule);
        }

        int sum = 0;

        // Read updates
        while (sc.hasNext()) {
            String[] updateStr = sc.nextLine().split(",");

            int[] update = new int[updateStr.length];
            for (int i = 0; i < updateStr.length; i++) {
                update[i] = Integer.parseInt(updateStr[i]);
            }

            // If the update isn't correct, sort it and add the middle number to sum
            if (!isCorrect(update)) {
                update = sortUpdate(update);
                sum += update[update.length / 2];
            }
        }

        System.out.println(sum);
    }

    public static int[] sortUpdate(int[] update) {
        // For every number, check if the rules are met leaving it where it is
        // If they aren't, move it one to the left and check again
        for (int i = 0; i < update.length; i++) {
            // Build a subarray containing elements from 0 to i
            int[] subUpdate = new int[i + 1];
            for (int j = 0; j <= i; j++) {
                subUpdate[j] = update[j];
            }

            int currNum = i;  // At the beginning, the current number is the rightmost one
            while(!isCorrect(subUpdate)) {
                // Move current number to the left
                int temp = subUpdate[currNum];
                subUpdate[currNum] = subUpdate[currNum - 1];
                subUpdate[currNum - 1] = temp;

                // The current number is now one position to the left
                currNum--;
            }

            // Copy the movements in the newUpdate subarray to the main update array
            for (int j = 0; j < subUpdate.length; j++) {
                update[j] = subUpdate[j];
            }
        }

        return update;
    }

    public static boolean isCorrect(int[] update) {// Ignore the last number
        for (int i = 0; i < update.length - 1; i++) {
            // Check if any rule is broken for the current number
            for (Integer[] rule : rules) {
                int first = rule[0];
                int last = rule[1];

                if (last == update[i]) {
                    // For the rule to be met, there cannot be `first` before `last`
                    for (int j = i + 1; j < update.length; j++) {
                        if (update[j] == first) return false;
                    }
                }
            }
        }

        return true;
    }

    public static void printUpdate(int[] update) {
        System.out.print("[");
        for (int i : update) {
            System.out.printf("%d,", i);
        }
        System.out.print("]");
    }
}
