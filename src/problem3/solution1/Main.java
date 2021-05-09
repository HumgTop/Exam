package problem3.solution1;

import java.io.*;

// 本地测试和牛客提交代码一致，无须修改相关
public class Main {
    static BufferedReader reader = getReader(); //初始化流
    static BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out));


    public static void main(String[] args) throws IOException {
        int t = getNextInt();
        for (int i = 0; i < t; i++) {
            int[] params = getNextArr();
            int x = params[0];
            int a = params[1];
            int b = params[2];
            int n = params[3];
            solution(x, a, b, n);
        }

        close();    //释放流资源
    }

    /**
     * 核心函数：题目的核心逻辑
     */
    static void solution(int x, int a, int b, int n) throws IOException {
        State state = new State(x, 0, 0);
        print(dfs(state, a, b, n));
        newLine();
    }


    static int dfs(State state, int a, int b, int n) {
        if (state.hourCnt == n) {
            return state.profit;
        }
        //本轮休息
        int p1 = dfs(new State(state.state + b, state.profit, state.hourCnt + 1), a, b, n);
        int p2 = dfs(new State(Math.max(0, state.state - a), state.profit + state.state, state.hourCnt + 1), a, b, n);
        return Math.max(p1, p2);
    }

    static class State {
        int state = 0;
        int profit = 0;
        int hourCnt = 0;

        public State(int state, int profit, int hourCnt) {
            this.state = state;
            this.profit = profit;
            this.hourCnt = hourCnt;
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
            return new BufferedReader(new FileReader("src/problem3/test.txt"));
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