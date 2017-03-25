package net.zdendukmonarezio.takuzu.domain.models.extensions

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
}