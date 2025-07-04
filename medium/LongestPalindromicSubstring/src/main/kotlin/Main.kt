package com.github.vkdev

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
fun main() {
    System.out.println(longestPalindrome("aaabaaaa"))
    System.out.println(longestPalindrome("ababababa"))
    System.out.println(longestPalindrome("azczbdkacaa"))
    System.out.println(longestPalindrome("cbb"))
    System.out.println(longestPalindrome("ac"))
    System.out.println(longestPalindrome("cccc"))
    System.out.println(longestPalindrome("sbabad"))
    System.out.println(longestPalindrome("xyztarratzyx"))
    System.out.println(longestPalindrome("abb"))
    System.out.println(longestPalindrome("cbbd"))
    System.out.println(longestPalindrome("a"))
    System.out.println(longestPalindrome("babad"))
}

fun longestPalindrome(s: String): String {
    if (s.length <= 1) {
        return s //один символ тоже палиндром?
    }

    if (s.length == 2) {
        return if (s[0] == s[1]) s else s[0].toString()
    }

    var position = 0
    var maxPalindromeStart = 0
    var maxPalindromeEnd = 0

    while (position < s.length - 1) {

        var left = position
        var right = position

        //aaaaaaa
        while (right < s.length - 1 && s[left] == s[right + 1]) {
            right++
            if (right - left > maxPalindromeEnd - maxPalindromeStart) {
                maxPalindromeStart = left
                maxPalindromeEnd = right
            }
        }

        var isPalindrome = right < s.length - 1
        while (isPalindrome) {

            //aba
            isPalindrome = left > 0 && right < s.length - 1
            while (isPalindrome && left > 0 && right < s.length - 1) {
                isPalindrome = false
                if (s[left - 1] == s[right + 1]) {
                    left--
                    right++
                    isPalindrome = true

                    if (right - left > maxPalindromeEnd - maxPalindromeStart) {
                        maxPalindromeStart = left
                        maxPalindromeEnd = right
                    }
                }
            }
        }

        position++
    }

    return s.substring(maxPalindromeStart, maxPalindromeEnd + 1)
}

/*
Runtime 1232 ms
Beats 7.85%
O(n^3)
* */
fun longestPalindromeOn3(s: String): String {

    if (s.length <= 1) {
        return s //один символ тоже палиндром?
    }

    var result = s.first().toString()

    //берем окошко
    //сначала полная строка
    //затем длина строки - 1
    //проверяем, это палиндром? если нет, то сдвигаем ее на 1, и проверяем снова
    //далее и далее, пока окошко не станет равным 2

    var windowSize = s.length

    while (windowSize >= 2) {
        var left = s.length - windowSize
        while (left >= 0) {
            val subString = s.substring(left, left + windowSize)

            if (isPalindromeFull(subString)) {
                val subStringLength = subString.length
                if (result.length < subStringLength) {
                    result = subString
                }
            }
            left--
        }

        windowSize--
    }

    return result
}

fun isPalindromeFull(s: String): Boolean {
    val maxIndex = s.length - 1
    s.forEachIndexed { i, c ->
        if (c != s[maxIndex - i]) return false
    }

    return true
}