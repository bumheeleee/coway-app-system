package assignment.cowaysystem.feature.order.controller

import assignment.cowaysystem.feature.order.controller.dto.VisitServiceReq
import assignment.cowaysystem.feature.order.service.VisitService
import io.swagger.annotations.Api
import io.swagger.annotations.ApiOperation
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/visit")
@Api(tags = ["방문 서비스 관리"])
class VisitController(
        private val visitService: VisitService
){
    @PostMapping
    @ApiOperation("방문 서비스 이용")
    fun saveVisit(
            @RequestBody visitServiceReq: VisitServiceReq
    ): ResponseEntity<String>{
        val visit = visitService.saveVisit(visitServiceReq)
        return if (visit != null) ResponseEntity.ok("ok") else ResponseEntity(HttpStatus.BAD_REQUEST)
    }
}