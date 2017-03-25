package net.zdendukmonarezio.takuzu.domain.models

/**
 * Created by samuelkodytek on 06/03/2017.
 */
interface Board {

    /**
     * returns fields
     */
    fun getFields(): List<List<Field>>

    /**
     * returns locked fields (unchangeable)
     */
    fun getLockedFields(): List<Pair<Int, Int>>

    /**
     * returns a new GameBoard with the edited fields, if possible
     */
    fun set(x: Int, y: Int, field: Field): Board

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
}