package assignment.cowaysystem.feature.order.repository

import assignment.cowaysystem.feature.order.entity.Category
import assignment.cowaysystem.feature.order.entity.item.Item
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.query.Param

interface ItemRepository: JpaRepository<Item, Long> {
    fun findByName(name: String?): Item?

    @Query(
            """
                select i
                from Item i
                where (
                    i.name like :searchKeyword or i.category.name = :searchKeyword
                )
            """
    )
    fun search(
            pageable: Pageable,
            @Param("searchKeyword") searchKeyword: String? = null
    ): Page<Item>
}