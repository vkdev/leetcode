package com.github.vkdev

import kotlin.contracts.contract
import kotlin.math.sign

fun main(args: Array<String>) {
//Roman numerals are represented by seven different symbols: I, V, X, L, C, D and M.
//
//Symbol       Value
//I             1
//V             5
//X             10
//L             50
//C             100
//D             500
//M             1000
//For example, 2 is written as II in Roman numeral, just two ones added together. 12 is written as XII, which is simply X + II. The number 27 is written as XXVII, which is XX + V + II.
//
//Roman numerals are usually written largest to smallest from left to right. However, the numeral for four is not IIII. Instead, the number four is written as IV. Because the one is before the five we subtract it making four. The same principle applies to the number nine, which is written as IX. There are six instances where subtraction is used:
//
//I can be placed before V (5) and X (10) to make 4 and 9.
//X can be placed before L (50) and C (100) to make 40 and 90.
//C can be placed before D (500) and M (1000) to make 400 and 900.
//Given a roman numeral, convert it to an integer.
//
//
//
//Example 1:
//Input: s = "III"
//Output: 3
//Explanation: III = 3.

//Example 2:
//Input: s = "LVIII"
//Output: 58
//Explanation: L = 50, V= 5, III = 3.

//Example 3:
//Input: s = "MCMXCIV"
//Output: 1994
//Explanation: M = 1000, CM = 900, XC = 90 and IV = 4.

//DCCXLIV is 766; expected 744
//System.out.println(romanToInt("DCCXLIV"))
//System.out.println(romanToInt2("IX"))


    val map = TestDataGenerator.generateUniquePatternRomans()
    var isPassed = true
    map.forEach { k, v ->
        val result = romanToInt2(k)
        if (result != v) {
            println("$k is $result; expected $v")
            isPassed = false
        }
    }

    if (isPassed) println("All tests passed") else println("Tests failed")
}

private val symbols = mapOf<Char, Int>(
    'I' to 1,
    'V' to 5,
    'X' to 10,
    'L' to 50,
    'C' to 100,
    'D' to 500,
    'M' to 1000
)

fun symbol(char: Char) = symbols[char]!!

fun romanToInt2(s: String): Int {
    if (s.isEmpty()) return 0
    if (s.length == 1) return symbol(s[0])
    //Ах! я упустил, что вариант есть IX, но нет. IIX => если следующее число больше предыдущего,
    // то мы вычитаем текущее из общей суммы, иначе – прибавляем
    var result = 0
    for (i in 0..s.length - 2) {

        val current = symbol(s[i])
        val next = symbol(s[i + 1])

        if (next > current) {
            result -= current
        } else {
            result += current
        }
    }

    return result + symbol(s[s.length -1])
}

fun romanToInt(s: String): Int {
    if (s.isEmpty()) return 0
    if (s.length == 1) return symbol(s[0])

    var result = 0
    var currentSum = 0
    var prevValue = 0

    s.forEachIndexed { index, c ->

        val next: Int? = if (index < s.length - 1) {
            symbol(s[index + 1])
        } else {
            null
        }

        val currentValue = symbol(c)

        when {
            //I or II
            (prevValue == 0 || prevValue == currentValue) -> {
                currentSum += currentValue
                if (next == null) result += currentSum
            }
            //IX
            prevValue < currentValue -> {
                result += currentValue - currentSum
                currentSum = 0
            }

            //XII
            else /* prevValue > currentValue */ -> {
                if (next != currentValue) {
                    result += currentSum
                    currentSum = 0
                }

                currentSum += currentValue
                if (next == null) {
                    result += currentSum
                }
            }
        }

        prevValue = currentValue
    }

    return result
}

object TestDataGenerator {
    fun generateUniquePatternRomans(): HashMap<String, Int> {
        val result = HashMap<String, Int>()

        for (n in 1..3999) {
            val r = toRoman(n)
            val pattern = romanPattern(r)
            if (!result.containsKey(pattern)) {
                result[r] = n
            }
        }
        return result
    }

    // --- Roman conversion ---
    private val numerals = listOf(
        1000 to "M", 900 to "CM", 500 to "D", 400 to "CD",
        100 to "C", 90 to "XC", 50 to "L", 40 to "XL",
        10 to "X", 9 to "IX", 5 to "V", 4 to "IV", 1 to "I"
    )

    fun toRoman(num: Int): String {
        var n = num
        val sb = StringBuilder()
        for ((v, s) in numerals) {
            while (n >= v) {
                sb.append(s)
                n -= v
            }
        }
        return sb.toString()
    }

    // --- Pattern generation ---
    fun romanPattern(s: String): String {
        val map = HashMap<Char, Char>()
        var next = 'A'
        val out = StringBuilder()

        for (c in s) {
            if (!map.containsKey(c)) {
                map[c] = next
                next++
            }
            out.append(map[c])
        }
        return out.toString()
    }
}