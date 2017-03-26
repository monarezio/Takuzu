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
    val pairs = ListUtil.randomPairs(4, 16, 16)
    val list = List(16) {List(16) { Field.ANON}}

    val newList = ListUtil.syncFields(list, pairs)

    println(list)
    print(newList)
}