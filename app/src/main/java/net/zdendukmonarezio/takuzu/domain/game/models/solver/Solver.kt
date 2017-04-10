package net.zdendukmonarezio.takuzu.domain.game.models.solver

import net.zdendukmonarezio.takuzu.domain.common.utils.ListUtil
import net.zdendukmonarezio.takuzu.domain.game.models.game.Board
import net.zdendukmonarezio.takuzu.domain.game.models.game.Field
import net.zdendukmonarezio.takuzu.domain.game.models.game.GameBoard
import net.zdendukmonarezio.takuzu.domain.game.models.hint.Hinter

/**
 * Created by monarezio on 09/04/2017.
 */
class Solver(private val board: Board): Solvable {

    override fun isSolvable(): Boolean {
        println("Solving")
        val a = solve()
        return a != null
    }

    override fun solve(): Board? {
        fun helper(gameBoard: Board): Board? {
            if(gameBoard.validateColorAmount() && gameBoard.validateAdjacency()) {
                val next = Hinter(gameBoard).hintOnly().coords
                        .filter { i -> gameBoard.getFields()[i.first][i.second] == Field.ANON }
                if (!next.isEmpty()) {
                    for (i in 0..next.size - 1) {
                        val blue = helper(gameBoard.set(next[i].first, next[i].second, Field.BLUE))
                        if(blue != null)
                            return blue

                        val red = helper(gameBoard.set(next[i].first, next[i].second, Field.RED))
                        if(red != null)
                            return red
                    }
                } else if (gameBoard.validateAll()) {
                    return gameBoard
                }
            }

            return null
        }

        return helper(board as GameBoard)
    }

    override fun getBoard(): Board = board

}