package com.github.vkdev

/*
Write a function to find the longest common prefix string amongst an array of strings.

If there is no common prefix, return an empty string "".

Example 1:
Input: strs = ["flower","flow","flight"]
Output: "fl"

Example 2:
Input: strs = ["dog","racecar","car"]
Output: ""
Explanation: There is no common prefix among the input strings.

Constraints:
1 <= strs.length <= 200
0 <= strs[i].length <= 200
strs[i] consists of only lowercase English letters if it is non-empty.
* */

class _14_Longest_Common_Prefix {

    fun longestCommonPrefix(strs: Array<String>): String {
        if (strs.isEmpty()) return ""
        val maxLength = strs.minBy { it.length }.length
        if (maxLength == 0) return ""

        var result = ""
        for (i in 0..<maxLength) {
            var isEquals = true
            val current: Char = strs[0][i]

            for (s in 1..<strs.size) {
                if (strs[s][i] != current) {
                    isEquals = false
                    break
                }
            }

            if (isEquals) {
                result += current
            } else {
                break
            }
        }

        return result
    }
}

fun main() {
    val parser = _14_Longest_Common_Prefix()

    val tests = listOf(
        arrayOf("flower", "flow", "flight") to "fl",
        arrayOf("dog", "racecar", "car") to "",
        arrayOf<String>() to "",
        arrayOf("") to "",
        arrayOf("", "abc") to "",
        arrayOf("abc", "") to "",
        arrayOf("test", "test", "test") to "test",
        arrayOf("alone") to "alone",
        arrayOf("a", "b", "c") to "",
        arrayOf("interstellar", "internet", "internal") to "inter",
        arrayOf("car", "carpet", "carpool") to "car",
        arrayOf("Case", "casing") to ""
    )

    for ((i, test) in tests.withIndex()) {
        val (input, expected) = test
        val result = parser.longestCommonPrefix(input)
        println("Test ${i + 1}: ${result == expected} (expected='$expected', got='$result')")
    }
}