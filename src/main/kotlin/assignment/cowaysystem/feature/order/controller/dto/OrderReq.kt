package assignment.cowaysystem.feature.order.controller.dto

import org.hibernate.validator.constraints.Range
import javax.validation.constraints.NotBlank
import javax.validation.constraints.Pattern

/**
 * 주문을 하기 위한 req dto
 */
class OrderReq(
        @field:NotBlank(message = "itemName 입력형식이 올바르지 않습니다.")
        val itemName: String? = null,

        @field:Range(min = 1, max = 100)
        val count: Int = 0,

        @field:NotBlank(message = "color 입력형식이 올바르지 않습니다.")
        val color: String? = null,

        @field:Pattern(regexp = "Y|N")
        val serviceYn: String = "N"
){
}