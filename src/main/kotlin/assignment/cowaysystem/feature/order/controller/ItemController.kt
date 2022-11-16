package assignment.cowaysystem.feature.order.controller

import assignment.cowaysystem.common.exception.BadRequestException
import assignment.cowaysystem.feature.order.controller.dto.SaveItemReq
import assignment.cowaysystem.feature.order.controller.dto.SearchItemRes
import assignment.cowaysystem.feature.order.repository.CategoryRepository
import assignment.cowaysystem.feature.order.service.ItemService
import io.swagger.annotations.Api
import io.swagger.annotations.ApiOperation
import org.springframework.data.domain.Page
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/item")
@Api(tags = ["상품 관리"])
class ItemController(
        private val itemService: ItemService,
        private val categoryRepository: CategoryRepository
){
    /**
     * 테스트를 위한 api
     */
    @ApiOperation("상품 추가", notes = "모든 상품은 카테고리가 있어야 한다.")
    @PostMapping
    fun saveItem(
            @RequestBody saveItemReq: SaveItemReq
    ): ResponseEntity<String> {
        val item = saveItemReq.toItem(categoryRepository)?: throw BadRequestException("존재하지 않는 카테고리입니다.")
        val saveItem = itemService.saveItem(item)
        return if (saveItem != null) ResponseEntity.ok("ok") else ResponseEntity(HttpStatus.BAD_REQUEST)
    }

    @ApiOperation("상품 검색", notes = "메인 페이지에서 상품검색(이름, 카테고리)")
    @GetMapping("/{searchKeyword}")
    fun searchItem(
            @PathVariable("searchKeyword") searchKeyword: String
    ): Page<SearchItemRes> {
        return itemService.search(searchKeyword).map {
            SearchItemRes(it)
        }
    }

    @ApiOperation("인기상품 검색", notes = "인기상품에서 상품검색 (카테고리 이름을 통한 검색)")
    @GetMapping("category/{categoryName}")
    fun searchItemByCategory(
            @PathVariable("categoryName") categoryName: String
    ): Page<SearchItemRes> {
        return itemService.searchCategory(categoryName).map {
            SearchItemRes(it)
        }
    }

}