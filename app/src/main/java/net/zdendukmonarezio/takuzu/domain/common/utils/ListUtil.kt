package net.zdendukmonarezio.takuzu.domain.common.utils

import net.zdendukmonarezio.takuzu.domain.game.models.game.Field
import net.zdendukmonarezio.takuzu.domain.common.extensions.random
import net.zdendukmonarezio.takuzu.domain.common.extensions.set

/**
 * Created by samuelkodytek on 07/03/2017.
 */
object ListUtil {
    /**
     * returns locked pairs according to the fields argument passed in
     * //TODO: optimizations, double foreach? not nice!
     */
    fun returnLockedPairs(fields: List<List<Field>>, lockedFields: List<Pair<Int, Int>> = listOf()): List<Pair<Int, Int>> {
        fields.forEachIndexed { i, list ->
            list.forEachIndexed { j, field ->
                if(field != Field.ANON)
                    return returnLockedPairs(
                            fields.set(i, fields[i].set(j, Field.ANON)),
                            lockedFields.plus(Pair(i, j))
                    )
            }
        }

        return lockedFields
    }

    /**
     * creates randomly generated field set
     */
    fun createNewFields(fields: List<List<Field>>, amount: Int): List<List<Field>> { //TODO: check validity
        val x = Int.random(0, fields.size - 1)
        val y = Int.random(0, fields[x].size - 1)

        if(amount != 0 && fields[x][y] == Field.ANON) {
            return createNewFields(
                    fields.set(x, fields[x].set(y, FieldPickerUtil.getField())),
                    amount - 1
            )
        } else if (amount != 0) {
            return createNewFields(fields, amount)
        }
        return fields
    }

    fun listOfPairsByFirst(size: Int, second: Int): List<Pair<Int, Int>> = 0.rangeTo(size - 1).mapIndexed { index, i -> Pair(i, second) }

    fun listOfPairsBySecond(size: Int, first: Int): List<Pair<Int, Int>> = 0.rangeTo(size - 1).mapIndexed { index, i -> Pair(first, i) }



    fun toPair(list: List<List<Field>>): List<Pair<Int, Int>> {
        /*return list.mapIndexed { x, list ->
            list.filter { i -> i == Field.ANON }
                    .mapIndexed { y, field -> Pair(x, y) }
        }.flatMap { i -> i }*/

        var newList = listOf<Pair<Int, Int>>()

        list.forEachIndexed { index, pair ->
            pair.forEachIndexed { jindex, field ->
                if(field == Field.ANON)
                    newList = newList + listOf(Pair(index, jindex))
            }
        }


        return newList
    }
}