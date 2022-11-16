package assignment.cowaysystem.feature.order.repository

import assignment.cowaysystem.feature.order.entity.OrderItem
import org.springframework.data.jpa.repository.JpaRepository

interface OrderItemRepository: JpaRepository<OrderItem, Long> {
}