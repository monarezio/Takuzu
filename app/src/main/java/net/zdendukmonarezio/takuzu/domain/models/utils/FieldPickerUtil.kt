package net.zdendukmonarezio.takuzu.domain.models.utils

import net.zdendukmonarezio.takuzu.domain.models.Field
import net.zdendukmonarezio.takuzu.domain.models.extensions.random

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
}