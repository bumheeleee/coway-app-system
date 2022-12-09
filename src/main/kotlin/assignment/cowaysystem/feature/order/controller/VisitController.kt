package assignment.cowaysystem.feature.order.controller

import assignment.cowaysystem.common.util.throwIfHasErrors
import assignment.cowaysystem.feature.order.controller.dto.OrderItemRes
import assignment.cowaysystem.feature.order.controller.dto.VisitServiceReq
import assignment.cowaysystem.feature.order.controller.dto.VisitServiceRes
import assignment.cowaysystem.feature.order.controller.dto.Result
import assignment.cowaysystem.feature.order.service.VisitService
import io.swagger.annotations.Api
import io.swagger.annotations.ApiOperation
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.validation.BindingResult
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import javax.validation.Valid

@RestController
@RequestMapping("/visit")
@Api(tags = ["방문 서비스 관리"])
class VisitController(
        private val visitService: VisitService
){
    @PostMapping
    @ApiOperation("방문 서비스 이용")
    fun saveVisit(
            @RequestBody @Valid visitServiceReq: VisitServiceReq,
            bindingResult: BindingResult
    ): ResponseEntity<String>{
        bindingResult.throwIfHasErrors()
        val res = visitService.saveVisit(visitServiceReq)
        return if (res) ResponseEntity.ok("ok") else ResponseEntity(HttpStatus.BAD_REQUEST)
    }

    @GetMapping("/{visitId}")
    @ApiOperation("방문서비스 이용 내역 조회")
    fun getVisit(
            @PathVariable("visitId") visitId: Long
    ): Result<VisitServiceRes>{
        val data = VisitServiceRes(visitService.getVisit(visitId))
        return Result(data)
    }

    @GetMapping("service/{loginId}")
    @ApiOperation("방문서비스 이용 상품검색", notes = "방문 서비스를 신청한 상품에 대한 상품검색")
    fun getUseServiceItem(
            @PathVariable("loginId") loginId: String,
    ): Result<List<OrderItemRes>>{
        val data = visitService.findItemsByUseService(loginId).map {
            OrderItemRes(it)
        }
        return Result(data)
    }

    @GetMapping("service/{loginId}/old")
    @ApiOperation("방문서비스 이용 상품중 3개월 지난 상품검색", notes = "방문 서비스를 신청한 상품중 3개월이 지난 상품검색")
    fun getOldItem(
            @PathVariable("loginId") loginId: String,
    ): Result<List<OrderItemRes>>{
        val data = visitService.findOldItemsByUseService(loginId).map {
            OrderItemRes(it)
        }
        return Result(data)
    }
}