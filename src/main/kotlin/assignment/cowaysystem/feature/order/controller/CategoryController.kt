package assignment.cowaysystem.feature.order.controller

import assignment.cowaysystem.feature.order.dto.CategoryReq
import assignment.cowaysystem.feature.order.dto.SearchItemRes
import assignment.cowaysystem.feature.order.service.CategoryService
import io.swagger.annotations.Api
import io.swagger.annotations.ApiOperation
import org.springframework.data.domain.Page
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
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
            @RequestBody categoryReq: CategoryReq
    ): ResponseEntity<String> {
        val saveCategory = categoryService.saveCategory(categoryReq.toCategory())
        return if (saveCategory != null) ResponseEntity.ok("ok") else ResponseEntity(HttpStatus.BAD_REQUEST)
    }

    /**
     * 카테고리를 통한 Item 검색
     */
    @ApiOperation("카테고리를 통한 상품검색", notes = "인기 상품에서 카테고리 이름을 통한 검색")
    @GetMapping("/{name}")
    fun findItemByCategory(
            @PathVariable("name") name: String
    ): List<SearchItemRes>{
        return categoryService.findItemByCategory(name).map {
            SearchItemRes(it)
        }
    }
}