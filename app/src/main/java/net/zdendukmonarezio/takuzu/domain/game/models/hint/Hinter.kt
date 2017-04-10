package net.zdendukmonarezio.takuzu.domain.game.models.hint

import net.zdendukmonarezio.takuzu.domain.common.extensions.random
import net.zdendukmonarezio.takuzu.domain.common.utils.ListUtil
import net.zdendukmonarezio.takuzu.domain.game.models.game.Board
import net.zdendukmonarezio.takuzu.domain.game.models.game.Field
import net.zdendukmonarezio.takuzu.domain.game.models.game.GameBoard
import net.zdendukmonarezio.takuzu.domain.game.models.hint.models.Hint
import net.zdendukmonarezio.takuzu.domain.game.models.hint.models.Notification.*

/**
 * Created by monarezio on 08/04/2017.
 */

class Hinter(private val board: Board): Hintable{

    override fun hintRows(): Pair<Int, Int>? {
        for (i in 0..board.rows() - 1) {
            val first = board.getField(i)
            for (j in 0..board.rows() - 1) {
                val second = board.getField(j)
                if(i != j && first == second)
                    return Pair(i, j)
            }
        }

        return null //Return something of the board, but this method should not be called if everything is correct
    }

    override fun hintColumns(): Pair<Int, Int>? {
        for (i in 0..board.columns() - 1) {
            val first = board.getFields().map { item -> item[i] }
            for (j in 0..board.columns() - 1) {
                val second = board.getFields().map { item -> item[j] }
                if(i != j && first == second)
                    return Pair(i, j)
            }
        }

        return null //Return something of the board, but this method should not be called if everything is correct
    }

    override fun hintWrongRow(): Int? {
        for(i in 0..board.rows()-1) {
            val first = board.getField(i)
            if(first.filter { item -> item == Field.BLUE }.count() != first.filter { item -> item == Field.RED }.count())
                return i
        }

        return null
    }

    override fun hintWrongColumn(): Int? {
        for(i in 0..board.rows()-1) {
            val first = board.getFields().map { item -> item[i] }
            if(first.filter { item -> item == Field.BLUE }.count() != first.filter { item -> item == Field.RED }.count())
                return i
        }

        return null
    }

    override fun hintByColor(): Pair<Int, Int>? {
        fun checkRowValidity(i: Int, j: Int): Int? {
            val fields = board.getFields()

            fun case(field1: Field, field2: Field, field3: Field): Boolean = fields[i][j] == field1 && fields[i][j + 1] == field2 && fields[i][j + 2] == field3
            fun case1(field: Field): Boolean = case(Field.ANON, field, field)
            fun case2(field: Field): Boolean = case(field, field, Field.ANON)
            fun case3(field: Field): Boolean = case(field, Field.ANON, field)

            if(case1(Field.RED) || case1(Field.BLUE))
                return j + 0
            else if (case2(Field.RED) || case2(Field.BLUE))
                return j + 2
            else if (case3(Field.RED) || case3(Field.BLUE))
                return j + 1
            else
                return null
        }

        fun checkColumnValidity(i: Int, j: Int): Int? {
            val fields = board.getFields()

            fun case(field1: Field, field2: Field, field3: Field): Boolean = fields[i][j] == field1 && fields[i + 1][j] == field2 && fields[i + 2][j] == field3
            fun case1(field: Field): Boolean = case(Field.ANON, field, field)
            fun case2(field: Field): Boolean = case(field, field, Field.ANON)
            fun case3(field: Field): Boolean = case(field, Field.ANON, field)

            if(case1(Field.RED) || case1(Field.BLUE))
                return i + 0
            else if (case2(Field.RED) || case2(Field.BLUE))
                return i + 2
            else if (case3(Field.RED) || case3(Field.BLUE))
                return i + 1
            else
                return null
        }

        for (i in 0..board.rows() - 1) {
            for(j in 0..board.columns()-3) {
                val row = checkRowValidity(i, j)
                if (row != null) return Pair(i, row)
            }
        }

        for(i in 0..board.rows() - 3) {
            for(j in 0..board.columns() - 1) {
                val col = checkColumnValidity(i, j)
                if (col != null) return Pair(col, j)
            }
        }
        return null
    }

