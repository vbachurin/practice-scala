package ua.com.prjctr.hw5;

import java.util.Arrays;
import java.util.Scanner;

public class MaxMoney {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = Integer.valueOf(scanner.nextLine());
        int k = Integer.valueOf(scanner.nextLine());
        int[] cost = Arrays.stream(scanner.nextLine().split(" ")).mapToInt(Integer::valueOf).toArray();
        int result = maxMoney(n, k, cost);
        System.out.println(result);
    }

    // собрать макс. кол-во монет
    static int maxMoney(int n, int k, int[] сost) {
        if (n < 0) {
            return 0;
        }
        if (n == 0 || n == 1) {
            return 1;
        }

        int[] a = new int[n + 1];
        int[] from = new int[n + 1];
        a[0] = 0;

        for (int i = 1; i <= n; ++i) {
            a[i] = a[i - 1] + сost[i];
            from[i] = i - 1;
            for (int j = 1; j <= Math.min(i, k); ++j) {
                if (a[i] < a[i - j] + сost[i]) {
                    a[i] = a[i - j] + сost[i];
                    from[i] = i - j;
                }
            }
        }

        Arrays.stream(a).forEach(System.out::print);
        System.out.println();
        Arrays.stream(from).forEach(System.out::print);

        return a[n];
    }

    /*
      def trapCorridor(cost: Array[Int], n: Int, k: Int = 2): Int = {
    if (n < 0) return 0
    if (n == 0 || n == 1) return 1

    val a: Array[Int] = new Array[Int](n + 1)
    val from: Array[Int] = new Array[Int](n + 1)
    a(0) = 0

    for {
      i <- 1 to n
    } {
      a(i) = a(i - 1) + cost(i)
      from(i) = i - 1
      for {
        j <- 1 to Math.min(i, k)
      } {
        if (a(i) < a(i - j) + cost(i)) {
          a(i) = a(i - j) + cost(i)
          from(i) = i - j
        }
      }
    }

     */


}
