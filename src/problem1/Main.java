package problem1;

import java.io.*;

// 本地测试和牛客提交代码一致，无须修改相关
public class Main {
    static BufferedReader reader = getReader(); //初始化流


    public static void main(String[] args) throws IOException {
        int[] params = getNextArr();
        int n = params[0];
        int k = params[1];
        int[][] res = new int[n * k][n * k];
        for (int i = 0; i < n; i++) {
            int[] cur = getNextArr();
            for (int j = 0; j < n; j++) {
                for (int p = 0; p < k; p++) {
                    for (int l = 0; l < k; l++) {
                        res[i * k + p][j * k + l] = cur[j];
                    }
                }
            }
        }

        for (int i = 0; i < n * k; i++) {
            for (int j = 0; j < n * k; j++) {
                System.out.print(res[i][j] + " ");
            }
            System.out.println();
        }
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
}