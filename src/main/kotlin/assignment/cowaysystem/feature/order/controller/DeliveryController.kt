package assignment.cowaysystem.feature.order.controller

import assignment.cowaysystem.common.exception.BadRequestException
import assignment.cowaysystem.feature.order.controller.dto.SaveItemReq
import assignment.cowaysystem.feature.order.service.DeliveryService
import io.swagger.annotations.Api
import io.swagger.annotations.ApiOperation
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/delivery")
@Api(tags = ["배송 관리"])
class DeliveryController(
        private val deliveryService: DeliveryService
){
    /**
     * 테스트를 위한 api
     */
    @ApiOperation("배달 완료", notes = "해당 주문에 대해 배달 완료 처리")
    @PostMapping("/{orderId}")
    fun saveItem(
            @PathVariable("orderId") orderId: Long
    ): ResponseEntity<String> {
        val res = deliveryService.deliveryComp(orderId)
        return if (res) ResponseEntity.ok("ok") else ResponseEntity(HttpStatus.BAD_REQUEST)
    }
}