package assignment.cowaysystem.feature.order.repository

import assignment.cowaysystem.feature.order.entity.Category
import assignment.cowaysystem.feature.order.entity.item.AirCleaner
import assignment.cowaysystem.feature.order.entity.item.Bidet
import assignment.cowaysystem.feature.order.service.ItemService
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.data.domain.PageRequest
import org.springframework.data.domain.Sort
import org.springframework.test.annotation.Rollback
import org.springframework.transaction.annotation.Transactional
import javax.persistence.EntityManager
import javax.persistence.PersistenceContext

@SpringBootTest
internal class ItemRepositoryTest {
    @PersistenceContext
    lateinit var em: EntityManager

    @Autowired
    lateinit var itemRepository: ItemRepository

    @Autowired
    lateinit var itemService: ItemService

    @Autowired
    lateinit var categoryRepository: CategoryRepository

    @Test
    @Transactional
    fun searchRepositoryTest() {
        // given
        var category1 = Category().also {
            it.name = "비데"
        }
        var category2 = Category().also {
            it.name = "매트리스"
        }
        var category3 = Category().also {
            it.name = "정수기"
        }
        var category4 = Category().also {
            it.name = "공기청정기"
        }
        categoryRepository.save(category1)
        categoryRepository.save(category2)
        categoryRepository.save(category3)
        categoryRepository.save(category4)


        var bidet = Bidet().also {
            it.name = "a123"
            it.category = category1
        }
        var bidet2 = Bidet().also {
            it.name = "b123"
            it.category = category1
        }
        var airCleaner1 = AirCleaner().also {
            it.name = "air123"
            it.category = category4
        }
        itemRepository.save(bidet)
        itemRepository.save(bidet2)
        itemRepository.save(airCleaner1)

        val pageRequest = PageRequest.of(0, 3, Sort.by(Sort.Direction.DESC, "name"))

        // when
        val bidetA = itemRepository.search(pageRequest,"a123")
        val bidetB = itemRepository.search(pageRequest,"b123")
        val bidets = itemRepository.search(pageRequest,"비데")
        val noItem = itemRepository.search(pageRequest, null)

        // then
        assertEquals(bidetA.totalElements, 1)
        assertEquals(bidetB.totalElements, 1)
        assertEquals(bidets.totalElements, 2)

        assertEquals(bidetA.content[0].name, "a123")
        assertEquals(bidetB.content[0].name, "b123")
        assertEquals(noItem.content.size, 0)
        println("=============")
        println(noItem.content)
        println("=============")
        noItem.forEach {
            println(it)
        }

        bidets.forEach {
            println(it.name)
        }
    }
    @Test
    fun findByNameTest() {
        // given
        var category1 = Category().also {
            it.name = "비데"
        }
        var category2 = Category().also {
            it.name = "매트리스"
        }
        var category3 = Category().also {
            it.name = "정수기"
        }
        var category4 = Category().also {
            it.name = "공기청정기"
        }
        categoryRepository.save(category1)
        categoryRepository.save(category2)
        categoryRepository.save(category3)
        categoryRepository.save(category4)


        var bidet = Bidet().also {
            it.name = "a123"
            it.category = category1
        }
        var bidet2 = Bidet().also {
            it.name = "b123"
            it.category = category1
        }
        var airCleaner1 = AirCleaner().also {
            it.name = "air123"
            it.category = category4
        }
        itemRepository.save(bidet)
        itemRepository.save(bidet2)
        itemRepository.save(airCleaner1)

        // when
        val findItem = itemService.findItem("a123")

        // then
        assertEquals(findItem.name, "a123")
    }
}