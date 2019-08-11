package com.ajit.hackerrank;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class HackerRankSolutions {

  // Complete the sockMerchant function below.
  static int sockMerchant(int n, int[] ar) {
    Map<Integer, Integer> pairMap = new HashMap<Integer, Integer>();
    for (int i : ar) {
      if (pairMap.get(i) != null) {
        int count = pairMap.get(i);
        pairMap.put(i, ++count);
      } else {
        pairMap.put(i, 1);
      }
    }
    int count = 0;
    for (int i : pairMap.keySet()) {
      count = count + (pairMap.get(i)) / 2;
    }
    return count;
  }


  public static void main(String[] args) throws IOException {
    Scanner scanner =
        new Scanner(HackerRankSolutions.class.getClassLoader().getResourceAsStream("input.txt"));

    int n = scanner.nextInt();
    scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");

    int[] ar = new int[n];

    String[] arItems = scanner.nextLine().split(" ");
    scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");

    for (int i = 0; i < n; i++) {
      int arItem = Integer.parseInt(arItems[i]);
      ar[i] = arItem;
    }

    int result = sockMerchant(n, ar);

    System.out.println(result);

    scanner.close();
  }
}
