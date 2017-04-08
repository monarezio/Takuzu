package net.zdendukmonarezio.takuzu.domain.score

import android.content.Context
import net.zdendukmonarezio.takuzu.data.score.ScoreData
import rx.Observable

/**
 * Created by monarezio on 08/04/2017.
 */
private class ScoreManager(private val score: Score) {

    /**
     * return a observable with the score
     */
    fun getScore(): Observable<Int> = this.score.getScore()

    /**
     * set the score
     */
    fun setScore(score: Int) = this.score.setScore(score)

    /**
     * adds the score
     */
    fun addScore(score: Int) = this.score.addScore(score)

    companion object {
        @JvmStatic fun createScoreManager(context: Context) = ScoreManager(ScoreData(context))
    }
}