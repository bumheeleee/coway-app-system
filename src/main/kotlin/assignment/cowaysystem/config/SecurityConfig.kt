package assignment.cowaysystem.config

import lombok.RequiredArgsConstructor
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
@RequiredArgsConstructor
class SecurityConfiguration(
        private val corsFilter: CorsFilter
): WebSecurityConfigurerAdapter() {
    /**
     * jwt를 사용하기 위해
     * 1. formLogin 사용 x
     * 2. httpBasic 사용 x
     * 3. session 사용 x
     */
    override fun configure(http: HttpSecurity) {
        http.csrf().disable()
        // 세션을 사용하지 않겠다.
        // form login 사용하지 않겠다.
        http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                .addFilter(corsFilter)   //모든 요청이 corsFilter를 타서 cors 정책으로부터 벗어날 수 있다.
                .formLogin().disable()
                .httpBasic().disable()
                .authorizeRequests()
                .antMatchers("/members/**")
                .access("hasRole('ROLE_USER') or hasRole('ROLE_MANAGER') or hasRole('ROLE_ADMIN')")
                .antMatchers("/managers/**")
                .access("hasRole('ROLE_MANAGER') or hasRole('ROLE_ADMIN')")
                .antMatchers("/admins/**")
                .access("hasRole('ROLE_ADMIN')")
                .anyRequest().permitAll()
    }

    @Bean
    fun passwordEncoder(): PasswordEncoder? {
        return BCryptPasswordEncoder()
    }
}