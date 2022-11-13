package assignment.cowaysystem.feature.order.dto

class OrderReq(
        val userId: String,
        val itemName: String,
        val count: Int,
        val color: String
){
}