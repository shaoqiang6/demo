package com.shawn.arts;

/**
 * 给定一个非负整数 c ，你要判断是否存在两个整数 a 和 b，使得 a2 + b2 = c。
 * 示例1:
 *
 * 输入: 5
 * 输出: True
 * 解释: 1 * 1 + 2 * 2 = 5
 *  
 * 示例2:
 *
 * 输入: 3
 * 输出: False
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/sum-of-square-numbers
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 * @author shawn
 * @date 2020-04-06
 */
public class SumOfSquareNumbers {


    public static void main(String[] args) {
        boolean solution = solution(6);
        System.out.println(solution);
    }

    /**
     * a的范围 [0,sqrt(c)]
     * @param c
     * @return true or false
     */
    public static boolean solution(int c) {
        long a = 0;
        long b;
        while ((b = a*a) <= c) {
            double sqrt = Math.sqrt(c - b);
            if (sqrt == (int) sqrt) {
                return true;
            }
            a++;
        }
        return false;
    }

    /**
     * 费马平方和定理：<br/>
     * 一个非负整数 c 能够表示为两个整数的平方和，当且仅当 c 的所有形如 4k+3 的质因子的幂次均为偶数。
     * @param c
     * @return
     */
    public static boolean solution2(int c) {


        return false;
    }
}
