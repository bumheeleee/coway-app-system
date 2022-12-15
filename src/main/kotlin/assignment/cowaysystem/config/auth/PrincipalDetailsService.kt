package assignment.cowaysystem.config.auth

import assignment.cowaysystem.feature.order.repository.MemberRepository
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.stereotype.Service

/**
 * http://localhost:8080/login -> loadUserByUsername가 호출된다.
 */
@Service
class PrincipalDetailsService(
        private val memberRepository: MemberRepository
): UserDetailsService{

    override fun loadUserByUsername(loginId: String?): UserDetails? {
        println("loadUserByUsername의 실행")
        val memberEntity = memberRepository.findByLoginId(loginId!!)
        return if (memberEntity != null) PrincipalDetails(memberEntity) else null
    }
}