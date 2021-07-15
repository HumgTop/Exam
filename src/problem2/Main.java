package problem2;

import java.io.*;
import java.util.Scanner;

// 本地测试和牛客提交代码一致，无须修改相关
public class Main {

    public static void main(String[] args) throws IOException {
        Scanner scanner = new Scanner(System.in);
        String input = scanner.nextLine();
        String[] s = input.split(" ");
        int[] params = new int[s.length];
        for (int i = 0; i < s.length; i++) {
            params[i] = Integer.parseInt(s[i]);
        }
        int num = Integer.parseInt(scanner.nextLine());
        int[] res = new int[s.length + 1];

        int resIdx = 0;
        int pIdx = 0;

        while (resIdx < res.length) {
            if (resIdx == res.length - 1) {
                res[resIdx] = num;
                break;
            }
            if (params[pIdx] < num) {
                res[resIdx++] = params[pIdx++];
            } else {
                res[resIdx++] = num;
                while (resIdx < res.length) {
                    res[resIdx++] = params[pIdx++];
                }
            }
        }

        for (int i = 0; i < res.length - 1; i++) {
            System.out.print(res[i] + " ");
        }
        System.out.print(res[res.length - 1]);
    }
}