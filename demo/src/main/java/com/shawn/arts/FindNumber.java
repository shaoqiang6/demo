package com.shawn.arts;


/**
 * 给你一个整数数组 nums，请你返回其中位数为 偶数 的数字的个数。
 *
 * 示例 1：
 *
 * 输入：nums = [12,345,2,6,7896]
 * 输出：2
 * 解释：
 * 12 是 2 位数字（位数为偶数） 
 * 345 是 3 位数字（位数为奇数）  
 * 2 是 1 位数字（位数为奇数） 
 * 6 是 1 位数字 位数为奇数） 
 * 7896 是 4 位数字（位数为偶数）  
 * 因此只有 12 和 7896 是位数为偶数的数字
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/find-numbers-with-even-number-of-digits
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 * @author shawn
 * @date 2020-05-23
 */
public class FindNumber {

    public static void main(String[] args) {
        int[] nums = new int[]{1,12,123,1234,12345};
        int count = findNumber(nums);
        System.out.println(count);
        int number2 = findNumber2(nums);
        System.out.println(number2);
    }

    private static int findNumber(int[] nums){
        int count = 0;
        for(int num : nums) {
            if (String.valueOf(num).length() % 2 == 0){
                count ++;
            }
        }
        return count;
    }

    /**
      一个包含 k 个数字的整数 x 满足不等式 10^{k-1} <= x < 10^k 将不等式取对数，得到 k - 1 <= log_{10}(x) < k
     log_{10}(x) 向下取整，即double强转为int a ，这时 k =a + 1
     * @param nums
     * @return
     */
    private static int findNumber2(int[] nums){
        int count = 0;
        for(int num : nums) {
            if (((int)Math.log10(num)+1) % 2 == 0){
                count ++;
            }
        }
        return count;
    }
}
