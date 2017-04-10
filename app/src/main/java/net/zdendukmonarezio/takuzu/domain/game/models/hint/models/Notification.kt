package net.zdendukmonarezio.takuzu.domain.game.models.hint.models

/**
 * Created by monarezio on 08/04/2017.
 */
enum class Notification {
    /**
     * Errors bellow
     */
    ROWS_EQUAL,
    COLUMNS_EQUAL,
    THREE_TILES, // TODO implement this rule
    FIELDS_DO_NOT_EQUAL_IN_ROW,
    FIELD_DO_NOT_EQUAL_IN_COLUMN,

    /**
     * Hints bellow
     */
    COLUMNS_HAS_EQUAL_NUMBER_OF_EACH_FIELD,
    ROWS_HAS_EQUAL_NUMBER_OF_EACH_FIELD,
    THREE_BLUE_TILES_HINT,
    THREE_RED_TILES_HINT,
    ONLY_ONE_POSSIBLE_COMBINATION,

    NO_HINT_AVAILABLE
}