package assignment.cowaysystem.feature.order.entity

import assignment.cowaysystem.feature.order.entity.item.Item
import java.time.LocalDateTime
import javax.persistence.*

@Entity
@Table(name = "VISIT")
class Visit {
    @Id
    @GeneratedValue
    @Column(name = "visit_id")
    var id: Long = 0

    @Embedded
    var address: Address? = null

    var visitTime: LocalDateTime? = null

    @OneToOne(fetch = FetchType.LAZY, cascade = [CascadeType.ALL])
    @JoinColumn(name = "order_item_id")
    var orderItem: OrderItem? = null

    /**
     * 연관관계 편의 메소드
     */
    fun addOrderItem(orderItem: OrderItem){
        this.orderItem = orderItem
        orderItem.visit = this
    }

    fun createVisit(
            address: Address,
            visitTime: LocalDateTime,
            orderItem: OrderItem
    ): Visit{
        val visit = Visit().also {
            it.address = address
            it.visitTime = visitTime
        }
        visit.addOrderItem(orderItem)
        return visit
    }
}