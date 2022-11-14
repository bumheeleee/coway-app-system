package assignment.cowaysystem.feature.order.dto

class OrderReq(
        val memberId: String,
        val itemName: String,
        val count: Int,
        val color: String
){
}