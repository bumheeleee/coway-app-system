package assignment.cowaysystem.feature.order.entity

import java.time.LocalDateTime
import javax.persistence.*


@Entity
@Table(name = "DELIVERY")
class Delivery {
    @Id
    @GeneratedValue
    @Column(name = "delivery_id")
    var id: Long? = null

    @OneToOne(fetch = FetchType.LAZY, mappedBy = "delivery")
    var order: Order? = null

    @Embedded
    var address: Address? = null

    @Enumerated(EnumType.STRING)
    var status: DeliveryStatus? = null

    /**
     * 배달 시작시간 완료시간 기입 필수
     */
    var deliveryStDt: LocalDateTime? = null

    var deliveryEdDt: LocalDateTime? = null
}