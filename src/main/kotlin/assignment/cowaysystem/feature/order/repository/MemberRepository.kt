package assignment.cowaysystem.feature.order.repository

import assignment.cowaysystem.feature.order.entity.Member
import org.springframework.data.jpa.repository.JpaRepository

interface MemberRepository: JpaRepository<Member, Long> {
    fun findByEmail(email: String): Member?
}