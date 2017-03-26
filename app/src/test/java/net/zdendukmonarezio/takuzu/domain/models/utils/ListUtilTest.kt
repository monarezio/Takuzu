package net.zdendukmonarezio.takuzu.domain.models.utils

import net.zdendukmonarezio.takuzu.domain.models.Field
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

    @Test
    fun syncFields() {
        val pairs = ListUtil.randomPairs(4, 16, 16)
        val list = List(16) {List(16) { Field.ANON}}

        val newList = ListUtil.syncFields(list, pairs)

        for (i in 0..list.size-1) {
            for (j in 0..list[i].size-1) {
                if(pairs.contains(Pair(i, j)))
                    assert(newList[i][j] != Field.ANON)
                else
                    assert(newList[i][j] == Field.ANON)
            }
        }
    }
}