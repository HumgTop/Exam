package problem1;

import java.util.HashMap;

public class Main {
    public static void main(String[] args) {
        int[][] arr = {{2, 1, 5}, {3, 3, 7}};
        System.out.println(solution(arr, 5));
    }

    public static boolean solution(int[][] load, int capacity) {
        int maxEnd = 0;
        HashMap<Integer, Integer> start = new HashMap<>();  //key为起点，value为加载重量
        HashMap<Integer, Integer> end = new HashMap<>();    //key为终点，value为卸载重量
        for (int[] each : load) {
            maxEnd = Math.max(maxEnd, each[2]);
            start.put(each[1], start.getOrDefault(each[1], 0) + each[0]);
            end.put(each[2], start.getOrDefault(each[2], 0) + each[0]);
        }

        int curWeight = 0;
        for (int i = 0; i <= maxEnd; i++) {
            int up = start.getOrDefault(i, 0);
            int down = end.getOrDefault(i, 0);
            curWeight = curWeight - down + up;
            if (curWeight > capacity) {
                return false;
            }
        }
        return true;


    }
}