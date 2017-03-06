package net.zdendukmonarezio.takuzu.domain.models

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
    fun getLockedFields(): List<Pair<Int, Int>> //TODO: save as coordinate (will extend Pair)


}