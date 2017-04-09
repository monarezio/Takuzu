package net.zdendukmonarezio.takuzu.domain.game.models.solver

import net.zdendukmonarezio.takuzu.domain.game.models.game.Board

/**
 * Created by monarezio on 09/04/2017.
 */
interface Solvable {

    /**
     * returns true if the board is sovable
     */
    fun isSolvable(): Boolean

    /**
     * returns the solved board
     */
    fun solve(): Board?

    /**
     * returns the board
     */
    fun getBoard(): Board
}