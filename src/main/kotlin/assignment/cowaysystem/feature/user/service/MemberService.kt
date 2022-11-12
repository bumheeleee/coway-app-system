package assignment.cowaysystem.feature.user.service

import assignment.cowaysystem.common.exception.BadRequestException
import assignment.cowaysystem.feature.user.entity.Member
import assignment.cowaysystem.feature.user.repository.MemberRepository
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Transactional(readOnly = true)
@Service
class MemberService(
        private val memberRepository: MemberRepository
){
    @Transactional
    fun save(member: Member): Member {
        validateDuplicateMember(member)
        return memberRepository.save(member)
    }

    fun validateDuplicateMember(member: Member){
        val findMember = member.email?.let { memberRepository.findByEmail(it) }
        if (findMember != null){
            throw BadRequestException("이미 가입된 회원입니다.")
        }
    }
}