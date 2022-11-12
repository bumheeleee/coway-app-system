package assignment.cowaysystem.common.exception

import java.lang.RuntimeException

open class NotEnoughStockException(reason: String?) : RuntimeException(reason)