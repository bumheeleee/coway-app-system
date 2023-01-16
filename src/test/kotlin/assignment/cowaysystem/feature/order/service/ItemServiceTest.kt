//package assignment.cowaysystem.feature.order.service
//
//import assignment.cowaysystem.feature.order.entity.Category
//import assignment.cowaysystem.feature.order.entity.item.AirCleaner
//import assignment.cowaysystem.feature.order.entity.item.Bidet
//import assignment.cowaysystem.feature.order.repository.CategoryRepository
//import assignment.cowaysystem.feature.order.repository.ItemRepository
//import org.junit.jupiter.api.Assertions
//import org.junit.jupiter.api.Assertions.*
//import org.junit.jupiter.api.Test
//import org.springframework.beans.factory.annotation.Autowired
//import org.springframework.boot.test.context.SpringBootTest
//import javax.persistence.EntityManager
//import javax.persistence.PersistenceContext
//
//@SpringBootTest
//internal class ItemServiceTest{
//    @PersistenceContext
//    lateinit var em: EntityManager
//
//    @Autowired
//    lateinit var itemRepository: ItemRepository
//
//    @Autowired
//    lateinit var itemService: ItemService
//
//    @Autowired
//    lateinit var categoryRepository: CategoryRepository
//
//    @Test
//    fun searchServiceTest() {
//        // given
//        var category1 = Category().also {
//            it.name = "비데"
//        }
//        var category2 = Category().also {
//            it.name = "매트리스"
//        }
//        var category3 = Category().also {
//            it.name = "정수기"
//        }
//        var category4 = Category().also {
//            it.name = "공기청정기"
//        }
//        categoryRepository.save(category1)
//        categoryRepository.save(category2)
//        categoryRepository.save(category3)
//        categoryRepository.save(category4)
//
//
//        var bidet = Bidet().also {
//            it.name = "a123"
//            it.category = category1
//        }
//        var bidet2 = Bidet().also {
//            it.name = "b123"
//            it.category = category1
//        }
//        var airCleaner1 = AirCleaner().also {
//            it.name = "air123"
//            it.category = category4
//        }
//        itemRepository.save(bidet)
//        itemRepository.save(bidet2)
//        itemRepository.save(airCleaner1)
//
//        // when
//        //val noItem = itemService.search(null)
//        val search = itemService.search("비데")
//
//        // then
//        assertEquals(search.totalElements, 2)
//        assertEquals(search.content[0].name, "b123")
//        assertEquals(search.content[1].name, "a123")
//    }
//
//}