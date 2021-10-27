package com.shawn.arts.a0003;

import com.google.common.collect.Sets;
import org.junit.Test;

import java.util.HashSet;
import java.util.Set;

/**
 * @author ysq
 * @date 2020/7/12
 */
public class Solution {

    @Test
    public void test() {
        String s = "abcabcbb";
        int i = longestSubstringWithoutRepeatingCharacters(s);
        System.out.println(i);
    }

    /**
     * 给定一个字符串，请你找出其中不含有重复字符的 最长子串 的长度。
     *
     * 示例 1:
     *
     * 输入: "abcabcbb"
     * 输出: 3
     * 解释: 因为无重复字符的最长子串是 "abc"，所以其长度为 3。
     * 示例 2:
     *
     * 输入: "bbbbb"
     * 输出: 1
     * 解释: 因为无重复字符的最长子串是 "b"，所以其长度为 1。
     * 示例 3:
     *
     * 输入: "pwwkew"
     * 输出: 3
     * 解释: 因为无重复字符的最长子串是 "wke"，所以其长度为 3。
     *      请注意，你的答案必须是 子串 的长度，"pwke" 是一个子序列，不是子串。
     * 通过
     *
     * 来源：力扣（LeetCode）
     * 链接：https://leetcode-cn.com/problems/longest-substring-without-repeating-characters
     * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
     * @param s 字符串
     * @return 最大不重复的子字符串长度
     */
    private int longestSubstringWithoutRepeatingCharacters(String s) {
        // ans 答案， rk右指针， size字符串长度
        int ans = 0,
                rk = -1,
                size = s.length();
        Set<Character> set = new HashSet<>(size);
        for (int i = 0; i < size; ++i) {
            // 左指针向右移动一个，删除左指针的前一个字符
            if (i != 0) {
                set.remove(s.charAt(i - 1));
            }
            // 最右侧的字符
            char rightString;
            // 右指针一直向右移动看下右面的一个字符是否已经存在，如果不存在则加到set中并且将指针右移1，直到包含同样的字符
            while (rk + 1 < size && !set.contains((rightString = s.charAt(rk + 1)))) {
                set.add(rightString);
                rk++;
            }
            // rk - i + 1 右索引减去做索引，两头都算 +1.
            ans = Math.max(ans, rk - i + 1);
        }
        return ans;
    }
}
