package problem2;

import java.io.*;

// 本地测试和牛客提交代码一致，无须修改相关
public class Main {
    static BufferedReader reader = getReader(); //初始化流
    static BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out));


    public static void main(String[] args) throws IOException {
        int[] params = getNextArr();
        int n = params[0];
        int m = params[1];
        int h = params[2];
        int[] height = getNextArr();
        solution(n, m, h, height);

        close();    //释放流资源
    }

    /**
     * @param n      n个木桩
     * @param m      家具宽度
     * @param h      可通过围栏最大高度
     * @param height 围栏高度数组
     */
    static void solution(int n, int m, int h, int[] height) throws IOException {
        //滑动窗口：窗口内的值<=h，当窗口长度为m时，返回left
        int left = 0, right = 0;
        for (int i = 0; i < height.length; i++) {
            if (height[i] <= h) {
                left = i;
                right = i;
                break;
            }
        }
        if (left == 0) {
            print(-1);
        }

        int wndLen = 1; //维护窗口的实时长度
        while (left < height.length) {
            if (height[left] > h) {
                left++;
                continue;
            }
            right = left + 1;
            //此时height[left]<=h，移动right
            while (right < height.length && height[right] <= h) {
                right++;
                if ((wndLen = right - left) == m) {
                    print(left + 1);
                    newLine();
                    return;
                }
            }
            if (right == height.length) {
                print(-1);
                newLine();
            }
            //此时height[right]>h
            //重置left，找到新的开始点
            left = right + 1;
        }
    }


//-------------------------------------------------以下为IO工具方法------------------------------------------------------------------


    //打印数字
    static void print(int num) throws IOException {
        writer.write("" + num);
    }

    //打印字符串
    static void print(String str) throws IOException {
        writer.write(str);
    }

    static void newLine() throws IOException {
        writer.newLine();
    }

    /**
     * @return 返回扫描src目录下test.txt（存放用于测试的本地用例）的Reader
     */
    static BufferedReader getReader() {
        try {
            return new BufferedReader(new FileReader("src/problem2/test.txt"));
        } catch (FileNotFoundException e) {
            //代码粘贴到牛客里，使用的是System.in使用
            return new BufferedReader(new InputStreamReader(System.in));
        }
    }

    /**
     * @return 获取下一行的单个int数据
     */
    static int getNextInt() throws IOException {
        return Integer.parseInt(reader.readLine());
    }

    /**
     * 获取input的下一行数据，并以空格分割字符串后转为int数组
     *
     * @return 分割后的字符串转为的int数组
     */
    static int[] getNextArr() throws IOException {
        String[] arr = reader.readLine().split(" ");
        int[] res = new int[arr.length];
        for (int i = 0; i < arr.length; i++) {
            res[i] = Integer.parseInt(arr[i]);
        }
        return res;
    }

    /**
     * 释放流
     *
     * @throws IOException
     */
    static void close() throws IOException {
        reader.close();
        writer.close();
    }
}