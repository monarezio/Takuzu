package net.zdendukmonarezio.takuzu.domain.common.extensions

import org.junit.Test

import org.junit.Assert.*

/**
 * Created by samuelkodytek on 25/03/2017.
 */
class ListKtTest {
    private val list = listOf(1, 2, 3, 4)

    @Test
    fun set() {
        val newList = list.set(2, 100)
        assertEquals(newList[2], 100)
        assert(newList[3] != 100)
    }

    @Test
    fun isSame() {
        val list1 = listOf("Hello", "My name is", "John")
        val list2 = listOf("Hello", "My name is", "John")

        assertTrue(list1 == (list2))
        val list3 = list2.set(1, "Lol")
        assertFalse(list1 == (list3))
    }
}