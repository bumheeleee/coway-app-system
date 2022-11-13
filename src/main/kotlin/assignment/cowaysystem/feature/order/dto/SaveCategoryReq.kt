package assignment.cowaysystem.feature.order.dto

import assignment.cowaysystem.feature.order.entity.Category

class SaveCategoryReq(
        val name: String
){
    fun toCategory(): Category{
        return Category().also {
            it.name = name
        }
    }
}