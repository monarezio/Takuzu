package net.zdendukmonarezio.takuzu.domain.models.utils

import net.zdendukmonarezio.takuzu.domain.models.Field
import net.zdendukmonarezio.takuzu.domain.models.extensions.random
import net.zdendukmonarezio.takuzu.domain.models.extensions.set

/**
 * Created by samuelkodytek on 07/03/2017.
 */
object ListUtil {
    /**
     * generate a list a pairs with random numbers
     */
    fun randomPairs(amount: Int, min: Int, secondMin: Int): List<Pair<Int, Int>> {
        fun pairs(pairs: List<Pair<Int, Int>> = emptyList(), amount: Int = amount): List<Pair<Int, Int>> {
            if(amount == 0) return pairs

            val x = Int.random(0, min - 1)
            val y = Int.random(0, secondMin - 1)
            val pair = Pair(x, y)
            if(pairs.filter { i -> i.equals(pair) }.isEmpty())
                return pairs(pairs.plus(pair), amount-1)
            return pairs(pairs.plus(pair), amount)
        }

        return pairs()
    }


    /**
     * returns list with set fields on locked fields
     */
    fun syncFields(fields: List<List<Field>>, lockedFields: List<Pair<Int, Int>>): List<List<Field>> {
        if(lockedFields.isNotEmpty()) {
            val last = lockedFields.last()
            val first = last.first
            val second = last.second
            val newFields = fields.set(first, fields[first].set(second, Field.BLUE))
            syncFields(newFields, lockedFields.dropLast(1))
        }

        return fields
    }
}