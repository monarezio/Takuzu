package net.zdendukmonarezio.takuzu.domain.game.models.hint

import net.zdendukmonarezio.takuzu.domain.common.utils.ListUtil
import net.zdendukmonarezio.takuzu.domain.game.models.game.Board
import net.zdendukmonarezio.takuzu.domain.game.models.game.Field
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

    override fun hintNext(): Hint {
        val row = hintWrongRow()

        if(row != null)
            return Hint(ListUtil.listOfPairsByFirst(board.columns(), row), FIELDS_DO_NOT_EQUAL_IN_ROW)

        val column = hintWrongColumn()
        if(column != null)
            return Hint(ListUtil.listOfPairsBySecond(board.rows(), column), FIELD_DO_NOT_EQUAL_IN_COLUMN)

        val rows = hintRows()
        if(rows != null)
            return Hint(
                    ListUtil.listOfPairsByFirst(board.rows(), rows.first) + ListUtil.listOfPairsByFirst(board.rows(), rows.second),
                    ROWS_EQUAL)

        val columns = hintColumns()
        if(columns != null)
            return Hint(
                    ListUtil.listOfPairsBySecond(board.rows(), columns.first) + ListUtil.listOfPairsBySecond(board.rows(), columns.second),
                    COLUMNS_EQUAL)

        return Hint(listOf(), NO_HINT_AVAILABLE)
    }

}