package assignment.cowaysystem.config

import assignment.cowaysystem.config.jwt.JwtAuthenticationFilter
import assignment.cowaysystem.config.jwt.JwtAuthorizationFilter
import assignment.cowaysystem.feature.order.repository.MemberRepository
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter
import org.springframework.security.config.http.SessionCreationPolicy
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.web.filter.CorsFilter

@Configuration
@EnableWebSecurity
class SecurityConfiguration(
        private val corsFilter: CorsFilter,
        private val memberRepository: MemberRepository
): WebSecurityConfigurerAdapter() {
    /**
     * jwt를 사용하기 위해
     * 1. formLogin 사용 x
     * 2. httpBasic 사용 x
     * 3. session 사용 x
     * UsernamePasswordAuthenticationFilter
     * http.addFilterBefore(MyFilter(), BasicAuthenticationFilter::class.java)
     */
    override fun configure(http: HttpSecurity) {
        //http.addFilterBefore(MyFilter3(), BasicAuthenticationFilter::class.java)
        http.csrf().disable()
        // 세션을 사용하지 않겠다.
        // form login 사용하지 않겠다.
        http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                .addFilter(corsFilter)   //모든 요청이 corsFilter를 타서 cors 정책으로부터 벗어날 수 있다.
                .formLogin().disable()
                .httpBasic().disable()
                .addFilter(JwtAuthenticationFilter(authenticationManager()))
                .addFilter(JwtAuthorizationFilter(authenticationManager(), memberRepository))
                .authorizeRequests()
//                .antMatchers("/members/**")
//                .access("hasRole('ROLE_USER') or hasRole('ROLE_MANAGER') or hasRole('ROLE_ADMIN')")
                .antMatchers("/managers/**")
                .access("hasRole('ROLE_MANAGER') or hasRole('ROLE_ADMIN')")
                .antMatchers("/admins/**")
                .access("hasRole('ROLE_ADMIN')")
                .antMatchers(
                        "/",
                        "/v2/api-docs", "/swagger-resources/**", "/swagger-ui.html", "/webjars/**", "/swagger/**",   // swagger
                        "/h2-console/**",
                        "/favicon.ico"
                ).permitAll()
                .anyRequest().permitAll()
    }

    // password encoder
    @Bean
    fun passwordEncoder(): PasswordEncoder? {
        return BCryptPasswordEncoder()
    }
}