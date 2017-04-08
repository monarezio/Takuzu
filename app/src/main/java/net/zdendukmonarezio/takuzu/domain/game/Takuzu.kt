package net.zdendukmonarezio.takuzu.domain.game

import net.zdendukmonarezio.takuzu.domain.game.models.game.Board
import net.zdendukmonarezio.takuzu.domain.game.models.game.Field
import net.zdendukmonarezio.takuzu.domain.game.models.game.GameBoard
import net.zdendukmonarezio.takuzu.domain.game.models.hint.models.Hint

/**
 * Created by samuelkodytek on 06/03/2017.
 */
interface Takuzu {

    /**
     * performs when a move was made
     */
    fun onMoveMade(x: Int, y: Int): Takuzu //TODO: discuss

    /**
     * returns gameboard
     */
    fun getGameBoard(): Board //TODO: discuss

    /**
     * checks if move is possible
     */
    fun isMovePossible(x: Int, y: Int): Boolean

    /**
     * check if game is over
     */
    fun isGameOver(): Boolean

    /**
     * returns the coordinates of incorrect fields
     */
    fun getWrongFields(): Hint

    /**
     * checks if board is fully filled - doesn't contain any ANON fields
     * and returns corresponding value
     */
    fun isBoardFilled(): Boolean
}