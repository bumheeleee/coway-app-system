package assignment.cowaysystem.feature.order.service

import assignment.cowaysystem.common.exception.BadRequestException
import assignment.cowaysystem.feature.order.repository.OrderRepository
import org.springframework.stereotype.Service
import assignment.cowaysystem.common.exception.NotFoundException
import assignment.cowaysystem.feature.order.entity.DeliveryStatus
import assignment.cowaysystem.feature.order.entity.OrderStatus
import org.springframework.transaction.annotation.Transactional
import java.time.LocalDateTime

@Service
class DeliveryService(
    private val orderRepository: OrderRepository
){
    /**
     * test API
     * 배달이 완료된 상품을 만들기 위한 API
     */
    @Transactional
    fun deliveryComp(orderId: Long): Boolean{
        val findOrder = orderRepository.findById(orderId)
        if (findOrder.isPresent){
            if(findOrder.get().status == OrderStatus.CANCEL){
                throw BadRequestException("배송완료를 할 수 없는 주문입니다.")
            }else{
                findOrder.get().delivery?.deliveryEdDt = LocalDateTime.now()
                findOrder.get().delivery?.status = DeliveryStatus.COMP
            }
        }else{
            throw NotFoundException("해당 ${orderId}는 존재하지 않습니다.")
        }
        return true
    }
}