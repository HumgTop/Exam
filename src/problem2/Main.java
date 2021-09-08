package problem2;

import java.io.*;
import java.util.ArrayList;

// 本地测试和牛客提交代码一致，无须修改相关
public class Main {
    static BufferedReader reader = getReader(); //初始化流


    public static void main(String[] args) throws IOException {
        int n = getNextInt();
        for (int i = 0; i < n; i++) {
            String input = reader.readLine();
            System.out.println(solution(input));
        }
    }

    private static String solution(String input) {
        char[] chs = input.toCharArray();
        ArrayList<Character> list = new ArrayList<>();
        for (int i = 0; i < chs.length; i++) {
            if (1 <= chs[i] - '0' && chs[i] - '0' <= 3) {
                list.add(chs[i]);
            } else if (chs[i] - '0' > 3) {
                list.add('3');
            } else {
                //当前位为0
                Character cur = list.get(i - 1);
                list.set(i - 1, (char) ((int) cur - 1));    //前位减一，后位全置3
                while (i < chs.length) {
                    list.add('3');
                    i++;
                }
            }
        }

        StringBuilder sb = new StringBuilder();
        for (Character ch : list) {
            if (ch == '0') continue;
            sb.append(ch);
        }
        return sb.toString();
    }


//-------------------------------------------------以下为IO工具方法------------------------------------------------------------------


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

}