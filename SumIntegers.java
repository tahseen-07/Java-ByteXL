import java.util.ArrayList;
import java.util.Scanner;

public class SumIntegers {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        ArrayList<Integer> numbers = new ArrayList<>();

        System.out.println("Enter integers separated by space:");
        String[] input = sc.nextLine().trim().split("\\s+"); // Trim and split by one or more spaces

        int sum = 0;
        for (String str : input) {
            if (!str.isEmpty()) {               // Skip empty strings
                Integer num = Integer.parseInt(str);
                numbers.add(num);
                sum += num;
            }
        }

        System.out.println("Sum of integers: " + sum);
    }
}
