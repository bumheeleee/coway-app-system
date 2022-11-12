package assignment.cowaysystem

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.data.jpa.repository.config.EnableJpaAuditing

@EnableJpaAuditing
@SpringBootApplication
class CowaysystemApplication

fun main(args: Array<String>) {
	runApplication<CowaysystemApplication>(*args)
}
