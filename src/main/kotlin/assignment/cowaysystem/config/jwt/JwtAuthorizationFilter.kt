package assignment.cowaysystem.config.jwt

import assignment.cowaysystem.config.auth.PrincipalDetails
import assignment.cowaysystem.feature.order.repository.MemberRepository
import com.auth0.jwt.JWT
import com.auth0.jwt.algorithms.Algorithm
import assignment.cowaysystem.common.exception.NotFoundException
import org.springframework.security.authentication.AuthenticationManager
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter
import javax.servlet.FilterChain
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse

/**
 * <허가>
 * 시큐리티가 filter를 가지고 있는데, 그 필터중에 BasicAuthenticationFilter라는것이 있음
 * BasicAuthenticationFilter : Authorization 해더에 Basic 토큰을 인증해주는 역할을 한다.
 * 권한이나 인증이 필요한 특정 주소를 요청했을때, 위에 필터를 무조건 통과하게 되어있음
 * 만약에 권한,인증이 필요한 주소가 아니라고 하면 이 필터를 통과하지 않는다.
 */
class JwtAuthorizationFilter(
        authenticationManager: AuthenticationManager?,
        private val memberRepository: MemberRepository
) : BasicAuthenticationFilter(authenticationManager) {

    /**
     * 인증이나 권한이 필요한 주소요청이 있을때, 해당 필터를 타게 될것
     */
    override fun doFilterInternal(request: HttpServletRequest, response: HttpServletResponse, chain: FilterChain) {
        println("인증이나 권한이 필요한 주소 요청됨")

        val jwtHeader = request.getHeader("Authorization")
        //헤더가 있는지를 확인한다.
        if (jwtHeader == null || !jwtHeader.startsWith("Bearer")){
            chain.doFilter(request, response)
            return
        }

        val jwtToken = request.getHeader("Authorization").replace("Bearer ", "")
        val loginIdString = JWT.require(Algorithm.HMAC512("dlqjagml")).build().verify(jwtToken).getClaim("id").toString()
        val loginId = loginIdString.substring(1, loginIdString.length - 1)

        //loginId가 null이 아니면 일단은 인증이 되긴했다.
        if (loginId != null){
            val member = memberRepository.findByLoginId(loginId)?: throw NotFoundException("존재하지 않는 $loginId 입니다.")
            val principalDetails = PrincipalDetails(member)

            val authentication = UsernamePasswordAuthenticationToken(principalDetails, null, principalDetails.authorities)
            SecurityContextHolder.getContext().authentication = authentication
        }
        chain.doFilter(request, response)

    }
}