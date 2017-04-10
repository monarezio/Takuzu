package net.zdendukmonarezio.takuzu.domain.game.models.game

/**
 * Created by samuelkodytek on 06/03/2017.
 */
interface Board {

    /**
     * returns fields
     */
    fun getFields(): List<List<Field>>

    fun getField(i: Int): List<Field>

    /**
     * returns locked fields (unchangeable)
     */
    fun getLockedFields(): List<Pair<Int, Int>>

    /**
     * returns a new GameBoard with the edited fields, if possible
     */
    fun set(x: Int, y: Int, field: Field): Board

    /**
     * returns a percentage that is filled in
     */
    fun getProgress(): Int

    /**
     * returns true if there are no rows that are the same
     */
    fun validateRowEquivalency() : Boolean

    /**
     * returns true if there are no columns that are the same
     */
    fun validateColumnEquivalency() : Boolean

    /**
     * returns true if columns and rows have an equal number of each color
     */
    fun validateColorAmount(): Boolean

    /**
     * return true if there are less then two of the same field adjacent
     */
    fun validateAdjacency(): Boolean

    /**
     * returns true if there is an equal number of 1s (red) and 0s (blue) in each row and column
     */
    fun validateFieldAmount(): Boolean


    /**
     * return true if all validate functions return true
     */
    fun validateAll(): Boolean

    /**
     * returns amount rows
     * TODO: discuss the usefulness of this function
     */
    fun rows(): Int

    /**
     * returns amount columns
     * TODO: discuss the usefulness of this function
     */
    fun columns(): Int

    /**
     * returns true if the board does not contain Field.ANON
     */
    fun isFilledIn(): Boolean
}