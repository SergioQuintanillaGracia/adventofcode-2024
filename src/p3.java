import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class p3 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        Pattern pattern = Pattern.compile("mul[(][0-9]{1,3},[0-9]{1,3}[)]");
        long sum = 0;

        while (sc.hasNext()) {
            String line = sc.nextLine();
            Matcher matcher = pattern.matcher(line);

            // Find a mul instruction and run it
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
