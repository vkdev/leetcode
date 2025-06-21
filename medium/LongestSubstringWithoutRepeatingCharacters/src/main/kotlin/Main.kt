package com.github.vkdev

import kotlin.collections.set
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
    System.out.println(lengthOfLongestSubstringV2("abcabcbbabcabcbbabcabcbbabcabcbb"))
}

fun lengthOfLongestSubstringV2(s: String): Int {
    if (s.isEmpty()) return 0
    //оптимизация:
    /*
    * ChatGpt:
    * вопрос:
    * сколько символов удовлетворяют этому условию?
    * s consists of English letters, digits, symbols and spaces.
    *
    * ChatGPT сказал:
    * ....
    * Примерная сумма: 52 + 10 + 33 + 1 = 96 символов
    *
    * вопрос: какой код самый больший?
    * ChatGPT сказал:
    * Самый большой код ASCII среди разрешённых символов — 126 (тильда ~)
    * */

    var left = 0
    var max = 0

    val handledChars = IntArray(126 + 1) { -1 } //важно, что размер не менее 127
    s.forEachIndexed { right: Int, c: Char ->
        //суть – у нас есть массив
        //индексы которого – это символ в строке
        //значение – это индекс элемента в строке
        //то есть у нас получается точно такое же решение,
        // как в оригинальном решении на hashmap
        //давайте его просто скопируем:
        /*
        val c = s[right]

        map[c]?.let {
            if (it >= left) {
                left = it + 1
            }
        }

        max = max(max, right - left + 1)
        map[c] = right
        * */

        //и изменим map на массив
        handledChars[c.code].let {
            if (it >= left) {
                left = it + 1
            }
        }

        max = max(max, right - left + 1)
        handledChars[c.code] = right
    }

    return max
}

fun lengthOfLongestSubstring(s: String): Int {

    if (s.isEmpty()) return 0

    var left = 0
    var max = 0
    val map = mutableMapOf<Char, Int>()

    s.forEachIndexed { right, c ->
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