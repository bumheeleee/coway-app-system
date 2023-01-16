package assignment.cowaysystem.feature.order.controller.dto

import assignment.cowaysystem.feature.order.entity.Address
import assignment.cowaysystem.feature.order.entity.Member
import org.springframework.security.crypto.password.PasswordEncoder
import javax.validation.Valid
import javax.validation.constraints.NotBlank
import javax.validation.constraints.NotNull

class MemberFormDto(
        @field:NotBlank(message = "loginId 입력형식이 올바르지 않습니다.")
        val loginId: String? = null,

        @field:NotBlank(message = "name 입력형식이 올바르지 않습니다.")
        val name: String? = null,

        @field:NotBlank(message = "email 입력형식이 올바르지 않습니다.")
        val email: String? = null,

        @field:NotBlank(message = "password 입력형식이 올바르지 않습니다.")
        val password: String? = null,

        @field:NotBlank(message = "city 입력형식이 올바르지 않습니다.")
        val city: String? = null,

        @field:NotBlank(message = "street 입력형식이 올바르지 않습니다.")
        val street: String? = null,

        @field:NotBlank(message = "zipcode 입력형식이 올바르지 않습니다.")
        val zipcode: String? = null
){
        fun toEntity(passwordEncoder: PasswordEncoder): Member {
                val address = Address(city, street, zipcode)
                return Member().also {
                        it.loginId = loginId
                        it.username = name
                        it.email = email
                        it.password = passwordEncoder.encode(password)
                        it.address = address
                }
        }
}