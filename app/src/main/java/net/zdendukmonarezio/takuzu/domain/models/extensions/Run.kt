package net.zdendukmonarezio.takuzu.domain.models.extensions

import net.zdendukmonarezio.takuzu.domain.models.Field
import net.zdendukmonarezio.takuzu.domain.models.utils.ListUtil

/**
 * Created by samuelkodytek on 26/03/2017.
 */

/**
 * TODO: This file will be after deleted, its for me to try if things are working since the tests are weird
 */
fun main(args: Array<String>) {
    val fields = listOf(
            listOf(Field.RED, Field.ANON, Field.ANON, Field.ANON),
            listOf(Field.RED, Field.ANON, Field.ANON, Field.ANON),
            listOf(Field.ANON, Field.ANON, Field.ANON, Field.ANON),
            listOf(Field.ANON, Field.ANON, Field.ANON, Field.ANON)
    )

    val col = fields.map { j -> j[0] }

    print(col)

}