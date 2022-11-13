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

    var color: String? = null

    /**
     * orderItem 생성
     */
    fun createOrderItem(item: Item, orderPrice: Int, count: Int, color: String): OrderItem? {
        val orderItem = OrderItem().also {
            it.item = item
            it.orderPrice = orderPrice
            it.count = count
            it.color = color
        }
        item.removeStock(count)
        return orderItem
    }

    /**
     * 주문 취소
     */
    fun cancel() {
        this.item?.addStock(count)
    }

    /**
     * 주문상품 전체 가격 조회
     */
    fun getTotalPrice(): Int {
        return orderPrice * count
    }
}