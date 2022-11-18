package assignment.cowaysystem.feature.order.service

import assignment.cowaysystem.common.exception.BadRequestException
import assignment.cowaysystem.common.exception.NotFoundException
import assignment.cowaysystem.feature.order.entity.Category
import assignment.cowaysystem.feature.order.entity.item.Item
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

    fun findAll(): List<Category>{
        val categories = categoryRepository.findAll()
        if (categories.isEmpty()){
            throw NotFoundException("카테고리 항목이 비어있습니다.")
        }
        return categories
    }
}