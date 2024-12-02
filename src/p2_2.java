import java.util.ArrayList;
import java.util.Scanner;

public class p2_2 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int safeCount = 0;

        while (sc.hasNext()) {
            // Get a line of integers
            String parts[] = sc.nextLine().split(" ");
            int arr[] = new int[parts.length];

            for (int i = 0; i < parts.length; i++) {
                arr[i] = Integer.parseInt(parts[i]);
            }

            // Try generating arrays without just one of their elements and check if they are safe
            for (int i = 0; i < arr.length; i++) {
                ArrayList<Integer> modArr = new ArrayList<>(arr.length - 1);

                for (int j = 0; j < arr.length; j++) {
                    if (i != j) {
                        modArr.add(arr[j]);
                    }
                }

                int j;
                boolean increasing = modArr.get(1) - modArr.get(0) > 0;

                for (j = 1; j < modArr.size(); j++) {
                    int sub = modArr.get(j) - modArr.get(j - 1);
                    int diff = Math.abs(sub);

                    // Check if the difference is in the range [1, 3]
                    if (diff < 1 || diff > 3) break;

                    // Check if the array continues to be increasing / decreasing
                    if (increasing && sub < 0 || !increasing && sub > 0) break;
                }

                if (j == modArr.size()) {
                    // The loop finished, so the line is safe
                    safeCount++;
                    // Break ot ouf the loop, as we know this line is safe and don't need
                    // to continue generating more arrays with one element removed
                    break;
                }
            }
        }

        System.out.println(safeCount);
    }
}
