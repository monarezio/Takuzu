package net.zdendukmonarezio.takuzu.domain.game.models.game

import net.zdendukmonarezio.takuzu.domain.common.extensions.*
import net.zdendukmonarezio.takuzu.domain.common.utils.FieldPickerUtil
import net.zdendukmonarezio.takuzu.domain.common.utils.ListUtil
import net.zdendukmonarezio.takuzu.domain.game.models.solver.Solver

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

    override fun getProgress(): Int {
        val locked = lockedFields.size
        val sum = rows() * columns() - locked
        val colored = fields.map { i -> i.filter { j -> j != Field.ANON }.size }.sum() - locked

        return (colored.toDouble() / sum  * 100).toInt()
    }

    override fun getFields(): List<List<Field>> = fields

    override fun getField(i: Int): List<Field> = getFields()[i]

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

    override fun validateAdjacency(): Boolean {

        for(i in 0..rows() - 1) {
            for(j in 0..columns() - 3) {
                if ((fields[i][j] != Field.ANON || fields[i][j + 1] != Field.ANON || fields[i][j + 1] != Field.ANON) &&
                        fields[i][j] == fields[i][j + 1] && fields[i][j + 1] == fields[i][j + 2])
                    return false
            }
        }

        for(i in 0..rows() - 3) {
            for(j in 0..columns() - 1) {
                if ((fields[i][j] != Field.ANON || fields[i + 1][j] != Field.ANON || fields[i + 2][j] != Field.ANON) &&
                        fields[i][j] == fields[i + 1][j] && fields[i + 1][j] == fields[i + 2][j])
                    return false
            }
        }

        return true
    }

    override fun validateFieldAmount(): Boolean {
        return validateRowEquivalency() && validateColumnEquivalency()
    }

    private fun validateRowsColorAmount(): Boolean {
        for(i in 0..rows() - 1) {
            val row = getField(i)
            val size = row.filter { i -> i == Field.ANON }.size
            if(size != 0) {
                val blue = row.filter { i -> i == Field.BLUE }.size
                val red = row.filter { i -> i == Field.RED }.size
                if(blue > rows() / 2 || red > rows() / 2)
                    return false
            }
        }

        return true
    }

    private fun validateColumnsColorAmount(): Boolean {
        for (i in 0..columns() - 1) {
            val column = getFields().map { item -> item[i] }
            val size = column.filter { i -> i == Field.ANON }.size
            if (size != 0) {
                val blue = column.filter { i -> i == Field.BLUE }.size
                val red = column.filter { i -> i == Field.RED }.size
                if(blue > rows() / 2 || red > rows() / 2)
                    return false
            }

        }

        return true
    }

    /**
     * returns true if fields don't contain Field.ANON
     */
    override fun isFilledIn(): Boolean {
        return !fields.layeredAny { item -> item == Field.ANON }
    }

    override fun validateColorAmount(): Boolean {
        return validateColumnsColorAmount() && validateRowsColorAmount()
    }

    override fun validateAll(): Boolean {
        return isFilledIn()
                && validateAdjacency()
                && validateColumnEquivalency()
                && validateRowEquivalency()
                && validateFieldAmount()
                && validateColorAmount()
    }

    fun getNextAvailableMove(): Pair<Int, Int>? {
        for(i in 0..rows()-1) {
            for(j in 0..columns()-1) {
                if(fields[i][j] == Field.ANON)
                    return Pair(i, j)
            }
        }

        return null
    }

    companion object GameBoard {

        /**
         * returns a new board with generated lockedFields
         */
        fun createBlankBoard(rows: Int, columns: Int): Board {
            val gb = GameBoard(ListUtil.createNewFields(List(rows) {List(rows) { Field.ANON }}, rows * columns / 4))

            if(!(gb.validateColorAmount() && gb.validateAdjacency()
                    && gb.validateFieldAmount() && Solver(gb).isSolvable())) //Using the famous ostrich algorithm https://en.wikipedia.org/wiki/Ostrich_algorithm
                return createBlankBoard(rows, columns)

            return gb
        }


        fun createBoard(fields: List<List<Field>>, lockedFields: List<Pair<Int, Int>>): Board = GameBoard(fields, lockedFields)

        fun createBoard(board: Board): Board = GameBoard(board.getFields(), board.getLockedFields())
    }
}