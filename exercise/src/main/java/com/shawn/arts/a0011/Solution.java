package com.shawn.arts.a0011;

import org.junit.Test;

/**盛最多水的容器
 * 给你 n 个非负整数 a1，a2，...，an，每个数代表坐标中的一个点 (i, ai) 。在坐标内画 n 条垂直线，垂直线 i 的两个端点分别为 (i, ai) 和 (i, 0)。找出其中的两条线，使得它们与 x 轴共同构成的容器可以容纳最多的水。
 *
 * 说明：你不能倾斜容器，且 n 的值至少为 2。
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/container-with-most-water
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 * 示例：
 *
 * 输入：[1,8,6,2,5,4,8,3,7]
 * 输出：49
 * @author ysq
 * @date 2020/8/3
 */
public class Solution {

    @Test
    public void test() {
        int[] nums = new int[]{1,8,6,2,5,4,8,3,7};
        int i = maxArea(nums);
        System.out.println(i);
    }

    private int maxArea(int[] height) {
        int leftIndex = 0, rightIndex = height.length - 1, answer = 0;
        while (leftIndex < rightIndex ) {
            int left = height[leftIndex];
            int right = height[rightIndex];
            int area = Math.min(left, right) * (rightIndex - leftIndex);
            answer = Math.max(area, answer);
            if (left <= right) {
                leftIndex++;
            } else {
                rightIndex--;
            }
        }
        return answer;
    }
}
