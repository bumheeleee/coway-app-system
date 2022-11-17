package assignment.cowaysystem.feature.order.controller.dto

import assignment.cowaysystem.feature.order.entity.Category
import assignment.cowaysystem.feature.order.entity.item.*
import assignment.cowaysystem.feature.order.repository.CategoryRepository
import org.hibernate.validator.constraints.Range
import javax.validation.constraints.NotBlank
import javax.validation.constraints.Pattern

class SaveItemReq(
        @field:NotBlank(message = "name 입력형식이 올바르지 않습니다.")
        var name: String? = null,

        @field:Range(min = 1, max = 10000000)
        var price: Int = 0,

        @field:NotBlank(message = "category 입력형식이 올바르지 않습니다.")
        var category: String? = null,

        @field:Range(min = 1, max = 100)
        var stockQuantity: Int = 0,

        @field:Pattern(regexp = "Bidet|AirCleaner|Mattress|WaterPurifier")
        var dtype: String? = null,

        @field:Pattern(regexp = "black|white|green|red|blue")
        var color: String? = null
) {
    fun toItem(category: Category): Item?{
        when (dtype) {
            "Bidet" -> {
                return Bidet().also {
                    it.name = name
                    it.price = price
                    it.category = category
                    it.stockQuantity = stockQuantity
                    it.color = color
                }
            }
            ("AirCleaner") -> {
                return AirCleaner().also {
                    it.name = name
                    it.price = price
                    it.category = category
                    it.stockQuantity = stockQuantity
                    it.color = color
                }
            }
            ("Mattress") -> {
                return Mattress().also {
                    it.name = name
                    it.price = price
                    it.category = category
                    it.stockQuantity = stockQuantity
                    it.color = color
                }
            }
            ("WaterPurifier") -> {
                return WaterPurifier().also {
                    it.name = name
                    it.price = price
                    it.category = category
                    it.stockQuantity = stockQuantity
                    it.color = color
                }
            }
            else -> {
                return null
            }
        }
    }
}