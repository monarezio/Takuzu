package net.zdendukmonarezio.takuzu.domain.models

import org.junit.Test

import org.junit.Assert.*

/**
 * Created by samuelkodytek on 07/03/2017.
 */
class GameBoardTest {
    @Test
    fun set() {
        val gameBoard = GameBoard.createBlankBoard(16, 16)
        val lockedFields = gameBoard.getLockedFields()

        val pair = Pair(10, 10)
        if(lockedFields.contains(pair)) {
            val newGameBoard = gameBoard.set(10, 10, Field.BLUE)
            assert(newGameBoard.getFields()[10][11] == Field.BLUE)
        }
    }

}