package assignment.cowaysystem.feature.order.controller

import assignment.cowaysystem.common.exception.BadRequestException
import assignment.cowaysystem.feature.order.dto.SaveCategoryReq
import assignment.cowaysystem.feature.order.dto.SaveItemReq
import assignment.cowaysystem.feature.order.service.CategoryService
import io.swagger.annotations.Api
import io.swagger.annotations.ApiOperation
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/category")
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
            @RequestBody saveCategoryReq: SaveCategoryReq
    ): ResponseEntity<String> {
        val saveCategory = categoryService.saveCategory(saveCategoryReq.toCategory())
        return if (saveCategory != null) ResponseEntity.ok("ok") else ResponseEntity(HttpStatus.BAD_REQUEST)
    }
}