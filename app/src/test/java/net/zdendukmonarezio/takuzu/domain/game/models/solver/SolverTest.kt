package net.zdendukmonarezio.takuzu.domain.game.models.solver

import net.zdendukmonarezio.takuzu.domain.game.models.game.Field
import net.zdendukmonarezio.takuzu.domain.game.models.game.GameBoard
import org.junit.Test

import org.junit.Assert.*

/**
 * Created by monarezio on 09/04/2017.
 */
class SolverTest {
    @Test
    fun isSolvable() {

    }

    @Test
    fun solve() {
        val board = GameBoard.createBoard(listOf(
            listOf(Field.BLUE, Field.ANON, Field.BLUE, Field.ANON),
            listOf(Field.ANON, Field.RED, Field.ANON, Field.ANON),
            listOf(Field.ANON, Field.ANON, Field.ANON, Field.ANON),
            listOf(Field.ANON, Field.ANON, Field.ANON, Field.ANON)
        ), listOf(Pair(0, 0), Pair(0, 2), Pair(1, 1), Pair(2, 1)))
        val solver = Solver(board)
        assertEquals(null, solver.solve()?.getFields())
    }

}