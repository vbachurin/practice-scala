package ua.com.prjctr.hw3;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class LilyJavaQuick {

  private static final Scanner scanner = new Scanner(System.in);

  public static void main(String[] args) throws IOException {
    BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(System.getenv("OUTPUT_PATH")));

    int n = scanner.nextInt();
    scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");

    int[] arr = new int[n];

    String[] arrItems = scanner.nextLine().split(" ");
    scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");

    for (int i = 0; i < n; i++) {
      int arrItem = Integer.parseInt(arrItems[i]);
      arr[i] = arrItem;
    }

    int result = lilysHomework(arr);

    bufferedWriter.write(String.valueOf(result));
    bufferedWriter.newLine();

    bufferedWriter.close();

    scanner.close();
  }

  static int lilysHomework(int[] arr) {
    return Math.min(
      swapsToSortArray(arr.clone(), false),
      swapsToSortArray(arr.clone(), true)
    );
  }

  public static int swapsToSortArray(int[] arr, boolean descending) {
    int i = 0;
    int counter = 0;
    while (i < arr.length - 1) {
      int indexOfMin = findMin(arr, i, descending);
      if (indexOfMin != i) {
        swap(arr, i, indexOfMin);
        counter += 1;
      }
      i += 1;
    }
    return counter;
  }


  public static int  findMin(int[] arr, int i, boolean descending) {
    int indexOfMin = i, j = i;
    while (j < arr.length) {
      boolean condition;
      if (descending) condition = arr[j] > arr[indexOfMin];
        else condition = arr[j] < arr[indexOfMin];
      if (condition)
        indexOfMin = j;
      j += 1;
    }
    return indexOfMin;
  }

  private static void swap(int[] arr, int sIndex, int tIndex) {
//    println(s"Swapping: [sIndex $sIndex value ${arr(sIndex)}] with [tIndex $tIndex value ${arr(tIndex)}]")
    int temp = arr[sIndex];
    arr[sIndex] = arr[tIndex];
    arr[tIndex] = temp;
  }

}
