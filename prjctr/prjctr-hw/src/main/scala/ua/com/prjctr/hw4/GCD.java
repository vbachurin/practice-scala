package ua.com.prjctr.hw4;

import java.util.Arrays;

public class GCD {

    public static void main(String[] args) {
        int[] arr = {2, 4, 7, 8, 10};
//        int[] arr = {10, 100, 1000};
        System.out.println(generalizedGCD(arr.length, arr));
    }

    public static int generalizedGCD(int num, int[] arr)
    {
        int result = findGCD(arr);
        return result;
    }
    // METHOD SIGNATURE ENDS

    private static int findGCD(int[] arr) {
        int min = Arrays.stream(arr).min().getAsInt();
        int result = min;
        for (int i = min; i > 0; i--) {
            if (allDividable(arr, i)) {
                return result = i;
            }
        }
        return result;
    }

    private static boolean allDividable(int[] arr, int mid) {
        return Arrays.stream(arr).allMatch(el -> el % mid == 0);
    }
}
