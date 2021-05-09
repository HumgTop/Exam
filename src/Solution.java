import org.junit.Test;
import utils.LeetCodeUtils;

import java.util.*;


/*

 */

public class Solution {

    public int purchasePlans(int[] nums, int target) {
        return 0;
    }

    int search(int[] nums, int target) {
        int left = 0, right = nums.length - 1;
        while (left <= right) {
            int mid = (left + right) / 2;
            if (nums[mid] < target) {
                left = mid + 1;
            } else if (nums[mid] == target) {
                while (mid < nums.length - 1 && nums[mid + 1] == target) {
                    mid++;
                }
                return mid;
            } else
                right = mid - 1;
        }
        return left - 1;
    }

    @Test
    public void test() {
        System.out.println(search(new int[]{1, 2, 3, 3, 5}, 1));
    }

    @Test
    public void test1() {
        System.out.println(purchasePlans(new int[]{2, 2, 1, 9}, 10));
    }
}