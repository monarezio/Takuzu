package net.zdendukmonarezio.takuzu.domain.models.utils

import org.junit.Test

import org.junit.Assert.*

/**
 * Created by samuelkodytek on 07/03/2017.
 */
class ListUtilTest {
    @Test
    fun randomPairs() {
        val list1 = ListUtil.randomPairs(4, 16, 16)
        val list2 = ListUtil.randomPairs(4, 16, 16)

        assert(!list1.equals(list2)) //Two list do not equal
    }
}