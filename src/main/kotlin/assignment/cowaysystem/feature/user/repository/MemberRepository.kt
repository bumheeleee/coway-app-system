package assignment.cowaysystem.feature.user.repository

import assignment.cowaysystem.feature.user.entity.Member
import org.springframework.data.jpa.repository.JpaRepository

interface MemberRepository: JpaRepository<Member, Long> {
    fun findByEmail(email: String): Member?
}