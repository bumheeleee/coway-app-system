package assignment.cowaysystem.feature.order.service

import assignment.cowaysystem.common.dto.PageableDto
import assignment.cowaysystem.common.exception.BadRequestException
import assignment.cowaysystem.feature.order.entity.item.Item
import assignment.cowaysystem.feature.order.repository.ItemRepository
import org.springframework.data.domain.Page
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
@Transactional(readOnly = true)
class ItemService(
        private val itemRepository: ItemRepository
){
    @Transactional
    fun saveItem(item: Item): Item{
        return itemRepository.save(item)
    }

    fun findItem(id: Long): Item{
        val findItem = itemRepository.findById(id)
        if (findItem.isPresent){
            return findItem.get()
        }else{
            throw BadRequestException("${id}에 해당하는 Item 이 존재하지 않습니다.")
        }
    }

    fun findItem(name: String): Item{
        return itemRepository.findByName(name)?: throw BadRequestException("해당 ${name}을 찾을 수 없습니다.")
    }

    fun search(
            searchKeyword: String?
    ): Page<Item>{
        val pageable = PageableDto("name")
        val searchItem = itemRepository.search(pageable.toPageable(), searchKeyword)
        if (searchItem.isEmpty){
            throw BadRequestException("해당 ${searchKeyword}을 찾을 수 없습니다.")
        }else{
            return searchItem
        }
    }
}