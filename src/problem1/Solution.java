package problem1;

import java.util.*;


public class Solution {
    /**
     * @param str string字符串 the string
     * @return string字符串
     */
    public static void main(String[] args) {
        System.out.println(new Solution().changespaceto20("a  b  c"));
    }
    public String changespaceto20(String str) {
        // write code here
        String s = str.replaceAll(" ", "%20");
        return s;
    }
}
