package assignment.cowaysystem.feature.order.entity

import lombok.AccessLevel
import lombok.NoArgsConstructor
import javax.persistence.Embeddable

@Embeddable
@NoArgsConstructor(access = AccessLevel.PROTECTED)
class Address(
        var city: String? = null,
        var street: String? = null,
        var zipcode: String? = null
){

}