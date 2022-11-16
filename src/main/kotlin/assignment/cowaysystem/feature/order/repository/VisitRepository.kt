package assignment.cowaysystem.feature.order.repository

import assignment.cowaysystem.feature.order.entity.Visit
import org.springframework.data.jpa.repository.JpaRepository

interface VisitRepository: JpaRepository<Visit, Long> {
}