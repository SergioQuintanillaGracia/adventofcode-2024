import java.util.ArrayList;
import java.util.Scanner;

public class p9 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        String input = sc.nextLine();
        ArrayList<Short> data = expandLine(input);

        long checksum = 0;

        int i = 0;
        int pointerL = 0;
        int pointerR = data.size() - 1;
        while (pointerL <= pointerR) {
            if (data.get(pointerL) == -1) {
                // We should take the element at pointerR instead, as there is free space
                // Move pointerR to the next number, skipping spaces
                while (data.get(pointerR) == -1) {
                    pointerR--;
                }
                checksum += data.get(pointerR) * i;
                pointerR--;

            } else {
                checksum += data.get(pointerL) * i;
            }

            pointerL++;
            i++;
        }

        System.out.println(checksum);
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
