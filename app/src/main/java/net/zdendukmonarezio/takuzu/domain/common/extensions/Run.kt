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
            listOf(Field.ANON, Field.BLUE, Field.BLUE, Field.BLUE),
            listOf(Field.ANON, Field.BLUE, Field.BLUE, Field.BLUE),
            listOf(Field.ANON, Field.BLUE, Field.BLUE, Field.BLUE),
            listOf(Field.ANON, Field.BLUE, Field.BLUE, Field.BLUE))

    println(ListUtil.toPair(a))
}