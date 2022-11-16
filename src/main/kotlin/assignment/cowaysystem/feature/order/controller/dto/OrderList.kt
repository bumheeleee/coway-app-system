package assignment.cowaysystem.feature.order.controller.dto

import assignment.cowaysystem.feature.order.entity.item.Item

class OrderList(
        val item: Item,
        val count: Int,
        val color: String,
        val serviceYn: String? = null
){
}