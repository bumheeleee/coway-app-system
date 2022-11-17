package assignment.cowaysystem.feature.order.controller.dto

import assignment.cowaysystem.feature.order.entity.Category
import javax.validation.constraints.NotBlank
import javax.validation.constraints.NotNull

class CategoryReq(
        @field:NotNull(message = "카테고리 이름은 NULL값이 들어올 수 없습니다.")
        @field:NotBlank(message = "카테고리 이름은 공백값이 들어올 수 없습니다.")
        val name: String? = null
){
    fun toCategory(): Category{
        return Category().also {
            it.name = name
        }
    }
}