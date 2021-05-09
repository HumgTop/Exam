import org.junit.Test;

import java.util.Arrays;


/*

 */

public class Solution2 {

    public int purchasePlans(int[] nums, int target) {
        if (nums.length == 0) return 0;
        Arrays.sort(nums);
        int res = 0;
        for (int i = 0; i < nums.length; i++) {
            for (int j = i + 1; j < nums.length; j++) {
                if (nums[i] + nums[j] <= target) res = (res % 1000000007) + 1;
                else break;
            }
        }

        return res;
    }

    @Test
    public void test() {

    }
}