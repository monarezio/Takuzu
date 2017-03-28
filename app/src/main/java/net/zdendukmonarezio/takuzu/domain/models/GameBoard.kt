package net.zdendukmonarezio.takuzu.domain.models

import net.zdendukmonarezio.takuzu.domain.models.extensions.set
import net.zdendukmonarezio.takuzu.domain.models.utils.FieldPickerUtil
import net.zdendukmonarezio.takuzu.domain.models.utils.ListUtil

/**
 * Created by samuelkodytek on 06/03/2017.
 */
class GameBoard private constructor(fields: List<List<Field>>, lockedFields: List<Pair<Int, Int>>): Board {

    private val fields: List<List<Field>> = fields
    private val lockedFields: List<Pair<Int, Int>> = lockedFields

    private constructor(fields: List<List<Field>>): this(
            fields,
            ListUtil.returnLockedPairs(fields)
    )

    /**
     * returns a new Gameboard with the edited fields, sets the field even if its locked
     */
    override fun set(x: Int, y: Int, field: Field): Board {
        val editedFields = fields.set(x, fields[x].set(y, field))

        return createBoard(editedFields, lockedFields)
    }

    override fun getFields(): List<List<Field>> = fields

    override fun getLockedFields(): List<Pair<Int, Int>> = lockedFields

    override fun rows(): Int = getFields().size

    override fun columns(): Int = getFields()[0].size

    override fun validateRowEquivalency(): Boolean {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun validateColumnEquivalency(): Boolean {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun validateAdjacency(): Boolean {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun validateFieldAmount(): Boolean {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun validateAll(): Boolean {
        return validateAdjacency() && validateColumnEquivalency() && validateRowEquivalency() && validateFieldAmount()
    }

    companion object GameBoard {

        /**
         * returns a new board with generated lockedFields
         */
        fun createBlankBoard(rows: Int, columns: Int): Board = GameBoard(
                ListUtil.createNewFields(List(rows) {List(rows) {Field.ANON}}, rows * columns / 4)
            )

        fun createBoard(fields: List<List<Field>>, lockedFields: List<Pair<Int, Int>>): Board = GameBoard(fields, lockedFields)
    }
}