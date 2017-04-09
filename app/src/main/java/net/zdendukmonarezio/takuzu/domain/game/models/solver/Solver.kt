package net.zdendukmonarezio.takuzu.domain.game.models.solver

import net.zdendukmonarezio.takuzu.domain.common.utils.ListUtil
import net.zdendukmonarezio.takuzu.domain.game.models.game.Board
import net.zdendukmonarezio.takuzu.domain.game.models.game.Field
import net.zdendukmonarezio.takuzu.domain.game.models.game.GameBoard

/**
 * Created by monarezio on 09/04/2017.
 */
class Solver(private val board: Board): Solvable {

    override fun isSolvable(): Boolean {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun solve(): Board? {
        fun helper(gameBoard: GameBoard): Board? {
            if(gameBoard.validateColorAmount() && gameBoard.validateAdjacency()) {
                val anonymousCoords = ListUtil.toPair(gameBoard.getFields())
                if (!anonymousCoords.isEmpty()) {
                    for (i in 0..anonymousCoords.size - 1) {
                        val blueBoard = gameBoard.set(anonymousCoords[i].first, anonymousCoords[i].second, Field.BLUE)
                        val blue = helper(blueBoard as GameBoard)
                        if (blue != null)
                            return blue

                        val redBoard = gameBoard.set(anonymousCoords[i].first, anonymousCoords[i].second, Field.RED)
                        val red = helper(redBoard as GameBoard)
                        if (red != null)
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