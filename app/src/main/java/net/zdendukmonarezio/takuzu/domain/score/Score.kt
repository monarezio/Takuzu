package net.zdendukmonarezio.takuzu.domain.score

import rx.Observable

/**
 * Created by samuelkodytek on 03/04/2017.
 */
interface Score {

    /**
     * returns a observable containing the the score (Int)
     */
    fun getScore(): Observable<Int>

    /**
     * sets the score
     */
    fun setScore(score: Int)

    /**
     * adds to the score
     */
    fun addScore(score: Int)
}