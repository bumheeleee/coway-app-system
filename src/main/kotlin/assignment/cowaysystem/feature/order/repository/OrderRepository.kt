package assignment.cowaysystem.feature.order.repository

import assignment.cowaysystem.feature.order.entity.Order
import org.springframework.data.jpa.repository.JpaRepository

interface OrderRepository: JpaRepository<Order, Long> {
}