    override fun hintByCombination(): List<Pair<Int, Int>>? {

        for(i in 0..board.rows() - 1) {
            val row = board.getField(i)
            val size = row.filter { i -> i == Field.ANON }.size
            val amountOfBlue = row.filter { i -> i == Field.BLUE }.size
            val amountOfRed = row.filter { i -> i == Field.RED }.size
            if((amountOfBlue >= board.columns() / 2 || amountOfRed >= board.columns() / 2) &&
                    size != 0 && board.rows() / 2 >= size)
                return ListUtil.listOfPairsBySecond(board.rows(), i)
        }

        for (i in 0..board.columns() - 1) {
            val column = board.getFields().map { item -> item[i] }
            val size = column.filter { i -> i == Field.ANON }.size
            val amountOfBlue = column.filter { i -> i == Field.BLUE }.size
            val amountOfRed = column.filter { i -> i == Field.RED }.size
            if ((amountOfBlue >= board.columns() / 2 || amountOfRed >= board.columns() / 2) &&
                    size != 0 && board.columns() / 2 >= size)
                return ListUtil.listOfPairsByFirst(board.columns(), i)
        }

        return null
    }

    override fun hintAdjacency(): List<Pair<Int, Int>>? {

        val fields = board.getFields()

        for(i in 0..board.rows() - 1) {
            for(j in 0..board.columns() - 3) {
                if ((fields[i][j] != Field.ANON || fields[i][j + 1] != Field.ANON || fields[i][j + 1] != Field.ANON) &&
                        fields[i][j] == fields[i][j + 1] && fields[i][j + 1] == fields[i][j + 2])
                    return listOf(Pair(i, j), Pair(i, j + 1), Pair(i, j + 2))
            }
        }

        for(i in 0..board.rows() - 3) {
            for(j in 0..board.columns() - 1) {
                if ((fields[i][j] != Field.ANON || fields[i + 1][j] != Field.ANON || fields[i + 2][j] != Field.ANON) &&
                        fields[i][j] == fields[i + 1][j] && fields[i + 1][j] == fields[i + 2][j])
                    return listOf(Pair(i, j), Pair(i + 1, j), Pair(i + 2, j))
            }
        }

        return null
    }

    override fun hintNext(): Hint {

        if(!board.isFilledIn()) {
            val hintColor = hintByColor()
            if (hintColor != null)
                return Hint(listOf(hintColor), THREE_TILES_HINT)

            val hintCombo = hintByCombination()
            if (hintCombo != null)
                return Hint(hintCombo, ONLY_ONE_POSSIBLE_COMBINATION)
        } else {
            val adjacency = hintAdjacency()
            if(adjacency != null)
                return Hint(adjacency, THREE_TILES)

            val row = hintWrongRow()
            if (row != null)
                return Hint(ListUtil.listOfPairsBySecond(board.columns(), row), FIELDS_DO_NOT_EQUAL_IN_ROW)

            val column = hintWrongColumn()
            if (column != null)
                return Hint(ListUtil.listOfPairsByFirst(board.rows(), column), FIELD_DO_NOT_EQUAL_IN_COLUMN)

            val rows = hintRows()
            if (rows != null)
                return Hint(
                        ListUtil.listOfPairsBySecond(board.rows(), rows.first) + ListUtil.listOfPairsBySecond(board.rows(), rows.second),
                        ROWS_EQUAL)

            val columns = hintColumns()
            if (columns != null)
                return Hint(
                        ListUtil.listOfPairsByFirst(board.rows(), columns.first) + ListUtil.listOfPairsByFirst(board.rows(), columns.second),
                        COLUMNS_EQUAL)
        }

        return Hint(listOf(), NO_HINT_AVAILABLE)
    }

    fun hintOnly(): Hint {
        val hintColor = hintByColor()
        if (hintColor != null)
            return Hint(listOf(hintColor), THREE_TILES_HINT)

        val hintCombo = hintByCombination()
        if (hintCombo != null)
            return Hint(hintCombo, ONLY_ONE_POSSIBLE_COMBINATION)

        if(board is GameBoard) {
            val list = board.getNextAvailableMove()
            if(list != null)
                return Hint(listOf(list), NO_HINT_AVAILABLE)
        }

        return Hint(listOf(), NO_HINT_AVAILABLE)
    }
}