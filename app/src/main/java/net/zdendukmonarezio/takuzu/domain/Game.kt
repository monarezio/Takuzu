package net.zdendukmonarezio.takuzu.domain

import net.zdendukmonarezio.takuzu.domain.models.Board
import net.zdendukmonarezio.takuzu.domain.models.GameBoard

/**
 * Created by samuelkodytek on 06/03/2017.
 */
class Game private constructor(val board: Board) : Takuzu{

    override fun onMoveMade(x: Int, y: Int): Takuzu {
        return Game.createNew(board.rows(), board.columns())
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

    companion object Game{
        fun createNew(rows: Int, colums: Int) = Game(GameBoard.createBlankBoard(rows, colums))
    }
}