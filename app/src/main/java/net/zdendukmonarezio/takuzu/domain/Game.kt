package net.zdendukmonarezio.takuzu.domain

import net.zdendukmonarezio.takuzu.domain.models.Board
import net.zdendukmonarezio.takuzu.domain.models.Field
import net.zdendukmonarezio.takuzu.domain.models.GameBoard
import net.zdendukmonarezio.takuzu.domain.models.extensions.set

/**
 * Created by samuelkodytek on 06/03/2017.
 */
class Game private constructor(val board: Board) : Takuzu{

    private fun nextField(field: Field): Field {
        if(field == Field.ANON) return Field.RED
        else if((field == Field.RED)) return Field.BLUE
        else return Field.ANON
    }

    override fun onMoveMade(x: Int, y: Int): Takuzu {
        val fields = board.getFields()
        return Game(
                GameBoard.createBoard(
                    fields.set(x, fields[x].set(y, nextField(fields[x][y]))),
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
    }
}