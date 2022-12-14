package assignment.cowaysystem.config.auth

import assignment.cowaysystem.feature.order.entity.Member
import org.springframework.security.core.GrantedAuthority
import org.springframework.security.core.userdetails.UserDetails

/**
 * 스프링 시큐리티가 로그인 요청을 가로채서 로그인을 진행하고 완료가 되면 UserDetails 타입의 오브젝트를
 * 스프링 시큐리티의 고유한 세션 저장소에 저장해준다.
 */

class PrincipalDetails(
        private val member: Member
): UserDetails{
    // 계정이 갖고있는 권한 목록을 return한다. (권한이 여러개면 loop를 돌아 확인)
    override fun getAuthorities(): MutableCollection<out GrantedAuthority?>? {
        val authorities: MutableCollection<GrantedAuthority> = ArrayList()
        authorities.add(GrantedAuthority { "ROLE" + member.authority })
        return authorities
    }

    override fun getPassword(): String{
        return member.password!!
    }

    override fun getUsername(): String {
        return member.username!!
    }

    // 계정이 만료되지 않았는지를 return한다 (true : 만료안됨)
    override fun isAccountNonExpired(): Boolean {
        return true
    }

    // 계정이 만료되지 않았는지를 return한다 (true : 만료안됨)
    override fun isAccountNonLocked(): Boolean {
        return true
    }

    // 비밀번호가 만료되지 않았는지를 return한다 (true : 만료안됨)
    override fun isCredentialsNonExpired(): Boolean {
        return true
    }

    // 계정이 활성화(사용가능)인지를 return한다 (true : 활성화)
    override fun isEnabled(): Boolean {
        return true
    }
}