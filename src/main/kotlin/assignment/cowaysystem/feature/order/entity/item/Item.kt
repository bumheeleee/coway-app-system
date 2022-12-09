package assignment.cowaysystem.feature.order.entity.item

import assignment.cowaysystem.common.annotation.AllOpen
import assignment.cowaysystem.common.exception.NotEnoughStockException
import assignment.cowaysystem.feature.order.entity.Category
import com.fasterxml.jackson.annotation.JsonIgnore
import javax.persistence.*

@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "dtype")
@AllOpen
abstract class Item {
    @Id
    @GeneratedValue
    @Column(name = "item_id")
    var id: Long? = null

    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "category_id")
    var category: Category? = null

    var name: String? = null

    var price: Int = 0

    var stockQuantity: Int = 0

    var color: String? = null

    /**
     * stock 증가
     */
    fun addStock(quantity: Int) {
        stockQuantity += quantity
    }

    /**
     * stock 감소
     */
    fun removeStock(quantity: Int) {
        val restStock = stockQuantity - quantity
        if (restStock < 0) {
            throw NotEnoughStockException("need more stock")
        }
        stockQuantity = restStock
    }
}
