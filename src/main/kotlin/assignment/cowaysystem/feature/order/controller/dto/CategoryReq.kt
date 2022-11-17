package assignment.cowaysystem.feature.order.controller.dto

import assignment.cowaysystem.feature.order.entity.Category
import javax.validation.constraints.NotBlank

class CategoryReq(
        @field:NotBlank(message = "name 입력형식이 올바르지 않습니다.")
        val name: String? = null
){
    fun toCategory(): Category{
        return Category().also {
            it.name = name
        }
    }
}