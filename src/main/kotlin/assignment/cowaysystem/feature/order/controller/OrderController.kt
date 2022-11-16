package assignment.cowaysystem.feature.order.controller

import assignment.cowaysystem.feature.order.dto.ItemRes
import assignment.cowaysystem.feature.order.dto.OrderReq
import assignment.cowaysystem.feature.order.service.OrderService
import io.swagger.annotations.Api
import io.swagger.annotations.ApiOperation
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.GetMapping
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
    ): ResponseEntity<String>{
        val order = orderService.order(loginId, orderReq)
        return if (order != null) ResponseEntity.ok("ok") else ResponseEntity(HttpStatus.BAD_REQUEST)
    }

    @DeleteMapping("/{loginId}")
    @ApiOperation("주문 취소 기능")
    fun cancelOrder(@PathVariable("orderId") orderId: Long): ResponseEntity<String>{
        val res = orderService.cancelOrder(orderId)
        return if (res) ResponseEntity.ok("ok") else ResponseEntity(HttpStatus.BAD_REQUEST)
    }

    @GetMapping("/{loginId}")
    @ApiOperation("방문서비스 이용 상품검색", notes = "방문 서비스를 신청한 상품에 대한 상품검색")
    fun getUseServiceItem(
            @PathVariable("loginId") loginId: String,
    ): List<ItemRes>{
        return orderService.findItemsByUseService(loginId).map {
            ItemRes(it)
        }
    }

    @GetMapping("old/{loginId}")
    @ApiOperation("방문서비스 이용 상품중 3개월 지난 상품검색", notes = "방문 서비스를 신청한 상품중 3개월이 지난 상품검색")
    fun getOldItem(
            @PathVariable("loginId") loginId: String,
    ): List<ItemRes>{
        return orderService.findOldItemsByUseService(loginId).map {
            ItemRes(it)
        }
    }

}