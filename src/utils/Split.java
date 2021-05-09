package utils;

public class Split {
    /**
     * @param s     待分割字符串
     * @param regex 分割字符串的正则表达式
     * @return 分割后的字符串转为的int数组
     */
    static int[] getArr(String s, String regex) {
        String[] arr = s.split(regex);
        int[] res = new int[arr.length];
        for (int i = 0; i < arr.length; i++) {
            res[i] = Integer.parseInt(arr[i]);
        }
        return res;
    }
}
