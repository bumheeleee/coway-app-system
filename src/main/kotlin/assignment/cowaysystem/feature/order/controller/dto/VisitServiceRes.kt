package assignment.cowaysystem.feature.order.controller.dto

import assignment.cowaysystem.feature.order.entity.Visit

class VisitServiceRes(
        visit: Visit
){
    val city = visit.address?.city

    val street = visit.address?.street

    val zipcode = visit.address?.zipcode

    val visitTime = visit.visitTime

    val itemName = visit.orderItem?.item?.name

    val color = visit.orderItem?.color
}