package net.zdendukmonarezio.takuzu.domain.common.extensions

import java.util.concurrent.ThreadLocalRandom

/**
 * Created by samuelkodytek on 06/03/2017.
 */

/**
 * Returns a random number
 */
fun Int.Companion.random (lower: Int , upper: Int) = ThreadLocalRandom.current().nextInt(lower, upper + 1);
