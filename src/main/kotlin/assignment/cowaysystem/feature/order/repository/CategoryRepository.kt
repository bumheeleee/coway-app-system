package assignment.cowaysystem.feature.order.repository

import assignment.cowaysystem.feature.order.entity.Category
import org.springframework.data.jpa.repository.JpaRepository

interface CategoryRepository: JpaRepository<Category, Long> {
    fun findByName(name: String?): Category?
}