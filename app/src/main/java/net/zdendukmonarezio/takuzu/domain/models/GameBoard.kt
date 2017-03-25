package net.zdendukmonarezio.takuzu.domain.models

import net.zdendukmonarezio.takuzu.domain.models.utils.ListUtil

/**
 * Created by samuelkodytek on 06/03/2017.
 */
class GameBoard private constructor(fields: List<List<Field>>, lockedFields: List<Pair<Int, Int>>): Board {
    override fun rows(): Int = getFields().size

    override fun columns(): Int = getFields()[0].size

    private val fields: List<List<Field>> = fields
    private val lockedFields: List<Pair<Int, Int>> = lockedFields

    private constructor(rows: Int, colums: Int): this(
            List(rows) {List(colums) {Field.ANON}},
            ListUtil.randomPairs(4, rows, colums) //TODO: how many locked pairs to generate?
    )

    init {

    }

    /**
     * returns a new Gameboard with the edited fields, sets the field even if its locked
     */
    private fun forceSet(x: Int, y: Int, field: Field): Board {
        val editedFields = fields.mapIndexed { x, list ->
            list.mapIndexed { y, field ->  field} } //TODO create a valid setter

        return GameBoard(editedFields, lockedFields)
    }

    override fun getFields(): List<List<Field>> = fields

    override fun getLockedFields(): List<Pair<Int, Int>> = lockedFields

    override fun set(x: Int, y: Int, field: Field): Board {
        if(lockedFields.contains(Pair(x, y)))
            throw LockedFieldException("Position: [" + x + "; " + y + "], is locked")

        return forceSet(x, y, field)
    }

    companion object GameBoard {

        /**
         * returns a new board with generated lockedFields
         */
        fun createBlankBoard(rows: Int, colums: Int): Board = GameBoard(rows, colums)
    }
}