package com.github.vkdev


/*
Given a string s containing just the characters '(', ')', '{', '}', '[' and ']', determine if the input string is valid.

An input string is valid if:
Open brackets must be closed by the same type of brackets.
Open brackets must be closed in the correct order.
Every close bracket has a corresponding open bracket of the same type.


Example 1:
Input: s = "()"
Output: true

Example 2:
Input: s = "()[]{}"
Output: true

Example 3:
Input: s = "(]"
Output: false

Example 4:
Input: s = "([])"
Output: true

Example 5:
Input: s = "([)]"
Output: false

Constraints:
1 <= s.length <= 104
s consists of parentheses only '()[]{}'.
* */
class Solution {

    companion object {
        val solution = Solution()

        @JvmStatic
        fun main(args: Array<String>) {

            mapOf(
                "()" to true,
                "()[]{}" to true,
                "(]" to false,
                "([])" to true,
                "([)]" to false,
                "(([]){})" to true,
            ).forEach {
                println("${it.key} result: ${solution.isValid(it.key)}; expected: ${it.value}")
            }
        }
    }

    fun Char.isOpen() = when (this) {
        '[', '{', '(' -> true
        else -> false
    }

    fun Char.isClose() = !this.isOpen()

    fun isBrackets(open: Char, close: Char): Boolean {
        return open == '[' && close == ']' ||
                open == '{' && close == '}' ||
                open == '(' && close == ')'
    }

    fun isSome(open: Char, close: Char): Boolean {
        return open == '[' && close == ']' ||
                close == '[' && open == ']' ||

                open == '{' && close == '}' ||
                close == '{' && open == '}' ||

                open == '(' && close == ')' ||
                close == '(' && open == ')'
    }

    fun isValid(s: String): Boolean {
        if (s.isEmpty()) return false
        if (s.length % 2 != 0) return false
        if (s[0].isClose()) return false
        if (s[s.length - 1].isOpen()) return false

        val stack = ArrayDeque<Char>()
        for(i in 0..<s.length) {
            val current = s[i]
            val prev = stack.lastOrNull()
            if (current.isOpen()) {
                stack.addLast(current)
            } else if (prev != null) {
                val isBrackets = isBrackets(prev, current)
                if (isBrackets) {
                    stack.removeLast()
                } else {
                    return false
                }
            } else if(current.isClose()) {
                return false
            }
        }

        return stack.isEmpty()
    }
}