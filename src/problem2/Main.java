package problem2;


import java.util.*;

// 本地测试和牛客提交代码一致，无须修改相关
public class Main {
    static Scanner reader = new Scanner(System.in); //初始化流


    public static void main(String[] args) {
        int n = getNextInt();
        Map<String, List<String>> classMap = new HashMap<>();
        Map<String, List<String>> instanceMap = new HashMap<>();
        String s1 = "subClassOf";

        for (int i = 0; i < n; i++) {
            String[] s = reader.nextLine().split(" ");
            if (s[1].equals(s1)) {
                classMap.computeIfAbsent(s[2], k -> new ArrayList<>()).add(s[0]);
            } else {
                instanceMap.computeIfAbsent(s[2], k -> new ArrayList<>()).add(s[0]);
            }
        }
        String target = reader.nextLine();
        System.out.print(solution(classMap, instanceMap, target));
    }

    static List<String> res = new ArrayList<>();

    private static String solution(Map<String, List<String>> classMap, Map<String, List<String>> instanceMap, String target) {
        addToRes(target, classMap, instanceMap);

        res.sort(((o1, o2) -> (o1 + o2).compareTo(o2 + o1)));
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < res.size() - 1; i++) {
            sb.append(res.get(i)).append(" ");
        }
        sb.append(res.get(res.size() - 1));
        return sb.toString();
    }

    static void addToRes(String key, Map<String, List<String>> classMap, Map<String, List<String>> instanceMap) {
        if (classMap.containsKey(key)) {
            List<String> sub = classMap.get(key);
            for (String k : sub) {
                addToRes(k, classMap, instanceMap);
            }
        }
        if (instanceMap.containsKey(key)) {
            res.addAll(instanceMap.get(key));
        }
    }

    /**
     * @return 获取下一行的单个int数据
     */
    static int getNextInt() {
        return Integer.parseInt(reader.nextLine());
    }

}