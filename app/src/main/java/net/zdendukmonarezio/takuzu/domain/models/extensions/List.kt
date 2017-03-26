package net.zdendukmonarezio.takuzu.domain.models.extensions

/**
 * Created by samuelkodytek on 25/03/2017.
 */

fun<E> List<E>.set(index: Int, value: E): List<E> {
    return mapIndexed { i, e ->
        if(i == index) value
        else e
    }
}