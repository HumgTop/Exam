package problem2;

import java.io.*;
import java.util.*;
import java.util.stream.Collectors;

// 本地测试和牛客提交代码一致，无须修改相关
public class Main {
    static BufferedReader reader = getReader(); //初始化流
    static BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out));


    public static void main(String[] args) throws IOException {
        int n = getNextInt();
        int[] degrees = new int[n]; //记录任务i的依赖的任务数
        boolean[] seen = new boolean[n];

        HashMap<Integer, Set<Integer>> map = new HashMap<>();
        int[] times = new int[n];
        for (int i = 0; i < n; i++) {
            String s = reader.readLine();
            String[] split = s.split(" ");
            times[i] = Integer.parseInt(split[1]);
            String[] arr = split[0].split(",");
            degrees[i] = arr.length;
            //遍历前驱节点
            for (String val : arr) {
                int preTaskIdx = Integer.parseInt(val);
                if (preTaskIdx == -1) {
                    degrees[i] = 0;
                }
                map.computeIfAbsent(preTaskIdx, k -> new HashSet<>()).add(i);
            }
        }

        System.out.println(solution(n, degrees, map, times));
        close();    //释放流资源
    }

    private static int solution(int n, int[] degrees, HashMap<Integer, Set<Integer>> map, int[] times) {
        Queue<Integer> queue = new LinkedList<>();
        int[] dp = new int[n];  //dp[i]表示完成任务i所需要的时间
        for (int i = 0; i < degrees.length; i++) {
            if (degrees[i] == 0) {
                queue.add(i);
                //第一批任务没有前置执行时间
                dp[i] = times[i];
            }
        }

        int cnt = 0;
        while (!queue.isEmpty()) {
            cnt++;
            Integer taskIdx = queue.remove();
            //如果当前节点无后继节点则跳过
            if (!map.containsKey(taskIdx)) continue;

            for (int nextTaskIdx : map.get(taskIdx)) {
                degrees[nextTaskIdx] -= 1;
                dp[nextTaskIdx] = Math.max(dp[nextTaskIdx], dp[taskIdx] + times[nextTaskIdx]); //刷新下个任务的执行时间
                if (degrees[nextTaskIdx] == 0) {
                    queue.add(nextTaskIdx);
                }
            }
        }
        if (cnt != n) return -1;    //说明存在环（循环依赖）

        return Arrays.stream(dp).max().getAsInt();
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