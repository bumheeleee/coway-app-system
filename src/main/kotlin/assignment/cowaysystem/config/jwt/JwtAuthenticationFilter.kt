package assignment.cowaysystem.config.jwt

import org.springframework.security.authentication.AuthenticationManager
import org.springframework.security.core.Authentication
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse

/**
 * 스프링 시큐리티에 UsernamePasswordAuthenticationFilter가 있음
 * /login 요청해서 username, password 전송하면 (post)
 * UsernamePasswordAuthenticationFilter 가 동작함
 */
class JwtAuthenticationFilter(authenticationManager: AuthenticationManager?) : UsernamePasswordAuthenticationFilter(authenticationManager) {

    /**
     * /login 요청을 하면 로그인 시도를 위해 실행되는 함수
     */
    override fun attemptAuthentication(request: HttpServletRequest?, response: HttpServletResponse?): Authentication {
        println("로그인 시도 중!!! -> 일단 들어와짐")
        /**
         * 여기서 로직을 작성하면 됨
         * 1. username, password 받고
         * 2. 정상인지 로그인을 시도해봄
         * 3. authenticationManager를 사용해 로그인을 시도하면 principleDetailsService가 호출
         * 4. principleDetailsService에서 loadUserByUsername() 함수 실행
         * 5. response로 PrincipalDetails를 세션에 담고 (PrincipalDetails를 세션에 담는 이유는 권한관리 같은것을 해주기 위해)
         * 6. jwt 토큰을 만들어서 응답하면됨
         */
        return super.attemptAuthentication(request, response)
    }

}