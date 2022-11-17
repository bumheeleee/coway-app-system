package assignment.cowaysystem.feature.order.controller.dto

import org.hibernate.validator.constraints.Range
import org.springframework.format.annotation.DateTimeFormat
import java.time.LocalDateTime
import javax.validation.constraints.NotBlank

class VisitServiceReq(
        @field:Range(min = 1, max = 10000000)
        val orderItemId: Long,

        @field:NotBlank(message = "city 입력형식이 올바르지 않습니다.")
        var city: String? = null,

        @field:NotBlank(message = "street 입력형식이 올바르지 않습니다.")
        var street: String? = null,

        @field:NotBlank(message = "zipcode 입력형식이 올바르지 않습니다.")
        var zipcode: String? = null,

        @field:DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss")
        val visitTime: LocalDateTime,
) {
}