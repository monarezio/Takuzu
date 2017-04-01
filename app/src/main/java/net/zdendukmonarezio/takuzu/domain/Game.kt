package net.zdendukmonarezio.takuzu.domain

import net.zdendukmonarezio.takuzu.domain.models.Board
import net.zdendukmonarezio.takuzu.domain.models.GameBoard
import net.zdendukmonarezio.takuzu.domain.models.IllegalMoveException
import net.zdendukmonarezio.takuzu.domain.models.extensions.set
import net.zdendukmonarezio.takuzu.domain.models.utils.FieldPickerUtil

/**
 * Created by samuelkodytek on 06/03/2017.
 */
class Game private constructor(val board: Board) : Takuzu {

    override fun onMoveMade(x: Int, y: Int): Takuzu {
        if(!isMovePossible(x, y))
            throw IllegalMoveException("Illegal move at position [" + x + ";" + y + "]")

        return createGame(board.set(x, y, FieldPickerUtil.nextField(board.getFields()[x][y])))
    }

    override fun getGameBoard(): Board {
        return board
    }

    override fun isMovePossible(x: Int, y: Int): Boolean {
        return !board.getLockedFields().contains(Pair(x, y))
    }

    override fun isGameOver(): Boolean {
        return false
    }

    override fun getWrongFields(): List<Pair<Int, Int>> {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    companion object Game {
        @JvmStatic fun createNew(rows: Int, colums: Int): Takuzu = Game(GameBoard.createBlankBoard(rows, colums))

        private fun createGame(board: Board): Takuzu = Game(board)
    }
}