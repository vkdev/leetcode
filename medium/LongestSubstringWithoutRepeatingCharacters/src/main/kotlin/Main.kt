package com.github.vkdev

import kotlin.math.max

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
/*
Example 1:

Input: s = "abcabcbb"
Output: 3
Explanation: The answer is "abc", with the length of 3.
Example 2:

Input: s = "bbbbb"
Output: 1
Explanation: The answer is "b", with the length of 1.
Example 3:

Input: s = "pwwkew"
Output: 3
Explanation: The answer is "wke", with the length of 3.
Notice that the answer must be a substring, "pwke" is a subsequence and not a substring.

* */
fun main() {
    System.out.println(lengthOfLongestSubstring("abcabcbbabcabcbbabcabcbbabcabcbb"))
}

fun lengthOfLongestSubstring(s: String): Int {

    if (s.isEmpty()) return 0

    var left = 0
    var max = 0
    val map = mutableMapOf<Char, Int>()

    for (right in s.indices) {

        val c = s[right]

        map[c]?.let {
            if (it >= left) {
                left = it + 1
            }
        }

        max = max(max, right - left + 1)
        map[c] = right
    }

    return max

    /*
        Runtime 11 ms
        Beats 66.79%
    * */
}