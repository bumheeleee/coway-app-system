package assignment.cowaysystem.feature.order.repository

import assignment.cowaysystem.feature.order.entity.item.Item
import org.springframework.data.jpa.repository.JpaRepository

interface ItemRepository: JpaRepository<Item, Long> {
}