package assignment.cowaysystem.feature.order.controller.dto
import assignment.cowaysystem.feature.order.entity.item.Item

class SearchItemRes(
        item: Item
){
    val name = item.name
    var price = item.price
    var stockQuantity = item.stockQuantity
    var color = item.color
    var category = item.category
}