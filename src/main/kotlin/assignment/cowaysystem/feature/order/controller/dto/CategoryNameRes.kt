package assignment.cowaysystem.feature.order.controller.dto

import assignment.cowaysystem.feature.order.entity.Category

class CategoryNameRes(
        category: Category
){
    val categoryName = category.name
}