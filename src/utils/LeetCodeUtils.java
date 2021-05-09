package utils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class LeetCodeUtils {
    public static void main(String[] args) {
        //测试get2dList（元素为不等长的一维数组）
        String inputStr0 = "[[-68,97,3],[-34],[60,-100,4,1,4,5],[2,31]]";
        List<int[]> res = get2dList(inputStr0);

        for (int[] arr : res) {
            System.out.println(Arrays.toString(arr));
        }

        System.out.println("-------");

        //测试get1dArr
        String inputStr2 = "[1,-2,33,-44]";
        int[] res2 = get1dArr(inputStr2);
        System.out.println(Arrays.toString(res2));
    }

    /**
     * @param inputStr 需要解析的字符串
     * @return 返回字符串构成的二维List（元素为不等长的一维数组）
     */
    public static List<int[]> get2dList(String inputStr) {
        Matcher matcher = Pattern.compile("\\[([\\-?\\d|,]+)]").matcher(inputStr);
        //temp暂存解析数据
        List<int[]> parent = new ArrayList<>();

        while (matcher.find()) {
            //将每个一维数组添加到temp中
            Matcher matcherChild = Pattern.compile("([\\-]?\\d+)").matcher(matcher.group());
            List<Integer> child = new ArrayList<>();
            while (matcherChild.find()) {
                child.add(Integer.valueOf(matcherChild.group()));
            }

            //将一维数组添加到parent集合中
            parent.add(child.stream().mapToInt(Integer::valueOf).toArray());
        }

        return parent;
    }

    /**
     * @param inputStr 需要解析的字符串
     * @return 返回字符串构成的二维数组
     */
    public static int[][] get2dArr(String inputStr) {
        return get2dList(inputStr).toArray(new int[0][0]);
    }

    /**
     * @param inputStr 需要解析的一维数组字符串
     * @return 返回字符串构成的一维数组
     */

    public static int[] get1dArr(String inputStr) {
        Matcher matcher = Pattern.compile("([\\-]?\\d+)").matcher(inputStr);
        //temp暂存解析数据
        ArrayList<Integer> temp = new ArrayList<>();

        while (matcher.find()) {
            //将每个解析的数字添加到temp中
            temp.add(Integer.parseInt(matcher.group()));
        }

        return temp.stream().mapToInt(Integer::valueOf).toArray();
    }
}
