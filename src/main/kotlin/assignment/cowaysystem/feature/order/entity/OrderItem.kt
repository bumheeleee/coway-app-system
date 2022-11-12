package assignment.cowaysystem.feature.order.entity

import assignment.cowaysystem.feature.order.entity.item.Item
import javax.persistence.*

@Entity
@Table(name = "ORDERITEM")
class OrderItem {
    @Id
    @GeneratedValue
    @Column(name = "order_item_id")
    var id: Long? = null

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "item_id")
    var item: Item? = null

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "order_id")
    var order: Order? = null

    var orderPrice: Int = 0

    var count: Int = 0
}