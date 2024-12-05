import java.util.ArrayList;
import java.util.Scanner;

public class p5 {
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

            // If the update is correct, add the middle number to sum
            if (isCorrect(update)) {
                sum += update[update.length / 2];
            }
        }

        System.out.println(sum);
    }

    public static boolean isCorrect(int[] update) {
        // Ignore the last number
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
}
