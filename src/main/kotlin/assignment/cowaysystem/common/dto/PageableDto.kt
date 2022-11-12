package assignment.cowaysystem.common.dto

import org.springframework.data.domain.PageRequest
import org.springframework.data.domain.Pageable
import org.springframework.data.domain.Sort

class PageableDto(
        val order: String
){
    private val size: Int = 10
    private val page: Int = 0

    fun toPageable(): Pageable{
        return PageRequest.of(
                page,
                size,
                Sort.Direction.DESC, "$order"
        )
    }

}