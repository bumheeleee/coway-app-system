package assignment.cowaysystem.feature.order.controller.dto

import assignment.cowaysystem.feature.order.entity.Visit

class VisitServiceRes(
        visit: Visit
){
    val address = visit.address

    val visitTime = visit.visitTime

    val itemName = visit.orderItem?.item?.name

    val color = visit.orderItem?.color
}