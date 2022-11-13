package assignment.cowaysystem.feature.order.dto

import assignment.cowaysystem.feature.order.entity.item.*
import assignment.cowaysystem.feature.order.repository.CategoryRepository

class SaveItemReq(
        var name: String?,
        var price: Int,
        var category: String,
        var stockQuantity: Int,
        var dtype: String
) {
    fun toItem(categoryRepository: CategoryRepository): Item?{
        when (dtype) {
            "Bidet" -> {
                return Bidet().also {
                    it.name = name
                    it.price = price
                    it.category = categoryRepository.findByName(category)
                    it.stockQuantity = stockQuantity
                }
            }
            ("AirCleaner") -> {
                return AirCleaner().also {
                    it.name = name
                    it.price = price
                    it.category = categoryRepository.findByName(category)
                    it.stockQuantity = stockQuantity
                }
            }
            ("Mattress") -> {
                return Mattress().also {
                    it.name = name
                    it.price = price
                    it.category = categoryRepository.findByName(category)
                    it.stockQuantity = stockQuantity
                }
            }
            ("WaterPurifier") -> {
                return WaterPurifier().also {
                    it.name = name
                    it.price = price
                    it.category = categoryRepository.findByName(category)
                    it.stockQuantity = stockQuantity
                }
            }
            else -> {
                return null
            }
        }
    }
}