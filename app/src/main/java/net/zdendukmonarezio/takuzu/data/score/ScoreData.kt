package net.zdendukmonarezio.takuzu.data.score

import android.content.Context
import net.zdendukmonarezio.takuzu.R
import net.zdendukmonarezio.takuzu.domain.score.Score
import rx.Observable

/**
 * Created by samuelkodytek on 03/04/2017.
 */
class ScoreData(context: Context): Score {

    private val keyData = context.getString(R.string.saved_data)
    private val keyScore = context.getString(R.string.saved_high_score)
    private val defaultScore = context.getString(R.string.default_high_score).toInt()
    private val sharedPref = context.getSharedPreferences(
            keyData,
            Context.MODE_PRIVATE
    )

    override fun getScore(): Observable<Int> {
        return Observable.just(getRawScore())
    }

    override fun setScore(score: Int) {
        val editor = sharedPref.edit()
        editor.putInt(keyScore, score)
    }

    override fun addScore(score: Int) {
        val savedScore = getRawScore()
        setScore(savedScore + score)
    }

    private fun getRawScore() = sharedPref.getInt(keyScore, defaultScore)
}