package com.haobin.algorithm;

import java.util.Scanner;

public class Main {


    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int num = in.nextInt();
        for (int i = 0; i < num; i++) {
            if (i == num -1) {
                System.out.print(processPerLine(in));
            }
            System.out.println(processPerLine(in));
        }
    }

    public static int processPerLine(Scanner in) {
        int[] input = new int[3];
        for (int i = 0; i < 3; i++) {
            input[i] = in.nextInt();
        }
        int n = input[0];
        int[] a = new int[n+1];
        a[0] = input[1];
        a[1] = input[2];
        // 0 或 任何数为本身
        int s = 0;
        for (int i = 0; i <= n; i++) {
            s = s | getAn(i, a[0], a[1]);
        }
        return getZero(s);
    }

    public static int getAn(int n, int a0, int a1) {
        if(n < 1) {
            return a0;
        }else if(n == 1 || n == 2) {
            return a1;
        }
        int res = 1;
        int pre = 1;
        int temp = 0;
        for(int i = 3; i < n; i++) {
            temp = res;
            res = pre + res;
            pre  = temp;
        }
        return res;
    }

    public static int getZero(int num) {
        int count=0;
        if(num<0){
            int i=1;
            while(i!=0){
                if((i&num)==0){
                    count++;
                }
                i=i<<1;
            }
            return  count;
        }
        while(num!=0){
            int mod = num%2;
            if(mod==0)
                count++;
            num = num/2;
        }
        return count;
    }
}
