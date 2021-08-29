package problem1;

import java.io.*;
import java.util.Arrays;
import java.util.Scanner;

// 本地测试和牛客提交代码一致，无须修改相关
public class Main {
    static Scanner reader = new Scanner(System.in);


    public static void main(String[] args) throws IOException {
        int n = getNextInt();   //n本书
        int[] thickness = getNextArr();
        int[] width = getNextArr();


        System.out.println(solution(n, thickness, width));
    }

    private static long solution(int n, int[] thickness, int[] width) {
        Arrays.sort(thickness);
        Arrays.sort(width);
        int cnt = 0;    //表示已经确定位置的元素数
        long res = 1;

        for (int i = n - 1; i >= 0; i--) {
            int widthIdx = getWidthIdx(thickness[i], width);
            int x = n - widthIdx - cnt;   //可放置位置
            res *= x;
            cnt++;
        }
        return res % (1000000000 + 7);
    }

    //返回width数组中>=thick的最小元素的索引
    static int getWidthIdx(int thick, int[] width) {
        int idx = Arrays.binarySearch(width, thick);
        if (idx < 0) {
            return -(idx + 1);
        }
        while (idx > 0 && width[idx - 1] == thick) {
            idx--;
        }
        return idx;
    }

    /**
     * @return 获取下一行的单个int数据
     */
    static int getNextInt() throws IOException {
        return Integer.parseInt(reader.nextLine());
    }

    /**
     * 获取input的下一行数据，并以空格分割字符串后转为int数组
     *
     * @return 分割后的字符串转为的int数组
     */
    static int[] getNextArr() throws IOException {
        String[] arr = reader.nextLine().split(" ");
        int[] res = new int[arr.length];
        for (int i = 0; i < arr.length; i++) {
            res[i] = Integer.parseInt(arr[i]);
        }
        return res;
    }
}