package problem1;

import java.io.*;

// 本地测试和牛客提交代码一致，无须修改相关
public class Main {
    static BufferedReader reader = getReader(); //初始化流
    static BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out));


    public static void main(String[] args) throws IOException {
        int[] params = getNextArr();
        int row = params[0];
        int col = params[1];
        int[][] matrix = new int[row][col];
        for (int i = 0; i < row; i++) {
            matrix[i] = getNextArr();
        }
        close();    //释放流资源
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
            return new BufferedReader(new FileReader("src/problem1/test.txt"));
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