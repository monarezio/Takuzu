package net.zdendukmonarezio.takuzu.domain.common.extensions

import net.zdendukmonarezio.takuzu.domain.game.models.game.Field
import net.zdendukmonarezio.takuzu.domain.common.utils.ListUtil
import net.zdendukmonarezio.takuzu.domain.game.models.game.GameBoard

/**
 * Created by samuelkodytek on 26/03/2017.
 */

/**
 * TODO: This file will be after deleted, its for me to try if things are working since the tests are weird
 */
fun main(args: Array<String>) {
    val a = listOf(
            listOf(Field.BLUE, Field.ANON, Field.BLUE, Field.ANON),
            listOf(Field.ANON, Field.RED, Field.ANON, Field.ANON),
            listOf(Field.ANON, Field.RED, Field.ANON, Field.ANON),
            listOf(Field.ANON, Field.ANON, Field.ANON, Field.ANON)
    )

    println(ListUtil.toPair(a))
}