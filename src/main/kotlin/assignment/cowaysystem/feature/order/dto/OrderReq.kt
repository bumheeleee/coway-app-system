package assignment.cowaysystem.feature.order.dto

/**
 * 주문을 하기 위한 req dto
 */
class OrderReq(
        val itemName: String,
        val count: Int,
        val color: String
){
}