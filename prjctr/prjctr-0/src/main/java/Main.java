
import java.util.Scanner;

public class Main {

    public static long MOD_LONG = 1000000007L;

    public static void main(String[] args) {
        Scanner s = new Scanner(System.in);
        long n = s.nextLong();
        long x = calculation(n);
        System.out.println(x);
    }

    public static long calculation(long n) {
        long x = 0;
        for (long i = n; i > 0; --i) {
            x += (i * (n + 1 - i));
        }
        return Math.floorMod(x, MOD_LONG);
    }

}

