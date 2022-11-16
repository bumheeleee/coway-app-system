package assignment.cowaysystem.feature.order.controller.dto

import assignment.cowaysystem.feature.order.entity.Category

class CategoryReq(
        val name: String
){
    fun toCategory(): Category{
        return Category().also {
            it.name = name
        }
    }
}