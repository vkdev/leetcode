package com.github.vkdev

/**
 * Example:
 * var li = ListNode(5)
 * var v = li.`val`
 * Definition for singly-linked list.
 * class ListNode(var `val`: Int) {
 *     var next: ListNode? = null
 * }
 */


fun main() {
    // Input: list1 = [1,2,4], list2 = [1,3,4]
    // Output: [1,1,2,3,4,4]
    val list1 = buildList(listOf(1, 2, 4))
    val list2 = buildList(listOf(1, 3, 4))

    MergeTwoSortedLists().mergeTwoListsWithRecursion(list1, list2)?.toList()?.toString().let {
        println("result = $it")
    }

    MergeTwoSortedLists().mergeTwoListsWithLoop(list1, list2)?.toList()?.toString().let {
        println("result = $it")
    }

    MergeTwoSortedLists().mergeTwoListsUsingClaudeAi(list1, list2)?.toList()?.toString().let {
        println("result = $it")
    }
}

fun buildList(list: List<Int>): ListNode? {
    if (list.isEmpty()) return null
    var current: ListNode? = null
    list.reversed().forEach {
        val node = ListNode(it)
        node.next = current
        current = node
    }

    return current
}

class ListNode(var `val`: Int) {
    var next: ListNode? = null

    fun toList(): List<Int> {
        return mutableListOf<Int>().also {
            toList(this, it)
        }
    }

    private fun toList(listNode: ListNode, list: MutableList<Int>) {
        list.add(listNode.`val`)
        listNode.next?.let {
            toList(it, list)
        }
    }
}

class MergeTwoSortedLists {

    fun mergeTwoListsWithRecursion(list1: ListNode?, list2: ListNode?): ListNode? {
        if (list1 == null) return list2
        if (list2 == null) return list1

        if (list1.`val` > list2.`val`) {
            list2.next = mergeTwoListsWithRecursion(list1, list2.next)
            return list2
        } else {
            list1.next = mergeTwoListsWithRecursion(list1.next, list2)
            return list1
        }
    }

    fun mergeTwoListsWithLoop(list1: ListNode?, list2: ListNode?): ListNode? {
        if (list1 == null) return list2
        if (list2 == null) return list1

        var current: ListNode? = null
        var result: ListNode? = null

        var node1: ListNode? = list1
        var node2: ListNode? = list2

        // Input: list1 = [1,2,4], list2 = [1,3,4]
        // Output: [1,1,2,3,4,4]

        var hasNext = true
        while (hasNext) {
            if (current != null && node1 == null) {
                current.next = node2
                hasNext = false
            } else if (current != null && node2 == null) {
                current.next = node1
                hasNext = false
            } else if (node1!!.`val` > node2!!.`val`) {
                val newNode2 = ListNode(node2.`val`)
                if (current == null) {
                    current = newNode2
                } else {
                    current.next = newNode2
                    current = current.next
                }
                node2 = node2.next
            } else {
                val newNode1 = ListNode(node1.`val`)
                if (current == null) {
                    current = newNode1
                } else {
                    current.next = newNode1
                    current = current.next
                }
                node1 = node1.next
            }

            if (result == null) result = current
        }

        return result
    }

    fun mergeTwoListsUsingClaudeAi(list1: ListNode?, list2: ListNode?): ListNode? {
        // Создаем фиктивный узел для упрощения логики
        val dummy = ListNode(0)
        var current = dummy

        var l1 = list1
        var l2 = list2

        // Проходим по обоим спискам, пока оба не пусты
        while (l1 != null && l2 != null) {
            if (l1.`val` <= l2.`val`) {
                current.next = l1
                l1 = l1.next
            } else {
                current.next = l2
                l2 = l2.next
            }
            current = current.next!!
        }

        // Присоединяем оставшиеся элементы
        current.next = l1 ?: l2

        return dummy.next
    }
}