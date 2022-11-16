package assignment.cowaysystem.feature.order.repository

import assignment.cowaysystem.feature.order.entity.Order
import assignment.cowaysystem.feature.order.entity.OrderStatus
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.query.Param

interface OrderRepository: JpaRepository<Order, Long> {
    @Query("""
        select o
        from Order o join o.member m
        where (o.status = :orderStatus)
        and (m.loginId = :loginId)
    """)
    fun findOrderByLoginId(
            @Param("loginId") loginId: String?,
            @Param("orderStatus") orderStatus: OrderStatus
    ): List<Order>
}