//package assignment.cowaysystem.feature.order.service
//
//import assignment.cowaysystem.feature.order.controller.dto.MemberFormDto
//import assignment.cowaysystem.feature.order.controller.dto.OrderReq
//import assignment.cowaysystem.feature.order.entity.Address
//import assignment.cowaysystem.feature.order.entity.Category
//import assignment.cowaysystem.feature.order.entity.item.AirCleaner
//import assignment.cowaysystem.feature.order.entity.item.Bidet
//import assignment.cowaysystem.feature.order.repository.CategoryRepository
//import assignment.cowaysystem.feature.order.repository.ItemRepository
//import org.junit.jupiter.api.Assertions.*
//import org.junit.jupiter.api.Test
//import org.springframework.beans.factory.annotation.Autowired
//import org.springframework.boot.test.context.SpringBootTest
//import org.springframework.security.crypto.password.PasswordEncoder
//import org.springframework.test.annotation.Rollback
//import org.springframework.transaction.annotation.Transactional
//import kotlin.concurrent.thread
//
//@SpringBootTest
//internal class OrderServiceTest{
//
//    @Autowired
//    lateinit var orderService: OrderService
//    @Autowired
//    lateinit var itemRepository: ItemRepository
//    @Autowired
//    lateinit var itemService: ItemService
//    @Autowired
//    lateinit var categoryRepository: CategoryRepository
//    @Autowired
//    lateinit var memberService: MemberService
//    @Autowired
//    lateinit var passwordEncoder: PasswordEncoder
//
//
//    @Test
//    @Transactional
//    @Rollback(false)
//    fun orderTest() {
//        // given
//        /**
//         * 카테고리
//         */
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
//        /**
//         * 상품
//         */
//        var bidet = Bidet().also {
//            it.name = "a123"
//            it.category = category1
//            it.price = 10000
//            it.stockQuantity = 100
//            it.color = "black"
//        }
//        var bidet2 = Bidet().also {
//            it.name = "b123"
//            it.category = category1
//            it.price = 20000
//            it.stockQuantity = 200
//            it.color = "white"
//        }
//        var airCleaner1 = AirCleaner().also {
//            it.name = "air123"
//            it.category = category4
//            it.price = 30000
//            it.stockQuantity = 300
//            it.color = "green"
//        }
//        itemRepository.save(bidet)
//        itemRepository.save(bidet2)
//        itemRepository.save(airCleaner1)
//
//        /**
//         * 회원
//         */
//        val memberFormDto = MemberFormDto("lee123","lee", "email", "password", "incheon",
//                "senhak", "12341")
//        val savedMember = memberService.save(memberFormDto.toEntity(passwordEncoder))
//
//        // when
//        val loginId = "lee123"
//        val listOrderReq = mutableListOf<OrderReq>()
//
//        val orderReq1 = OrderReq("a123", 10, "black")
//        val orderReq2 = OrderReq("b123", 10, "white")
//        listOrderReq.add(orderReq1)
//        listOrderReq.add(orderReq2)
//
//        val order = orderService.order(loginId, listOrderReq)
//
//        // then
//        assertEquals(order.member?.loginId, "lee123")
//        order.orderItems.forEach {
//            println("itemName : ${it.item?.name}")
//            println("itemColor : ${it.item?.color}")
//            println("itemPrice : ${it.item?.price}")
//            println("===========================")
//        }
//
//        assertEquals(order.delivery?.address, savedMember.address)
//    }
//
//    @Test
//    fun findVisitServiceUserItemTest() {
//        // given
//        /**
//         * 카테고리
//         */
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
//        /**
//         * 상품
//         */
//        var bidet = Bidet().also {
//            it.name = "a123"
//            it.category = category1
//            it.price = 10000
//            it.stockQuantity = 100
//            it.color = "black"
//        }
//        var bidet2 = Bidet().also {
//            it.name = "b123"
//            it.category = category1
//            it.price = 20000
//            it.stockQuantity = 200
//            it.color = "white"
//        }
//        var airCleaner1 = AirCleaner().also {
//            it.name = "air123"
//            it.category = category4
//            it.price = 30000
//            it.stockQuantity = 300
//            it.color = "green"
//        }
//        itemRepository.save(bidet)
//        itemRepository.save(bidet2)
//        itemRepository.save(airCleaner1)
//
//        /**
//         * 회원
//         */
//        val memberFormDto = MemberFormDto("lee123","lee", "email", "password", "incheon",
//                "senhak", "12341")
//        val savedMember = memberService.save(memberFormDto.toEntity(passwordEncoder))
//
//        // when
//        val loginId = "lee123"
//        val listOrderReq = mutableListOf<OrderReq>()
//
//        val orderReq1 = OrderReq("a123", 10, "black")
//        val orderReq2 = OrderReq("b123", 10, "white")
//        listOrderReq.add(orderReq1)
//        listOrderReq.add(orderReq2)
//
//        val order = orderService.order(loginId, listOrderReq)
//
//
//        // then
//    }
//}