import java.util.Scanner;

public class p2 {
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

            int i;
            boolean increasing = arr[1] - arr[0] > 0;

            for (i = 1; i < arr.length; i++) {
                int sub = arr[i] - arr[i - 1];
                int diff = Math.abs(sub);

                // Check if the difference is in the range [1, 3]
                if (diff < 1 || diff > 3) break;

                // Check if the array continues to be increasing / decreasing
                if (increasing && sub < 0 || !increasing && sub > 0) break;
            }

            if (i == arr.length) {
                // The loop finished, so the line is safe
                safeCount++;
            }
        }

        System.out.println(safeCount);
    }
}
