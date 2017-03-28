package net.zdendukmonarezio.takuzu.domain

import net.zdendukmonarezio.takuzu.domain.models.Board
import net.zdendukmonarezio.takuzu.domain.models.Field
import net.zdendukmonarezio.takuzu.domain.models.GameBoard
import net.zdendukmonarezio.takuzu.domain.models.extensions.set
import net.zdendukmonarezio.takuzu.domain.models.utils.FieldPickerUtil

/**
 * Created by samuelkodytek on 06/03/2017.
 */
class Game private constructor(val board: Board) : Takuzu{

    override fun onMoveMade(x: Int, y: Int): Takuzu {
        val fields = board.getFields()
        return createGame(
                GameBoard.createBoard(
                    fields.set(x, fields[x].set(y, FieldPickerUtil.nextField(fields[x][y]))),
                    board.getLockedFields()
                )
        )
    }

    override fun getGameBoard(): Board {
        return board
    }

    override fun isMovePossible(x: Int, y: Int): Boolean {
        return true
    }

    override fun isGameOver(): Boolean {
        return true
    }

    companion object Game {
        @JvmStatic fun createNew(rows: Int, colums: Int): Takuzu = Game(GameBoard.createBlankBoard(rows, colums))

        private fun createGame(board: Board): Takuzu = Game(board)
    }
}