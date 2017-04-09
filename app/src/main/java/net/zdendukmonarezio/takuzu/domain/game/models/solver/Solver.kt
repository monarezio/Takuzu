package net.zdendukmonarezio.takuzu.domain.game.models.solver

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

    override fun solve(): Board {
        fun helper(board: Board): Board {
            val anonymousCoords = board.getFields().mapIndexed { index, list ->
                Pair(index, list.filter { i -> i == Field.ANON }
                        .mapIndexed { index, field -> index }
                )
            }

            anonymousCoords.forEach { i ->
                //helper(GameBoard.createBoard(board.set(i.first, i.second, Field.BLUE)))
            }

            return board
        }

        return helper(board)
    }

    override fun getBoard(): Board = board

}