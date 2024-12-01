import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

public class p2 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        ArrayList<Integer> a1 = new ArrayList<>();
        ArrayList<Integer> a2 = new ArrayList<>();

        while (sc.hasNext()) {
            int i1 = sc.nextInt();
            if (i1 == -1) break;

            a1.add(i1);
            a2.add(sc.nextInt());
        }

        long similarity = 0;

        for (int i : a1) {
            for (int j : a2) {
                int times = 0;

                if (i == j) {
                    times++;
                }

                similarity += i * times;
            }
        }

        System.out.println(similarity);
    }
}