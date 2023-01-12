package assignment.cowaysystem.feature.order.controller.dto

import assignment.cowaysystem.feature.order.entity.Member

class MemberRes(
        member: Member
){
    val loginId = member.loginId
    val username = member.username
    val email = member.email
    val address = member.address
    val order = if (member.orders.isNullOrEmpty()) member.orders.forEach { it.id } else null
    val role = member.getRole()
    val createdDate = member.createdDate
    val lastModifiedDate = member.lastModifiedDate
}