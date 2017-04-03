package net.zdendukmonarezio.takuzu.domain.common.utils

import net.zdendukmonarezio.takuzu.domain.game.models.game.Field
import net.zdendukmonarezio.takuzu.domain.common.extensions.random

/**
 * Created by samuelkodytek on 26/03/2017.
 */
object FieldPickerUtil {

    /**
     * TODO: think of a algo to return a valid field on the position
     * returns a field
     */
    fun getField(): Field {
        if(Int.random(0, 2) == 1)
            return Field.BLUE
        else
            return Field.RED
    }

    /**
     * returns a new field according to the field given in the argument
     */
    fun nextField(field: Field): Field {
        if(field == Field.ANON) return Field.RED
        else if((field == Field.RED)) return Field.BLUE
        else return Field.ANON
    }
}