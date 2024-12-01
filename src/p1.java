import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

public class p1 {
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

        Collections.sort(a1);
        Collections.sort(a2);

        int sum = 0;

        for (int i = 0; i < a1.size(); i++) {
            sum += Math.abs(a1.get(i) - a2.get(i));
        }

        System.out.println(sum);
    }
}