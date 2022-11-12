package assignment.cowaysystem.feature.order.entity

import javax.persistence.*

@Entity
@Table(name = "AUTHORITY")
class Authority {
    @Id
    @GeneratedValue
    @Column(name = "authority_id")
    var id: Long = 0

    var name: String? = null
}