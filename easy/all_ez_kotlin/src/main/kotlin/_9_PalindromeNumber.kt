package com.github.vkdev

import kotlin.math.floor
import kotlin.math.log10
import kotlin.math.pow
import kotlin.math.roundToInt

/*
Example 1:

Input: x = 121
Output: true
Explanation: 121 reads as 121 from left to right and from right to left.


Example 2:
Input: x = -121
Output: false
Explanation: From left to right, it reads -121. From right to left, it becomes 121-. Therefore it is not a palindrome.

Example 3:
Input: x = 10
Output: false
Explanation: Reads 01 from right to left. Therefore it is not a palindrome.

Constraints:

-231 <= x <= 231 - 1
* */

fun main() {
    //все отрицательные числа – не палиндром
    //одна любая цифра – палиндром
    //палиндромы:
    //1
    //121
    //22
    //1221

    val x = 11
    val resultIsPalindrome = isPalindrome(x)

    System.out.println("Is Palindrome $resultIsPalindrome")
}

fun isPalindrome(x: Int): Boolean {

    //все отрицательные числа – не палиндром
    if (x < 0) {
        return false
    }

    //одна любая цифра – палиндром
    if (x < 10) {
        return true
    }

    //все что заканчивается на ноль не палиндром. ноль то палиндром, см выше
    if (x % 10 == 0) {
        return false
    }

    var forward = x
    var reverse = 0

    // проверяем чтобы 1234 было 4321 наоборот
    // догадался, посмотрев решение. хм.
    while (forward > reverse) {
        reverse = forward % 10 + reverse * 10
        forward = forward / 10
    }

    return forward == reverse || forward == reverse / 10
}

fun isPalindromeV1(x: Int): Boolean {

    if (x < 0) {
        return false
    }

    val digitCount: Int = if (x == 0) 1 else floor(log10(x.toDouble())).roundToInt() + 1
    if (digitCount == 1) {
        return true
    }

    var index = 0

    var newX = x
    var newDigitsCount = digitCount

    while (index < digitCount / 2) {
        newDigitsCount--
        val powNewDigitPosition = (10.toDouble().pow(newDigitsCount)).roundToInt()
        val left = newX / (powNewDigitPosition)
        val right = newX - ((newX / 10) * 10)
        if (left != right) {
            return false
        }

        newX = newX % (powNewDigitPosition) / 10

        newDigitsCount--

        index++
    }

    return true
}