package net.zdendukmonarezio.takuzu.domain.models

import net.zdendukmonarezio.takuzu.domain.Game
import net.zdendukmonarezio.takuzu.domain.models.Field

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
}