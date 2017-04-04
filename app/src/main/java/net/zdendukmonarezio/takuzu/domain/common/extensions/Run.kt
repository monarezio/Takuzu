package net.zdendukmonarezio.takuzu.domain.common.extensions

import net.zdendukmonarezio.takuzu.domain.game.models.game.Field
import net.zdendukmonarezio.takuzu.domain.common.utils.ListUtil

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

    val f = fields.mapIndexed { index, list -> list[index] }

    print(f)

}