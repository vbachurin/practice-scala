package ua.com.prjctr;


import java.util.Scanner;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class MadnessJava {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Integer t = Integer.valueOf(scanner.nextLine());
        Stream<int[]> arrays = IntStream.range(0, t).mapToObj(a -> {
                    Integer n = Integer.valueOf(scanner.nextLine());
                    String x = scanner.nextLine();
                    return Stream.of(x.split(" ")).limit(n).mapToInt(b -> Integer.valueOf(b)).toArray();
                }
        );

        arrays.forEach(arr -> {
            int counter = 0;
            for (int i = 0; i < arr.length; i++) {
                if (Math.abs(arr[i] - i - 1) <= 2) {
                    System.out.println(String.format("arr[i] %d, i: %d: OK", arr[i], i));
                    counter++;
                }
                else {
                    System.out.println(String.format("arr[i] %d, i: %d: CHAOTIC", arr[i], i));
                    counter = -1;
                    break;
                }
            }
            if (counter >= 0)
                System.out.println(counter);
            else
                System.out.println("Too chaotic");


        }
        );
    }

//    public static void main(String[] args) {
//        Scanner scanner = new Scanner(System.in);
//        Integer t = Integer.valueOf(scanner.nextLine());
//        int[] x;
//        for (int i =0; i < t; i++) {
//                    Integer n = Integer.valueOf(scanner.nextLine());
//                    x = Stream.of(scanner.nextLine().split(" ")).mapToInt(a -> Integer.valueOf(a)).toArray();
//                }
//
//        for (int j = 0; j < n; j++) {
//            System.out.println(x[j]);
//        }
//    }

        /*
2
5
2 1 5 3 4
5
2 5 1 3 4
         */
}
