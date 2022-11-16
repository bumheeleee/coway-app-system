package assignment.cowaysystem.feature.order.controller.dto

import assignment.cowaysystem.feature.order.entity.item.Item

class ItemRes(
        item: Item
){
    val name = item.name
    val price = item.price
}