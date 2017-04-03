package net.zdendukmonarezio.takuzu.domain.models.utils

import net.zdendukmonarezio.takuzu.domain.models.game.Field
import net.zdendukmonarezio.takuzu.domain.models.extensions.random
import net.zdendukmonarezio.takuzu.domain.models.extensions.set

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
}