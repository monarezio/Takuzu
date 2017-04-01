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
        for(i in 0..rows()-1) {
            val first = fields[i]
            for(j in 0..rows()-1) {
                if(i != j && first == fields[j] && (first.contains(Field.BLUE) || first.contains(Field.RED)))
                    return false
            }
        }

        return true
    }

    override fun validateColumnEquivalency(): Boolean {
        for(i in 0..columns()-1) {
            val first = fields.map { k -> k[i] } //Tranforming into column
            for (j in 0..columns()-1) {
                val second = fields.map { k -> k[j] }
                if(i != j && first == second && (first.contains(Field.BLUE) || first.contains(Field.RED)))
                    return false
            }
        }

        return true
    }

    private fun validateRowAdjacency(): Boolean {
        for(i in 0..rows()-1) {
            for(j in 0..rows()-3) {
                if(fields[i][j] != Field.ANON && (fields[i][j] == fields[i][j+1] && fields[i][j+1] == fields[i][j+2] ))
                    return false
            }
        }

        return true
    }

    private fun validateColumnAdjacency(): Boolean {
        for(i in 0..columns()-1) {
            val col = fields.map { k -> k[i] } //Tranforming into column
            for(j in 0..columns()-3) {
                if(col[j] != Field.ANON && (col[j] == col[j+1] && col[j+1] == col[j+2] ))
                    return false
            }
        }

        return true
    }

    override fun validateAdjacency(): Boolean {
        return validateColumnAdjacency() && validateRowAdjacency()
    }

    override fun validateFieldAmount(): Boolean {
        return validateRowEquivalency() && validateColumnEquivalency()
    }

    private fun validateRowsColorAmount(): Boolean {
        for(i in 0..rows() - 1) {
            if(fields[i].filter { i -> i != Field.BLUE }.size != fields[i].filter { i -> i != Field.RED }.size)
                return false
        }

        return true
    }

    private fun validateColumnsColorAmount(): Boolean {
        for(i in 0..columns() - 1) {
            val col = fields.map { list -> list[i] }
            if(col.filter { i -> i != Field.BLUE }.size != col.filter { i -> i != Field.RED }.size)
                return false
        }

        return true
    }

    override fun validateColorAmount(): Boolean {
        return validateColumnsColorAmount() && validateRowAdjacency()
    }

    override fun validateAll(): Boolean {
        return validateAdjacency()
                && validateColumnEquivalency()
                && validateRowEquivalency()
                && validateFieldAmount()
                && validateColorAmount()
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