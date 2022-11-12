package assignment.cowaysystem.feature.order.entity.item

import javax.persistence.Entity
import javax.persistence.EnumType
import javax.persistence.Enumerated

@Entity
class Mattress: Item(){
    @Enumerated(EnumType.STRING)
    var size: Size? = null
}