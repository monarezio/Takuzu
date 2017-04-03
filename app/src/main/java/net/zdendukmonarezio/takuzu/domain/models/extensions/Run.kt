package net.zdendukmonarezio.takuzu.domain.models.extensions

import net.zdendukmonarezio.takuzu.domain.models.game.Field
import net.zdendukmonarezio.takuzu.domain.models.utils.ListUtil

/**
 * Created by samuelkodytek on 26/03/2017.
 */

/**
 * TODO: This file will be after deleted, its for me to try if things are working since the tests are weird
 */
fun main(args: Array<String>) {
    val fields = listOf(
            listOf(Field.RED, Field.BLUE, Field.RED, Field.BLUE),
            listOf(Field.BLUE, Field.RED, Field.BLUE, Field.RED),
            listOf(Field.BLUE, Field.BLUE, Field.BLUE, Field.ANON),
            listOf(Field.RED, Field.RED, Field.BLUE, Field.ANON)
    )

    val fields2 = listOf(
            listOf(Field.RED, Field.ANON, Field.ANON, Field.ANON),
            listOf(Field.ANON, Field.ANON, Field.ANON, Field.ANON),
            listOf(Field.ANON, Field.ANON, Field.ANON, Field.ANON),
            listOf(Field.ANON, Field.ANON, Field.ANON, Field.ANON)
    )

    fun validate(col: List<List<Field>>): Boolean {
        for(i in 0..fields.size - 1) {
            for(j in 0..fields[i].size - 1) {
                if(col[i][j] == Field.ANON)
                    return false
            }
        }

        return true
    }


    val col = validate(fields)
    val col2 = validate(fields2)

    print("" + col + " " + col2)

}