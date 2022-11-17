package assignment.cowaysystem.feature.order.service

import assignment.cowaysystem.feature.order.controller.dto.VisitServiceReq
import assignment.cowaysystem.feature.order.repository.VisitRepository
import assignment.cowaysystem.common.exception.NotFoundException
import assignment.cowaysystem.feature.order.entity.*
import assignment.cowaysystem.feature.order.repository.OrderItemRepository
import assignment.cowaysystem.feature.order.repository.OrderRepository

import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import java.time.LocalDateTime

@Service
@Transactional(readOnly = true)
class VisitService(
        private val visitRepository: VisitRepository,
        private val orderItemRepository: OrderItemRepository,
        private val orderRepository: OrderRepository
){
    @Transactional
    fun saveVisit(
            visitServiceReq: VisitServiceReq
    ): Boolean{
        val orderItem = orderItemRepository.findById(visitServiceReq.orderItemId)
        val address = Address(
                visitServiceReq.city,
                visitServiceReq.street,
                visitServiceReq.zipcode
        )
        if (orderItem.isPresent){
            val visit = Visit().createVisit(
                    address,
                    visitServiceReq,
                    orderItem.get()
            )
            visitRepository.save(visit)
        }else{
            throw NotFoundException("${visitServiceReq.orderItemId}는 존재하지 않습니다.")
        }
        return true
    }

    /**
     * 특정 회원이 주문한 모든 내용을 보여줌
     */
    fun findOrders(loginId: String?): List<Order>{
        val orders = orderRepository.findOrderByLoginId(loginId, OrderStatus.ORDER)
        if (orders.isEmpty()) throw NotFoundException("${loginId}이 주문한 내용을 찾을 수 없습니다.")
        return orders
    }

    /**
     * 방문 서비스 신청한 상품 검색
     */
    fun findItemsByUseService(
            loginId: String?
    ): List<OrderItem>{
        val orders = findOrders(loginId)
        val orderItems = mutableListOf<OrderItem>()

        orders.forEach {order ->
            order.orderItems.forEach {orderItem ->
                if (orderItem.serviceYn == "Y") orderItems.add(orderItem)
            }
        }

        return orderItems
    }

    /**
     * 방문 서비스 신청한 상품 중 3개월이 지난 상품
     */
    fun findOldItemsByUseService(
            loginId: String?
    ): List<OrderItem>{
        val orders = findOrders(loginId)
        val oldOrderItems = mutableListOf<OrderItem>()

        /**
         * 배송이 완료된 시간에서 3개월 지난 시간이 현재 시간보다 작은경우 (배송완료 후 3개울이 지났다.)
         * 3개월이 지난 주문 (3개월은 정책으로 내가 정함)
         */
        val oldOrders = orders.filter {
            it.delivery?.deliveryEdDt?.plusMonths(3)?.isBefore(LocalDateTime.now()) == true
        }

        oldOrders.forEach {oldOrder ->
            oldOrder.orderItems.forEach {orderItem ->
                if (orderItem.serviceYn == "Y") oldOrderItems.add(orderItem)
            }
        }

        return oldOrderItems
    }

    /**
     * test를 위한 서비스
     */
    fun getVisit(
            visitId: Long
    ): Visit{
        val findVisit = visitRepository.findById(visitId)
        if (findVisit.isPresent){
            return findVisit.get()
        }else{
            throw NotFoundException("${visitId}는 존재하지 않습니다.")
        }
    }
}