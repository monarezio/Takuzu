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
            assertTrue(newGameBoard.getFields()[10][10] == Field.BLUE)
        }
    }

    @Test
    fun validateRowEquivalency() {
        val gameBoard = GameBoard.createBoard(listOf(
                listOf(Field.BLUE, Field.ANON, Field.ANON, Field.ANON),
                listOf(Field.BLUE, Field.ANON, Field.ANON, Field.ANON),
                listOf(Field.ANON, Field.ANON, Field.ANON, Field.ANON),
                listOf(Field.ANON, Field.ANON, Field.ANON, Field.ANON)
        ), listOf())

        assertFalse(gameBoard.validateRowEquivalency())
        val newGameBoard = gameBoard.set(1, 1, Field.BLUE)
        assertTrue(newGameBoard.validateRowEquivalency())

    }

    @Test
    fun validateColumnEquivalency() {
        val gameBoard = GameBoard.createBoard(listOf(
                listOf(Field.BLUE, Field.ANON, Field.ANON, Field.ANON),
                listOf(Field.ANON, Field.ANON, Field.ANON, Field.ANON),
                listOf(Field.ANON, Field.ANON, Field.ANON, Field.ANON),
                listOf(Field.ANON, Field.ANON, Field.ANON, Field.ANON)
        ), listOf())

        assertTrue(gameBoard.validateColumnEquivalency())
        val newGameBoard = gameBoard.set(0, 1, Field.BLUE)
        assertFalse(newGameBoard.validateColumnEquivalency())
    }

    @Test
    fun validateAdjacency() {
        val gameBoard = GameBoard.createBoard(listOf(
                listOf(Field.BLUE, Field.BLUE, Field.ANON, Field.ANON),
                listOf(Field.ANON, Field.ANON, Field.ANON, Field.ANON),
                listOf(Field.ANON, Field.ANON, Field.ANON, Field.ANON),
                listOf(Field.ANON, Field.ANON, Field.ANON, Field.ANON)
        ), listOf())
        assertTrue(gameBoard.validateAdjacency())
        val newGameBoard = gameBoard.set(0, 2, Field.BLUE)
        assertFalse(newGameBoard.validateAdjacency())
        val newGameBoard2 = gameBoard.set(1, 0, Field.BLUE).set(2, 0, Field.BLUE)
        assertFalse(newGameBoard2.validateAdjacency())
    }

    @Test
    fun validateFieldAmount() {
        val gameBoard = GameBoard.createBoard(listOf(
                listOf(Field.BLUE, Field.BLUE, Field.RED, Field.RED),
                listOf(Field.RED, Field.RED, Field.BLUE, Field.BLUE),
                listOf(Field.BLUE, Field.RED, Field.RED, Field.BLUE),
                listOf(Field.RED, Field.BLUE, Field.BLUE, Field.RED)
        ), listOf())
        assertTrue(gameBoard.validateAll())
        val newGameBoard = gameBoard.set(0, 0, Field.RED)
        assertFalse(newGameBoard.validateAll())
    }
}