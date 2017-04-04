package net.zdendukmonarezio.takuzu.data.score

import net.zdendukmonarezio.takuzu.domain.score.Score
import rx.Observable

/**
 * Created by samuelkodytek on 03/04/2017.
 */
class ScoreData: Score {
    override fun getScore(): Observable<Int> {
        return Observable.just(0)
    }

    override fun setScore(score: Int) {

    }

    override fun addScore(score: Int) {

    }
}