package assignment.cowaysystem.feature.order.controller.dto

import assignment.cowaysystem.feature.order.entity.OrderItem

class OrderItemRes(
        orderItem: OrderItem
){
    val orderItemId = orderItem.id
    val name = orderItem.item?.name
    val price = orderItem.item?.price
    val color = orderItem.item?.color
}