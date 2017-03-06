package net.zdendukmonarezio.takuzu.domain

import net.zdendukmonarezio.takuzu.domain.models.GameBoard
import net.zdendukmonarezio.takuzu.domain.models.Field

/**
 * Created by samuelkodytek on 06/03/2017.
 */
interface Takuzu {

    /**
     * performs when a move was made
     */
    fun onMoveMade(x: Int, y: Int): GameBoard //TODO: discuss

    /**
     * returns gameboard
     */
    fun getGameBoard(): GameBoard //TODO: discuss

    /**
     * checks if move is possible
     */
    fun isMovePossible(): Boolean

    /**
     * check if game is over
     */
    fun isGameOver(): Boolean
}