package assignment.cowaysystem.feature.order.repository

import assignment.cowaysystem.common.exception.NotFoundException
import assignment.cowaysystem.feature.order.dto.MemberFormDto
import assignment.cowaysystem.feature.order.dto.OrderReq
import assignment.cowaysystem.feature.order.entity.Address
import assignment.cowaysystem.feature.order.entity.Category
import assignment.cowaysystem.feature.order.entity.OrderStatus
import assignment.cowaysystem.feature.order.entity.item.AirCleaner
import assignment.cowaysystem.feature.order.entity.item.Bidet
import assignment.cowaysystem.feature.order.service.ItemService
import assignment.cowaysystem.feature.order.service.MemberService
import assignment.cowaysystem.feature.order.service.OrderService
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.test.annotation.Rollback
import org.springframework.transaction.annotation.Transactional


@SpringBootTest
@Transactional(readOnly = true)
internal class OrderRepositoryTest {
    @Autowired
    lateinit var orderService: OrderService
    @Autowired
    lateinit var itemRepository: ItemRepository
    @Autowired
    lateinit var itemService: ItemService
    @Autowired
    lateinit var categoryRepository: CategoryRepository
    @Autowired
    lateinit var memberService: MemberService
    @Autowired
    lateinit var passwordEncoder: PasswordEncoder
    @Autowired
    lateinit var orderRepository: OrderRepository


    @Test
    @Transactional
    @Rollback(false)
    fun findOrderByLoginIdTest() {
        // given
        /**
         * 카테고리
         */
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

        /**
         * 상품
         */
        var bidet = Bidet().also {
            it.name = "a123"
            it.category = category1
            it.price = 10000
            it.stockQuantity = 100
            it.color = "black"
        }
        var bidet2 = Bidet().also {
            it.name = "b123"
            it.category = category1
            it.price = 20000
            it.stockQuantity = 200
            it.color = "white"
        }
        var airCleaner1 = AirCleaner().also {
            it.name = "air123"
            it.category = category4
            it.price = 30000
            it.stockQuantity = 300
            it.color = "green"
        }
        itemRepository.save(bidet)
        itemRepository.save(bidet2)
        itemRepository.save(airCleaner1)

        /**
         * 회원
         */
        val address = Address("incheon", "senhak", "12341")
        val memberFormDto = MemberFormDto("lee123","lee", "email", "password", address)
        val savedMember = memberService.save(memberFormDto.toEntity(passwordEncoder))

        // when
        val loginId = "lee123"
        val listOrderReq = mutableListOf<OrderReq>()
        val listOrderReq2 = mutableListOf<OrderReq>()

        val orderReq1 = OrderReq("a123", 10, "black")
        val orderReq2 = OrderReq("b123", 10, "white")
        val orderReq5 = OrderReq("b123", 10, "white")
        listOrderReq.add(orderReq1)
        listOrderReq.add(orderReq2)
        listOrderReq.add(orderReq5)

        val orderReq3 = OrderReq("b123", 10, "white")
        val orderReq4 = OrderReq("air123", 10, "green")
        listOrderReq2.add(orderReq3)
        listOrderReq2.add(orderReq4)

        val order = orderService.order(loginId, listOrderReq)
        val order2 = orderService.order(loginId, listOrderReq2)

        order.status = OrderStatus.ORDER
        order2.status = OrderStatus.CANCEL

        // when
        val findOrderByLoginId = orderRepository.findOrderByLoginId(loginId,OrderStatus.ORDER)?: throw NotFoundException("not found")

        // then
        assertEquals(findOrderByLoginId.size, 1)
        assertEquals(findOrderByLoginId[0].orderItems.size, 3)

        findOrderByLoginId.forEach {
            it.orderItems.forEach {
                println(it.item?.name)
            }
        }


    }
}