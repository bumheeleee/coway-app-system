package assignment.cowaysystem.config.jwt

import assignment.cowaysystem.config.auth.PrincipalDetails
import assignment.cowaysystem.feature.order.entity.Member
import com.fasterxml.jackson.databind.ObjectMapper
import org.springframework.security.authentication.AuthenticationManager
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.Authentication
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter
import java.io.IOException
import javax.servlet.FilterChain
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
     *
     * 여기서 로직을 작성하면 됨
     * 1. username, password 받고
     * 2. 정상인지 로그인을 시도해봄
     * 3. authenticationManager를 사용해 로그인을 시도하면 principleDetailsService가 호출
     * 4. principleDetailsService에서 loadUserByUsername() 함수 실행
     * 5. response로 PrincipalDetails를 세션에 담고 (PrincipalDetails를 세션에 담는 이유는 권한관리 같은것을 해주기 위해)
     * 6. jwt 토큰을 만들어서 응답하면됨
     */
    override fun attemptAuthentication(request: HttpServletRequest?, response: HttpServletResponse?): Authentication? {
        try{
            println("로그인 시도 중!!! -> 일단 들어와짐")
            val mapper = ObjectMapper()
            val member = mapper.readValue(request!!.inputStream, Member::class.java)
            // 토큰 제작
            val authenticationToken = UsernamePasswordAuthenticationToken(member.loginId, member.password)

            // 이때 principalDetailsService의 loadUserByUsername()이 호출된다.
            // DB애 있는 loginId와 password가 일치하는것을 확인
            val authentication = authenticationManager.authenticate(authenticationToken)

            val principalDetails = authentication.principal as PrincipalDetails
            println(principalDetails.username)
            println(principalDetails.password)

            // authentication 객체가 session영역에 저장해야되는게 그방법이 return하는거임
            // 리턴을 해서 session에 저장하는 이유는 권한관리를 security가 대신 해주기 때문에 편안하기 위해 사용
            // 굳이 jwt 토큰을 사용하면서 session을 사용할 필요는 없음
            return authentication
        }catch (e: IOException){
            e.printStackTrace()
        }
        return null
    }
    // attemptAuthentication 실행 후 인증이 정상적으로 되었으면 successfulAuthentication 함수가 실행됨
    // jwt 토큰을 만들어서 요청한 클라이언트한데 jwt 토큰을 response 해줘야됨!
    override fun successfulAuthentication(request: HttpServletRequest?, response: HttpServletResponse?, chain: FilterChain?, authResult: Authentication?) {
        super.successfulAuthentication(request, response, chain, authResult)
    }
}