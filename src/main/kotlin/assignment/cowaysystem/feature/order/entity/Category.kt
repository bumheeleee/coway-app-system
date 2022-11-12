package assignment.cowaysystem.feature.order.entity

import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.Id
import javax.persistence.Table

@Entity
@Table(name = "CATEGORY")
class Category {
    @Id
    @GeneratedValue
    @Column(name = "category_id")
    var id: Long = 0

    var name: String? = null
}