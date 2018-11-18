
public class Experiments {

    static int MOD = 1000000007;

    public static int calculationInitial(int n) {
        int x = 0;
        for (int i = 0; i < n; ++i) {
            for (int j = i; j < n; ++j) {
                for (int k = i; k <= j; ++k) {
                    x = (x + 1) % MOD;
                }
            }
        }
        return x;
    }

    public static long calculationCube(int n) {
        long x = 0;
        for (int i = 0; i < n; ++i) {
            for (int j = i; j < n; ++j) {
                for (int k = j; k < n; ++k) {
//                    System.out.println(String.format("i: %d, j: %d, k: %d", i, j, k));
                    x = (x + 1);
                }
            }
        }
        long mod = x % MOD;
        System.out.println("x: " + x);
        int i = (int) mod;
        return i;
    }

//    public static long performOperations(int n) {
//        long x = 0;
//        long y = 0;
//        int i, j, k;
//        i = j = k = 0;
//        while (i < n) {
//            y += j;
//            j = i;
//            while (j < n) {
//                y += k;
//                k = j;
//                while (k < n) {
//                    x = (x + 1);
////                    System.out.println(String.format("i: %d, j: %d, k: %d => x: %d, y: %d", i, j, k, x, y));
//                    k++;
//                }
//                j++;
//            }
//            i++;
//        }
//        long mod = x % MOD;
//        System.out.println(String.format("x: %d, y: %d", x, y));
//        int res = (int) mod;
//        return res;
//    }

//    public static long performOperations(int n) {
//        long x = 0;
//        for (int i = 0; i < n; ++i) {
//            for (int j = 0; j <= i; ++j) {
//                for (int k = 0; k <= j; ++k) {
//                    x = (x + 1);
////                    System.out.println(String.format("i: %d, j: %d, k: %d => x: %d", i, j, k, x));
//                }
//            }
//        }
//        long mod = x % MOD;
////        System.out.println("x: " + x);
//        int i = (int) mod;
//        return i;
//    }



//    public static int performOperations(int n) {
//        long x = 0;
//        int i = n;
//        while (i >= 0) {
//            x += i * (n - --i);
//        }
//        long mod = x % MOD;
//        return (int) mod;
//    }
//
//
//    public static int performOperations(int n) {
//        long x = 0;
//
//        for (int i = n; i >= 0; --i) {
//            x += i * (n + 1 - i);
//        }
//        long mod = x % MOD;
//        return (int) mod;
//    }

//
//    public static int performOperations(int n) {
//        long x = n;
//
//        for (int i = 0; i < n; ++i) {
//            x += i * (n + 1 - i); // one step short
////            System.out.println(String.format("n: %d, i: %d => x: %d", n, i, x));
////            for (int j = 0; j <= i; x += j++) {}
//        }
//        long mod = x % MOD;
//        return (int) mod;
//    }
//


//    public static int performOperations(int n) {
//        long x = 0;
//        for (int i = n; i > 0; --i) {
//            x += i * (n + 1 - i);
//        }
//        long mod = x % MOD;
//        return (int) mod;
//    }

//    public static int performOperations(int n) {
//        long x = 0;
//        for (int i = n; i > 0; --i) {
//            x = r(x) + r(i * (n + 1 - i));
//        }
//        return (int) r(x);
//    }


//    private static long r(long x) {
//        if (x > MOD_LONG)
//            return x % MOD_LONG;
//        else
//            return x;
//    }

}
