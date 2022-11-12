package assignment.cowaysystem.user.service

import assignment.cowaysystem.feature.user.dto.MemberFormDto
import assignment.cowaysystem.feature.user.service.MemberService
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.test.annotation.Rollback
import org.springframework.transaction.annotation.Transactional

@SpringBootTest
internal class MemberServiceTest{
    @Autowired
    lateinit var memberService: MemberService

    @Autowired
    lateinit var passwordEncoder: PasswordEncoder



    @Test
    @Transactional
    @Rollback(value = false)
    fun saveTest() {
        // given
        val memberFormDto = MemberFormDto("lee", "email", "password", "inCheon")

        // when
        val savedMember = memberService.save(memberFormDto.toEntity(passwordEncoder))

        // then
        assertEquals(savedMember.username, memberFormDto.name)
    }
}