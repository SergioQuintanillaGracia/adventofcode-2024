import java.util.Scanner;

public class p2 {
    public static void main(String[] args) {
        // WARNING: Worst solution to the problem ever, don't even try to understand it
        // I promise I'll do better code tomorrow
        // Stop scrolling if you want to preserve your vision

        Scanner sc = new Scanner(System.in);

        int safeCount = 0;

        while (sc.hasNext()) {
            String line = sc.nextLine();
            if (line.equals("a")) break;

            Scanner ls = new Scanner(line);
            Boolean increasing = null;
            int prev = ls.nextInt();

            boolean safe = true;

            while (ls.hasNextInt()) {
                int n = ls.nextInt();
                int sub = n - prev;
                prev = n;

                if (increasing == null) {
                    increasing = sub > 0;
                }

                if (Math.abs(sub) < 1 || Math.abs(sub) > 3) {
                    safe = false;
                    break;
                }

                if (increasing) {
                    if (sub < 0) {
                        safe = false;
                        break;
                    }
                } else {
                    if (sub > 0) {
                        safe = false;
                        break;
                    }
                }
            }

            if (safe) safeCount++;
        }

        System.out.println(safeCount);
    }
}
