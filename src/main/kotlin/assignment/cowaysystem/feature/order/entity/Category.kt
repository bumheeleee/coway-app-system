package assignment.cowaysystem.feature.order.entity

import assignment.cowaysystem.feature.order.entity.item.Item
import com.fasterxml.jackson.annotation.JsonIgnore
import javax.persistence.*

@Entity
@Table(name = "CATEGORY")
class Category {
    @Id
    @GeneratedValue
    @Column(name = "category_id")
    var id: Long = 0

    var name: String? = null

    @JsonIgnore
    @OneToMany(mappedBy = "category", cascade = [CascadeType.ALL])
    val items: List<Item> = mutableListOf()
}