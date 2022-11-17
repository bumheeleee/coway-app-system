package assignment.cowaysystem.feature.order.controller.dto

import assignment.cowaysystem.feature.order.entity.item.Item

/**
 * dto 아님 -> itemName -> item으로 변환해서 만든 주문 리스트
 */
class OrderList(
        val item: Item,
        val count: Int = 0,
        val color: String? = null,
        val serviceYn: String = "N"
){
}