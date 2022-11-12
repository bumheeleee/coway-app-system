package assignment.cowaysystem.feature.order.dto
import assignment.cowaysystem.feature.order.entity.item.Item

class SearchItemRes(
        val item: Item
){
    val name = item.name
    var price = item.price
    var stockQuantity = item.stockQuantity
    var color = item.color
    var category = item.category
}