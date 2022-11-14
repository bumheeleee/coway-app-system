package assignment.cowaysystem.feature.order.controller

import assignment.cowaysystem.feature.order.dto.OrderReq
import assignment.cowaysystem.feature.order.service.OrderService
import io.swagger.annotations.Api
import io.swagger.annotations.ApiOperation
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController

@RequestMapping("/order")
@Api(tags = ["주문 관리"])
class OrderController(
        private val orderService: OrderService
){
    @PostMapping("/{loginId}")
    @ApiOperation("주문 기능")
    fun order(
            @PathVariable("loginId") loginId: String,
            @RequestBody orderReq: List<OrderReq>
    ): ResponseEntity<String> {
        val order = orderService.order(loginId, orderReq)
        return if (order != null) ResponseEntity.ok("ok") else ResponseEntity(HttpStatus.BAD_REQUEST)
    }

}