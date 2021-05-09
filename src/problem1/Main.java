package problem1;

import java.io.*;
import java.util.*;

// 本地测试和牛客提交代码一致，无须修改相关
public class Main {
    static BufferedReader reader = getReader(); //初始化流
    static BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out));


    public static void main(String[] args) throws IOException {
        int[] params = getNextArr();
        int n = params[0];
        int m = params[1];


        //边权值最小堆
        PriorityQueue<int[]> minHeap = new PriorityQueue<>(Comparator.comparingInt(o -> o[1]));

        int k = params[2];
        boolean[] seen = new boolean[n * m + 1];
        int[] minDist = new int[n * m + 1];
        Arrays.fill(minDist, Integer.MAX_VALUE);

        HashMap<Integer, List<int[]>> map = new HashMap<>();

        for (int i = 0; i < k; i++) {
            int[] arr = getNextArr();
            //2个节点坐标
            int p1Idx = getIdx(arr[0], arr[1], m);
            int p2Idx = getIdx(arr[2], arr[3], m);
            //构建邻接表
            map.computeIfAbsent(p1Idx, key -> new ArrayList<>()).add(new int[]{p2Idx, arr[4]});
            map.computeIfAbsent(p2Idx, key -> new ArrayList<>()).add(new int[]{p1Idx, arr[4]});
        }

        minHeap.add(new int[]{1, 0});   //添加起点
        int destIdx = n * m;    //终点坐标

        //遍历最小堆，直到起点与终点联通
        while (!minHeap.isEmpty()) {
            int[] cur = minHeap.remove();
            int transferIdx = cur[0];   //当前中转点坐标
            int transferDist = cur[1];  //中转距离
            if (seen[transferIdx]) continue;
            seen[transferIdx] = true;

            //遍历中转点的邻接表
            for (int[] neighbor : map.get(transferIdx)) {
                //优化路径：直达或者从中转点中转到达
                minDist[neighbor[0]] = Math.min(minDist[neighbor[0]], transferDist + neighbor[1]);
                minHeap.add(new int[]{neighbor[0], minDist[neighbor[0]]});
            }
        }
        if (minDist[destIdx] != Integer.MAX_VALUE) {
            print(minDist[destIdx]);
        } else {
            //终点不可达
            print(-1);
        }
        newLine();

        close();    //释放流资源
    }


    //坐标一维化
    static int getIdx(int r, int c, int m) {
        return (r - 1) * m + c;
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