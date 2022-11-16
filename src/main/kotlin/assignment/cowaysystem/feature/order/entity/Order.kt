package assignment.cowaysystem.feature.order.entity

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
    var orderItems: MutableList<OrderItem> = mutableListOf()

    var orderDate: LocalDateTime? = null

    @Enumerated(EnumType.STRING)
    var status: OrderStatus? = null

    /**
     * 연관관계 편의 매소드
     */
    fun addMember(member: Member){
        this.member = member
        member.orders.add(this)
    }

    fun addDelivery(delivery: Delivery){
        this.delivery = delivery
        delivery.order = this
    }

    fun addOrderItems(orderItems: List<OrderItem>){
        this.orderItems = orderItems.toMutableList()
        orderItems.forEach {
            it.order = this
        }
    }

    /**
     * 주문 기능
     * 기존에 오류가 있는 코드
     * 연관관계 주인이 있는 곳에 객체를 붙여주면 문제가 발생하지 않지만(fk에 값이 들어가기 때문에)
     * 하지만 order와 orderItem처럼 (연관관계 주인이 아닌곳에 객체를 붙여주면), 연관관계 주인의 fk에는 값이 없어 나중에 테이블에서 join을 못함....
     * 따라서 연관관계의 양방향 값을 사용할거면 양방향 연관관계 매핑을 해야됨
     */
//    fun createOrder(
//            member: Member,
//            delivery: Delivery,
//            orderItems: List<OrderItem>
//    ): Order{
//        return Order().also {
//            it.member = member
//            it.delivery = delivery
//            it.orderItems = orderItems
//            it.status = OrderStatus.ORDER
//            it.orderDate = LocalDateTime.now()
//        }
//    }

    /**
     * 주문 기능
     */
    fun createOrder(
            member: Member,
            delivery: Delivery,
            orderItems: List<OrderItem>
    ): Order{
        val order = Order().also {
            it.status = OrderStatus.ORDER
            it.orderDate = LocalDateTime.now()
        }
        order.addMember(member)
        order.addDelivery(delivery)
        order.addOrderItems(orderItems)
        return order
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