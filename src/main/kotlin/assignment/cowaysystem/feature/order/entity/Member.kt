package assignment.cowaysystem.feature.order.entity

import javax.persistence.*

@Entity
@Table(name = "Member")
class Member{
    @Id
    @GeneratedValue
    @Column(name = "member_id")
    var uid: Long = 0

    var username: String? = null

    @Column(unique = true)
    var email: String? = null

    var password: String? = null

    var address: String? = null
}