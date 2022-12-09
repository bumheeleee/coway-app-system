package assignment.cowaysystem.feature.order.controller

import assignment.cowaysystem.common.util.throwIfHasErrors
import assignment.cowaysystem.feature.order.controller.dto.OrderReq
import assignment.cowaysystem.feature.order.controller.dto.OrderRes
import assignment.cowaysystem.feature.order.controller.dto.Result
import assignment.cowaysystem.feature.order.service.OrderService
import io.swagger.annotations.Api
import io.swagger.annotations.ApiOperation
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.validation.BindingResult
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import javax.validation.Valid

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
            @RequestBody @Valid orderReq: List<OrderReq>,
            bindingResult: BindingResult
    ): ResponseEntity<String>{
        bindingResult.throwIfHasErrors()
        val order = orderService.order(loginId, orderReq)
        return if (order != null) ResponseEntity.ok("ok") else ResponseEntity(HttpStatus.BAD_REQUEST)
    }

    @DeleteMapping("/{orderId}")
    @ApiOperation("주문 취소 기능")
    fun cancelOrder(@PathVariable("orderId") orderId: Long): ResponseEntity<String>{
        val res = orderService.cancelOrder(orderId)
        return if (res) ResponseEntity.ok("ok") else ResponseEntity(HttpStatus.BAD_REQUEST)
    }

    @GetMapping("/{loginId}")
    @ApiOperation("주문 조회 기능", notes = "특정 회원이 주문한 주문서 조회")
    fun getOrder(@PathVariable("loginId") loginId: String): Result<List<OrderRes>>{
        val data = orderService.findOrders(loginId).map {
            OrderRes(it)
        }
        return Result(data)
    }

    @GetMapping("cancel/{loginId}")
    @ApiOperation("주문취소 조회 기능", notes = "특정 회원이 주문 취소한 주문서 조회")
    fun getCancelOrder(@PathVariable("loginId") loginId: String): Result<List<OrderRes>>{
        val data = orderService.findCancelOrders(loginId).map {
            OrderRes(it)
        }
        return Result(data)
    }
}