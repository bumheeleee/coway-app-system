package assignment.cowaysystem.feature.order.controller

import assignment.cowaysystem.feature.order.dto.SearchItemRes
import assignment.cowaysystem.feature.order.service.ItemService
import io.swagger.annotations.Api
import io.swagger.annotations.ApiOperation
import org.springframework.data.domain.Page
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController



@RestController
@RequestMapping("/orders")
@Api(tags = ["상품 관리"])
class OrderController(
        private val itemService: ItemService
){
    @ApiOperation("상품 검색", notes = "메인 페이지에서 상품검색(이름, 카테고리)")
    @GetMapping("/{searchKeyword}")
    fun searchItem(
            @PathVariable("searchKeyword") searchKeyword: String?
    ): Page<SearchItemRes> {
        return itemService.search(searchKeyword).map {
            SearchItemRes(it)
        }
    }
}