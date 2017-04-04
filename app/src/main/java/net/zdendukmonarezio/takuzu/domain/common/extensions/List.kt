package net.zdendukmonarezio.takuzu.domain.common.extensions

/**
 * Created by samuelkodytek on 25/03/2017.
 */

fun<E> List<E>.set(index: Int, value: E): List<E> {
    return mapIndexed { i, e ->
        if(i == index) value
        else e
    }
}

fun<E> List<List<E>>.layeredAnyIndexed(f: (i: Int, j: Int, item: E) -> Boolean): Boolean {
    for(i in 0..size - 1) {
        for(j in 0..this[i].size - 1) {
            if(f(i, j, this[i][j]))
                return true
        }
    }

    return false
}

fun<E> List<List<E>>.layeredAny(f: (item: E) -> Boolean): Boolean {
    for(i in 0..size - 1) {
        for(j in 0..this[i].size - 1) {
            if(f(this[i][j]))
                return true
        }
    }

    return false
}

fun<E> List<List<E>>.layeredInvertedAnyIndexed(f: (i: Int, j: Int, item: E) -> Boolean): Boolean {
    for(i in 0..size - 1) {
        val inverse = this.map { item -> item[i] }
        for(j in 0..inverse.size - 1) {
            if(f(i, j, inverse[j]))
                return true
        }
    }

    return false
}

fun<E> List<List<E>>.layeredInvertedAny(f: (item: E) -> Boolean): Boolean {
    for(i in 0..size - 1) {
        val inverse = this.map { item -> item[i] }
        for(j in 0..inverse.size - 1) {
            if(f(inverse[j]))
                return true
        }
    }

    return false
}