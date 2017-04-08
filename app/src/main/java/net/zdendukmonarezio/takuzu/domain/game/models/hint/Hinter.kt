package net.zdendukmonarezio.takuzu.domain.game.models.hint

import net.zdendukmonarezio.takuzu.domain.game.models.game.Board
import net.zdendukmonarezio.takuzu.domain.game.models.hint.models.Hint

/**
 * Created by monarezio on 08/04/2017.
 */

class Hinter(private val board: Board): Hintable{

    override fun hintRows(): Pair<Int, Int>? {
        for (i in 0..board.rows()) {
            val first = board.getField(i)
            for (j in 0..board.rows()) {
                val second = board.getField(j)
                if(first == second) return Pair(i, j)
            }
        }

        return null //Return something of the board, but this method should not be called if everything is correct
    }

    override fun hintColumns(): Pair<Int, Int> {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun hintWrongRow(): Int {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun hintWrongColumn(): Int {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun hintNext(): Hint {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

}