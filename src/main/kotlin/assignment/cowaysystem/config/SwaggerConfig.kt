package assignment.cowaysystem.config

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import springfox.documentation.builders.ApiInfoBuilder
import springfox.documentation.builders.RequestHandlerSelectors
import springfox.documentation.service.ApiInfo
import springfox.documentation.spi.DocumentationType
import springfox.documentation.spring.web.plugins.Docket
import springfox.documentation.swagger2.annotations.EnableSwagger2

@Configuration
@EnableSwagger2
class SwaggerConfiguration {
    @Bean
    fun swaggerAPI(): Docket {
        return Docket(DocumentationType.SWAGGER_2)
                .apiInfo(swaggerInfo())
                .select()
                .apis(RequestHandlerSelectors.basePackage("assignment.cowaysystem"))
                .build()
                .useDefaultResponseMessages(true)
    }

    private fun swaggerInfo(): ApiInfo {
        return ApiInfoBuilder()
                .title("코웨이 앱 서비스")
                .description("API 명세")
                .version("0.0.1")
                .build()
    }
}
