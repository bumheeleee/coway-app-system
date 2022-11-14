package assignment.cowaysystem.feature.order.entity

import java.lang.IllegalStateException
import java.time.LocalDateTime
import javax.persistence.*

@Entity
@Table(name = "ORDERS")
class Order {
    @Id
    @GeneratedValue
    @Column(name = "order_id")
    var id: Long? = null

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    var member: Member? = null

    @OneToOne(fetch = FetchType.LAZY, cascade = [CascadeType.ALL])
    @JoinColumn(name = "delivery_id")
    var delivery: Delivery? = null

    @OneToMany(mappedBy = "order", cascade = [CascadeType.ALL])
    var orderItems: List<OrderItem> = mutableListOf()

    var orderDate: LocalDateTime? = null

    @Enumerated(EnumType.STRING)
    var status: OrderStatus? = null

    /**
     * 주문 기능
     */
    fun createOrder(
            member: Member,
            delivery: Delivery,
            orderItems: List<OrderItem>
    ): Order{
        return Order().also {
            it.member = member
            it.delivery = delivery
            it.orderItems = orderItems
            it.status = OrderStatus.ORDER
            it.orderDate = LocalDateTime.now()
        }
    }

    /**
     * 주문 취소
     */
    fun cancel(){
        if (this.delivery?.status == DeliveryStatus.COMP) throw IllegalStateException("이미 배송이 완료된 상태입니다.")
        this.status = OrderStatus.CANCEL
        if (this.orderItems.isNotEmpty()){
            orderItems.forEach {
                it.cancel()
            }
        }
    }

    /**
     * 주문 전체 주문 가격 조회
     */
    fun getTotalPrice(): Int{
        var totalPrice: Int = 0
        this.orderItems.forEach {
            totalPrice += it.getTotalPrice()
        }
        return  totalPrice
    }

}