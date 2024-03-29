package assignment.cowaysystem.feature.order.controller

import assignment.cowaysystem.common.util.throwIfHasErrors
import assignment.cowaysystem.feature.order.controller.dto.MemberFormDto
import assignment.cowaysystem.feature.order.controller.dto.MemberRes
import assignment.cowaysystem.feature.order.service.MemberService
import io.swagger.annotations.Api
import io.swagger.annotations.ApiOperation
import org.springframework.data.domain.Page
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.validation.BindingResult
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import javax.validation.Valid

@RestController
@RequestMapping("/members")
@Api(tags = ["회원관리"])
class MemberController(
        private val memberService: MemberService,
        private val passwordEncoder: PasswordEncoder
){
    @ApiOperation("회원생성")
    @PostMapping
    fun save(
            @RequestBody @Valid memberFormDto: MemberFormDto,
            bindingResult: BindingResult
    ): ResponseEntity<String>{
        bindingResult.throwIfHasErrors()
        val res = memberService.save(memberFormDto.toEntity(passwordEncoder))
        return if (res != null) ResponseEntity.ok("ok") else ResponseEntity(HttpStatus.BAD_REQUEST)
    }

    @ApiOperation("회원 전체 조회")
    @GetMapping
    fun findAll(): Page<MemberRes>{
        return memberService.findAll().map {
            MemberRes(it)
        }
    }

}