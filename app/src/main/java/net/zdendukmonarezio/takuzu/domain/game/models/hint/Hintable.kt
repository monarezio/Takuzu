package net.zdendukmonarezio.takuzu.domain.game.models.hint

import net.zdendukmonarezio.takuzu.domain.game.models.hint.models.Hint

/**
 * Created by monarezio on 08/04/2017.
 */
interface Hintable {

    /**
     * returns the position of two rows which are incorrect (equal)
     */
    fun hintRows(): Pair<Int, Int>?

    /**
     * returns the position of two columns which are incorrect (equal)
     */
    fun hintColumns(): Pair<Int, Int>?

    /**
     * return the position of a wrong row (fields amount does not equal)
     */
    fun hintWrongRow(): Int?

    /**
     * return the position of a wrong column (fields amount does not equal)
     */
    fun hintWrongColumn(): Int?

    /**
     * returns the position of pairs that are adjacent
     */
    fun hintAdjacency(): List<Pair<Int, Int>>?

    /**
     * returns a hint by its color if its possible R;A;R or R;R;A or A;R;R
     */
    fun hintByColor(): Pair<Int, Int>?

    /**
     * returns a hint by the possible combinations
     */
    fun hintByCombination(): List<Pair<Int, Int>>?

    /**
     * hints the next best move
     * returns list of moves
     */
    fun hintNext(): Hint
}