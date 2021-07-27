package com.musinsa.codi.core.criteria

import org.springframework.data.domain.PageRequest as SpringPageRequest
import com.musinsa.shared.domain.criteria.Pageable
import org.springframework.data.domain.Sort

private const val DEFAULT_PAGE = 1
private const val DEFAULT_SIZE = 10
private const val MAX_SIZE = 50

class PageRequest(
    page: Int = DEFAULT_PAGE,
    size: Int = DEFAULT_SIZE,
    private val direction: Sort.Direction = Sort.DEFAULT_DIRECTION
) : Pageable {
    override val page: Int by lazy {
        if (page < DEFAULT_PAGE) DEFAULT_PAGE else page
    }
    override val size: Int by lazy {
        if (size > MAX_SIZE) DEFAULT_SIZE else size
    }

    fun of(): SpringPageRequest {
        return SpringPageRequest.of(page - 1, size, direction, "created_at")
    }
}
