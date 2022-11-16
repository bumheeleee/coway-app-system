package assignment.cowaysystem.feature.order.controller.dto

import assignment.cowaysystem.feature.order.entity.Address
import java.time.LocalDateTime

class VisitServiceReq(
        val orderItemId: Long,
        val address: Address,
        val visitTime: LocalDateTime,
) {
}