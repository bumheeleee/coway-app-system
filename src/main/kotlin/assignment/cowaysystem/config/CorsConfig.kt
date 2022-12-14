package assignment.cowaysystem.config

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.web.cors.CorsConfiguration
import org.springframework.web.cors.UrlBasedCorsConfigurationSource
import org.springframework.web.filter.CorsFilter
@Configuration
class CorsConfig {
    @Bean
    fun corsFilter(): CorsFilter{
        val source = UrlBasedCorsConfigurationSource()
        val config = CorsConfiguration()
        config.allowCredentials = true      // 내 서버가 응답을 할때 json을 자바스크립트에서 처리할 수 있게 할지를 결정
        config.addAllowedOrigin("*")  // 모든 ip에 응답을 허용하겠다.
        config.addAllowedHeader("*") // 모든 header에 응답을 허용하겠다.
        config.addAllowedMethod("*")    // 모든 메서드의 요청을 허용하겠다.
        source.registerCorsConfiguration("**", config)
        return CorsFilter(source)
    }
}