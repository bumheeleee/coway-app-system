package assignment.cowaysystem.feature.order.service

import assignment.cowaysystem.feature.order.controller.dto.VisitServiceReq
import assignment.cowaysystem.feature.order.entity.Visit
import assignment.cowaysystem.feature.order.repository.VisitRepository
import assignment.cowaysystem.common.exception.NotFoundException
import assignment.cowaysystem.feature.order.repository.OrderItemRepository

import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
@Transactional(readOnly = true)
class VisitService(
        private val visitRepository: VisitRepository,
        private val orderItemRepository: OrderItemRepository
){
    @Transactional
    fun saveVisit(
            visitServiceReq: VisitServiceReq
    ): Visit{
        val orderItem = orderItemRepository.findById(visitServiceReq.orderItemId)
        if (orderItem.isPresent){
            val visit = Visit().createVisit(
                    visitServiceReq.address,
                    visitServiceReq.visitTime,
                    orderItem.get()
            )
            return visitRepository.save(visit)
        }else{
            throw NotFoundException("${visitServiceReq.orderItemId}는 존재하지 않습니다.")
        }
    }
}