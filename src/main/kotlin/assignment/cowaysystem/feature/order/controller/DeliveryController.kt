package assignment.cowaysystem.feature.order.controller

import assignment.cowaysystem.common.exception.BadRequestException
import assignment.cowaysystem.feature.order.controller.dto.OrderItemRes
import assignment.cowaysystem.feature.order.controller.dto.SaveItemReq
import assignment.cowaysystem.feature.order.service.DeliveryService
import io.swagger.annotations.Api
import io.swagger.annotations.ApiOperation
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/delivery")
@Api(tags = ["배송 관리"])
class DeliveryController(
        private val deliveryService: DeliveryService
){
    /**
     * 테스트를 위한 api
     */
    @ApiOperation("배달 완료 기능", notes = "해당 주문에 대해 배달 완료 처리")
    @PostMapping("/{orderId}")
    fun saveItem(
            @PathVariable("orderId") orderId: Long
    ): ResponseEntity<String> {
        val res = deliveryService.deliveryComp(orderId)
        return if (res) ResponseEntity.ok("ok") else ResponseEntity(HttpStatus.BAD_REQUEST)
    }


    @GetMapping("/{loginId}")
    @ApiOperation("배달이 완료된 상품 조회", notes = "특정 회원의 상품중 배달이 완료된 상품을 조회")
    fun getDeliveryCompItem(
            @PathVariable("loginId") loginId: String
    ): List<OrderItemRes>{
        return deliveryService.findCompDeliveryItem(loginId).map {
            OrderItemRes(it)
        }
    }
}