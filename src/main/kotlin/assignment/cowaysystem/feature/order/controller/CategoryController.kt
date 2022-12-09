package assignment.cowaysystem.feature.order.controller

import assignment.cowaysystem.common.util.throwIfHasErrors
import assignment.cowaysystem.feature.order.controller.dto.CategoryNameRes
import assignment.cowaysystem.feature.order.controller.dto.CategoryReq
import assignment.cowaysystem.feature.order.controller.dto.Result
import assignment.cowaysystem.feature.order.controller.dto.SearchItemRes
import assignment.cowaysystem.feature.order.service.CategoryService
import io.swagger.annotations.Api
import io.swagger.annotations.ApiOperation
import org.springframework.data.domain.Page
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
@RequestMapping("/categories")
@Api(tags = ["카테고리 관리"])
class CategoryController(
        private val categoryService: CategoryService
){
    /**
     * 테스트를 위한 api
     */
    @ApiOperation("카테고리 추가", notes = "모든 상품은 카테고리가 있어야 한다.")
    @PostMapping
    fun saveItem(
            @RequestBody @Valid categoryReq: CategoryReq,
            bindingResult: BindingResult
    ): ResponseEntity<String> {
        bindingResult.throwIfHasErrors()
        val saveCategory = categoryService.saveCategory(categoryReq.toCategory())
        return if (saveCategory != null) ResponseEntity.ok("ok") else ResponseEntity(HttpStatus.BAD_REQUEST)
    }

    /**
     * 모든 항목 카테고리 이름 검색
     */
    @ApiOperation("모든 카테고리 종류 검색", notes = "모든 카테고리 종류 검색 API")
    @GetMapping
    fun findItemByCategory(): Result<List<CategoryNameRes>>{
        val data = categoryService.findAll().map {
            CategoryNameRes(it)
        }
        return Result(data)
    }
}