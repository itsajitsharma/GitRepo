package com.ajit.problems;

import java.io.BufferedReader;
import java.io.InputStreamReader;

import java.util.*;

/**
 *
 *
 * Input Format:
 *      First Line: length of array
 *      second line: input array
 *      third line: number of queries, Q
 *      next Q lines : space separated number representing positions L and R
 * Output: For each Query, print the XOR of prime numbers of array elements from position L to R. L and R are inclusive
 *
 *
 * Sample input:
 * 5
 * 2 5 8 4 3
 * 1
 * 1 5
 *
 * output:
 * 4
 */

public class PrimeXOR {
    static boolean prime[] = new boolean[1000006];

    public static void fillPrime(int n){
        Arrays.fill(prime, true);
        prime[1] = false;
        for (int p =2 ; p*p<n;p++){
            if(prime[p])
            {
                for(int i = p*2;i<n; i+=p){
                    prime[i] = false;
                }

            }

        }
    }

    public static boolean isPrime(int n)
    {
        return prime[n];
    }

    public static void main(String args[] ) throws Exception {
        /* Sample code to perform I/O:
         * Use either of these methods for input

        //Scanner
        Scanner s = new Scanner(System.in);
        String name = s.nextLine();                 // Reading input from STDIN
        System.out.println("Hi, " + name + ".");    // Writing output to STDOUT

        */

        fillPrime(1000000);

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        //Length of array
        String length = "5";
        //String length = br.readLine();
        int n = Integer.parseInt(length);

        //input Array
        String tempArray = "2 5 8 4 3";
        //String tempArray = br.readLine();
        String [] arr_String = tempArray.split(" ");
        int [] arr = new int[n];
        for(int i =0; i<n;i++){
            arr[i] = Integer.parseInt(arr_String[i]);
        }

        //Num Query
        //int num_query = Integer.parseInt(br.readLine());
        int num_query = 1;

        int [] leftXOR = new int[n];
        int [] rightXOR = new int[n];

        if(isPrime(arr[0]))
            leftXOR[0] = arr[0];
        else
            leftXOR[0] = 0;

        if(isPrime(arr[n-1])){
            rightXOR[n-1] = arr[n-1];
        }
        else{
            rightXOR[n-1] = 0;
        }


        for(int i=1;i<n;i++){
            if(isPrime(arr[i]))
                leftXOR[i] = leftXOR[i-1] ^ arr[i];
            else
                leftXOR[i] = leftXOR[i-1];

            if(isPrime(arr[n-i-1]))
                rightXOR[n-1-i] = rightXOR[n-i] ^ arr[n-1-i];
            else {
                rightXOR[n-1-i] = rightXOR[n-i];
            }
        }

        //each query
        for(int i = 0; i < num_query; i++){
//            String [] arr_index = (br.readLine()).split(" ");
//            int start = Integer.parseInt(arr_index[0]);
//            int end = Integer.parseInt(arr_index[1]);
            int start = 2;
            int end = 4;

            //leftXOR[n] ^ leftXOR[start-1] ^ rightXOR[end+1];
            if(start==1 && end ==n)
                System.out.println(leftXOR[n-1]);
            else if(start == 1)
                System.out.println(leftXOR[end-1] );
            else if(end == n)
                System.out.println(rightXOR[start-1]);
            else
                System.out.println(leftXOR[n-1] ^ leftXOR[start-2] ^ rightXOR[end]);


        }

    }

}