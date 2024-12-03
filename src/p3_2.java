import java.util.ArrayList;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class p3_2 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        Pattern pattern = Pattern.compile("mul[(][0-9]{1,3},[0-9]{1,3}[)]");
        long sum = 0;

        StringBuffer inputBuffer = new StringBuffer();

        // Get all the input and store it in an input buffer (as there could be cases where there is a don't()
        // instruction at the end of one of the lines, so the start of the next line should have mul instructions
        // disabled at the beginning, but with my method, all new lines have mul instructions enabled by default
        // at the beginning
        while (sc.hasNext()) {
            inputBuffer.append(sc.nextLine().trim());
        }

        String input = inputBuffer.toString();

        String separatedByDo[] = input.split("do[(][)]");

        for (String s : separatedByDo) {
            String separatedByDont[] = s.split("don't[(][)]");
            // The first element of the array will be the substring with mul instructions executed,
            // as it will be preceded by a do() instruction (or nothing in the case of the first substring)
            // and followed by a don't()
            String line = separatedByDont[0];

            // Find a mul instruction and run it
            Matcher matcher = pattern.matcher(line);
            while (matcher.find()) {
                String match = matcher.group();
                String numsWithComma = match.substring(4, match.length() - 1);
                String numsStr[] = numsWithComma.split(",");
                sum += Integer.parseInt(numsStr[0]) * Integer.parseInt(numsStr[1]);
            }
        }

        System.out.println(sum);
    }
}
