package problem1;


import java.io.*;

// 本地测试和牛客提交代码一致，无须修改相关
public class Main {
    static BufferedReader reader = getReader(); //初始化流
    static BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out));


    public static void main(String[] args) throws IOException {
        int num = getNextInt();
        String string = String.valueOf(num);    //将输入参数转为字符串

        if (isHuiWen(string)) {
            print(0);
            close();
            return;
        }

        int[][] dp = new int[100][100];
        char[] str = string.toCharArray();
        int[] add = {0, 100, 200, 360, 220};//添加及删除的开销，前面加个0是为了从1开始
        int[] del = {0, 120, 350, 200, 320};

        //dp[i][j]表示从序号i到j变成回文串时的最小代价，而dp[i][j]可以由dp[i+1][j]和dp[i][j-1]得到
        //如果第i个字符和第j个字符不同，那么dp[i][j]可由dp[i+1][j]删除第i个字符，或者在字符串最末尾添加第i个字符得到，
        //也可以由dp[i][j-1]删除第j个字符，或者在最左侧添加第j个字符得到。
        for (int j = 1; j < str.length; j++) {
            for (int i = j - 1; i >= 0; i--) {
                if (str[i] == str[j]) {
                    dp[i][j] = dp[i + 1][j - 1];
                } else {
                    int temp1 = Math.min(dp[i + 1][j] + del[str[i] - '0'],
                            dp[i + 1][j] + add[str[i] - '0']);
                    int temp2 = Math.min(dp[i][j - 1] + del[str[j] - '0'],
                            dp[i][j - 1] + add[str[j] - '0']);
                    dp[i][j] = Math.min(temp1, temp2);

                }
            }
        }
        System.out.println(dp[0][str.length - 1]);
        close();    //释放流资源
    }

    static boolean isHuiWen(String numStr) {
        int left = 0;
        int right = numStr.length() - 1;
        while (left < right) {
            if (numStr.charAt(left) == numStr.charAt(right)) {
                left++;
                right--;
            } else return false;
        }
        return true;
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