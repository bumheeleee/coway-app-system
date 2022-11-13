package assignment.cowaysystem.feature.order.service

import assignment.cowaysystem.feature.order.entity.Category
import assignment.cowaysystem.feature.order.repository.CategoryRepository
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
@Transactional(readOnly = true)
class CategoryService(
        private val categoryRepository: CategoryRepository
){
    @Transactional
    fun saveCategory(category: Category): Category{
        return categoryRepository.save(category)
    }
}