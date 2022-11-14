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

    fun findItemByCategory(name: String): List<Item>{
        val findCategory = categoryRepository.findByName(name)
                ?: throw NotFoundException("${name}은/는 존재하지 않는 카테고리입니다.")
        return findCategory.items
    }
}