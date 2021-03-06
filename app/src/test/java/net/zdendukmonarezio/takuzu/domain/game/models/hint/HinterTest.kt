package net.zdendukmonarezio.takuzu.domain.game.models.hint

import junit.framework.Assert.assertEquals
import net.zdendukmonarezio.takuzu.domain.game.models.game.Field
import net.zdendukmonarezio.takuzu.domain.game.models.game.GameBoard
import org.junit.Assert
import org.junit.Test

/**
 * Created by monarezio on 08/04/2017.
 */
class HinterTest {

    @Test
    fun hintRows() {
        val hint = Hinter(GameBoard.createBoard(listOf(
                listOf(Field.BLUE, Field.BLUE, Field.RED, Field.RED),
                listOf(Field.BLUE, Field.BLUE, Field.RED, Field.ANON),
                listOf(Field.BLUE, Field.BLUE, Field.RED, Field.RED),
                listOf(Field.BLUE, Field.BLUE, Field.ANON, Field.ANON)
        ), listOf()))
        Assert.assertEquals(Pair(0, 2), hint.hintRows())
    }

    @Test
    fun hintColumns() {
        val hint = Hinter(GameBoard.createBoard(listOf(
                listOf(Field.BLUE, Field.BLUE, Field.RED, Field.RED),
                listOf(Field.BLUE, Field.BLUE, Field.RED, Field.ANON),
                listOf(Field.BLUE, Field.BLUE, Field.RED, Field.RED),
                listOf(Field.BLUE, Field.BLUE, Field.BLUE, Field.ANON)
        ), listOf()))
        Assert.assertEquals(Pair(0, 1), hint.hintColumns())
        val hint2 = Hinter(GameBoard.createBoard(listOf(
                listOf(Field.BLUE, Field.BLUE, Field.BLUE, Field.RED),
                listOf(Field.BLUE, Field.BLUE, Field.BLUE, Field.ANON),
                listOf(Field.BLUE, Field.BLUE, Field.BLUE, Field.RED),
                listOf(Field.BLUE, Field.RED, Field.BLUE, Field.ANON)
        ), listOf()))
        Assert.assertEquals(Pair(0, 2), hint2.hintColumns())
    }

    @Test
    fun hintWrongRow() {
        val hint = Hinter(GameBoard.createBoard(listOf(
                listOf(Field.BLUE, Field.BLUE, Field.RED, Field.RED),
                listOf(Field.BLUE, Field.BLUE, Field.RED, Field.ANON),
                listOf(Field.BLUE, Field.BLUE, Field.RED, Field.RED),
                listOf(Field.BLUE, Field.BLUE, Field.BLUE, Field.ANON)
        ), listOf()))
        Assert.assertEquals(1, hint.hintWrongRow())
    }

    @Test
    fun hintWrongColumn() {
        val hint = Hinter(GameBoard.createBoard(listOf(
                listOf(Field.BLUE, Field.BLUE, Field.RED, Field.RED),
                listOf(Field.BLUE, Field.BLUE, Field.RED, Field.ANON),
                listOf(Field.BLUE, Field.BLUE, Field.RED, Field.RED),
                listOf(Field.BLUE, Field.BLUE, Field.BLUE, Field.ANON)
        ), listOf()))
        Assert.assertEquals(0, hint.hintWrongColumn())
        val hint2 = Hinter(GameBoard.createBoard(listOf(
                listOf(Field.BLUE, Field.RED, Field.RED, Field.RED),
                listOf(Field.BLUE, Field.BLUE, Field.RED, Field.ANON),
                listOf(Field.RED, Field.RED, Field.RED, Field.RED),
                listOf(Field.RED, Field.BLUE, Field.BLUE, Field.ANON)
        ), listOf()))
        Assert.assertEquals(2, hint2.hintWrongColumn())
        val hint3 = Hinter(GameBoard.createBoard(listOf(
                listOf(Field.RED, Field.BLUE, Field.RED, Field.ANON),
                listOf(Field.BLUE, Field.RED, Field.RED, Field.ANON),
                listOf(Field.BLUE, Field.RED, Field.BLUE, Field.ANON),
                listOf(Field.RED, Field.BLUE, Field.BLUE, Field.RED)
        ), listOf()))
        Assert.assertEquals(3, hint3.hintWrongColumn())
    }

    @Test
    fun hintNext() {
        /*val hint = Hinter(GameBoard.createBoard(listOf(
                listOf(Field.RED, Field.BLUE, Field.RED, Field.ANON),
                listOf(Field.BLUE, Field.RED, Field.RED, Field.ANON),
                listOf(Field.BLUE, Field.RED, Field.BLUE, Field.ANON),
                listOf(Field.RED, Field.BLUE, Field.BLUE, Field.RED)
        ), listOf()))
        Assert.assertEquals(3, hint.hintNext())

        val hint2 = Hinter(GameBoard.createBoard(listOf(
                listOf(Field.RED, Field.BLUE, Field.ANON, Field.BLUE),
                listOf(Field.RED, Field.ANON, Field.BLUE, Field.ANON),
                listOf(Field.BLUE, Field.ANON, Field.ANON, Field.ANON),
                listOf(Field.BLUE, Field.ANON, Field.ANON, Field.ANON)
        ), listOf()))
        Assert.assertEquals(3, hint2.hintNext())*/
    }

    @Test
    fun hintByColor() {
        val hint = Hinter(GameBoard.createBoard(listOf(
                listOf(Field.RED, Field.ANON, Field.RED, Field.ANON),
                listOf(Field.BLUE, Field.RED, Field.RED, Field.BLUE),
                listOf(Field.BLUE, Field.RED, Field.BLUE, Field.RED),
                listOf(Field.RED, Field.BLUE, Field.BLUE, Field.RED)
        ), listOf()))
        assertEquals(Pair(0, 1), hint.hintByColor())
        val hint2 = Hinter(GameBoard.createBoard(listOf(
                listOf(Field.BLUE, Field.RED, Field.RED, Field.BLUE),
                listOf(Field.BLUE, Field.RED, Field.RED, Field.RED),
                listOf(Field.RED, Field.RED, Field.BLUE, Field.RED),
                listOf(Field.RED, Field.RED, Field.BLUE, Field.ANON)
        ), listOf()))
        assertEquals(Pair(3, 3), hint2.hintByColor())
        val hint3 = Hinter(GameBoard.createBoard(listOf(
                listOf(Field.ANON, Field.ANON, Field.ANON, Field.ANON),
                listOf(Field.ANON, Field.ANON, Field.ANON, Field.ANON),
                listOf(Field.ANON, Field.ANON, Field.RED, Field.RED),
                listOf(Field.ANON, Field.ANON, Field.ANON, Field.ANON)
        ), listOf()))
        assertEquals(Pair(2, 1), hint3.hintByColor())
    }

    @Test
    fun hintAdjacency() {
        val hint3 = Hinter(GameBoard.createBoard(listOf(
                listOf(Field.ANON, Field.ANON, Field.ANON, Field.ANON),
                listOf(Field.ANON, Field.ANON, Field.ANON, Field.ANON),
                listOf(Field.ANON, Field.RED, Field.RED, Field.RED),
                listOf(Field.ANON, Field.ANON, Field.ANON, Field.ANON)
        ), listOf()))
        assertEquals(listOf(Pair(2, 1), Pair(2, 2), Pair(2, 3)), hint3.hintAdjacency())
    }

}