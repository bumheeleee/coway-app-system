package assignment.cowaysystem.feature.order.controller.dto

import assignment.cowaysystem.feature.order.entity.Order

class OrderRes(
        order: Order
){
    val address = order.delivery?.address

    val deliveryStatus = order.delivery?.status

    val deliveryStDt = order.delivery?.deliveryStDt

    val deliveryEdDt = order.delivery?.deliveryEdDt

    val itemRes = order.orderItems.map {
        it.item?.let { it1 -> ItemRes(it1) }
    }
    val orderDate = order.orderDate
}