package ua.com.prjctr.hw3;

import java.util.Scanner;

class CarpathiansJava {

    private static final Scanner scanner = new Scanner(System.in);


    public static void main(String[] args) {
        String[] fa = scanner.nextLine().split(" ");
        int n = Integer.parseInt(fa[0]);
        int k = Integer.parseInt(fa[1]);

        int[] t = new int[n];

        String[] arrItems = scanner.nextLine().split(" ");
        scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");

        for (int i = 0; i < n; i++) {
            int arrItem = Integer.parseInt(arrItems[i]);
            t[i] = arrItem;
        }
        int d = longestDailyHike(k, t, n);
        System.out.println(d);
    }

    public static int longestDailyHike(int k, int[] t, int n) {
        int l = t[n - 1] - t[n - 2] - 1; // <last element> - <second to last element> - 1
        int r = t[n - 1];

        while (r - l > 1) {
            int mid = (l + r) / 2;
            if (possible(k, t, n, mid))
                r = mid;
            else
                l = mid;
        }
        return r;
    }

    public static boolean possible(int k, int[] t, int n, int rangeToday) {
        int days = 0;
        int stopoverIndex = 0;
        while (t[stopoverIndex] < t[n - 1] && days <= k + 1) {
            stopoverIndex = furthestHikeToday(stopoverIndex, t, t[stopoverIndex] + rangeToday);
            days += 1;
        }
        return days <= k + 1; // if k stops, then k + 1 days of hiking available

    }

    /* Finding the furthest point (element) group can get to with given velocity */
    public static int furthestHikeToday(int l0, int t[], int maxRangeToday) {
        int l = l0;
        int r = t.length - 1;

        while (r - l > 1) {
            int mid = (l + r) / 2;
            if (t[mid] < maxRangeToday)
                l = mid;
            else
                r = mid;
        }
        if (t[r] <= maxRangeToday)
            return r;
        else
            return l;
    }

}
