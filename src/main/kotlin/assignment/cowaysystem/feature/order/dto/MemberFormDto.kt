package assignment.cowaysystem.feature.order.dto

import assignment.cowaysystem.feature.order.entity.Address
import assignment.cowaysystem.feature.order.entity.Member
import org.jetbrains.annotations.NotNull
import org.springframework.security.crypto.password.PasswordEncoder
import javax.validation.constraints.Pattern

class MemberFormDto(
        @field:NotNull("이름은 필수 입력 값입니다.")
        val name: String? = null,

        @field:NotNull("이메일은 필수 입력 값입니다.")
        val email: String? = null,

        @field:NotNull("비밀번호는 필수 입력 값입니다.")
        //@field:Pattern(regexp = "[^(?=.*\\d)(?=.*[a-zA-Z])[0-9a-zA-Z]{6,24}\$]")
        val password: String? = null,

        @field:NotNull("주소는 필수 입력 값입니다.")
        val address: Address? = null
){
        fun toEntity(passwordEncoder: PasswordEncoder): Member {
                return Member().also {
                        it.username = name
                        it.email = email
                        it.password = passwordEncoder.encode(password)
                        it.address = address
                }
        }
}