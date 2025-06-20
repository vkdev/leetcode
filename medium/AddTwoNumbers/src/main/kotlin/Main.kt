package com.github.vkdev

/*
Input: l1 = [2,4,3], l2 = [5,6,4]
Output: [7,0,8]
Explanation: 342 + 465 = 807.

Example 2:
Input: l1 = [0], l2 = [0]
Output: [0]

Example 3:
Input: l1 = [9,9,9,9,9,9,9], l2 = [9,9,9,9]
Output: [8,9,9,9,0,0,0,1]
* */
//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
fun main() {
    //Input: l1 = [2,4,3], l2 = [5,6,4] Output: [7,0,8]
    // Explanation: 342 + 465 = 807.
    val l1 = buildNode(listOf(2, 4, 3))
    val l2 = buildNode(listOf(5, 6, 4))

//    Input: l1 = [9,9,9,9,9,9,9], l2 = [9,9,9,9]
//    Output: [8,9,9,9,0,0,0,1]
//    val l1 = buildNode(listOf(9, 9, 9, 9, 9, 9, 9))
//    val l2 = buildNode(listOf(9, 9, 9, 9))


    val result = Solution().addTwoNumbers(l1, l2)

    System.out.println("Input:")
    l1?.let { System.out.println(nodeToString(it).reversed()) }
    l2?.let { System.out.println(nodeToString(it).reversed()) }

    System.out.println("\nOutput:")
    result?.let { System.out.println(nodeToString(it)) }
}

private fun nodeToString(node: ListNode): String {
    val sb = StringBuilder()
    var currentNode: ListNode? = node

    while (currentNode != null) {
        sb.append(currentNode.value)
        currentNode = currentNode.next
    }

    return sb.toString()
}

private fun buildNode(list: List<Int>): ListNode? {
    var node: ListNode? = null
    for (i in (list.size - 1) downTo 0) {
        val current = ListNode(list[i]).also { it.next = node }
        node = current
    }

    return node
}

class Solution {
    fun addTwoNumbers(l1: ListNode?, l2: ListNode?): ListNode? {

        var lastNode: ListNode? = ListNode(-1) // !!!
        val startNode: ListNode? = lastNode

        var l1Next = l1
        var l2Next = l2

        var inMemory = 0

        while (l1Next != null || l2Next != null) {

            val l1Value = l1Next?.value ?: 0
            val l2Value = l2Next?.value ?: 0

            val tempSum = l1Value + l2Value + inMemory
            val result: Int = tempSum % 10
            inMemory = tempSum / 10

            val newNode = ListNode(result)
            lastNode?.next = newNode
            lastNode = newNode

            l1Next = l1Next?.next
            l2Next = l2Next?.next
        }

        if (inMemory > 0) {
            lastNode?.next = ListNode(inMemory)
        }

        return startNode?.next
    }
}

class ListNode(val value: Int) {
    var next: ListNode? = null
}