package com.musinsa.codi.application.find

import com.musinsa.codi.application.response.CodiResponse
import com.musinsa.codi.domain.entity.Codi
import com.musinsa.codi.domain.repository.CodiRepository
import com.musinsa.shared.application.bus.query.QueryHandler
import com.musinsa.shared.domain.criteria.Pageable
import com.musinsa.shared.domain.criteria.Specification

class FindCodiQueryHandler(
    private val repository: CodiRepository<Specification<Codi>, Pageable>
) : QueryHandler<FindCodiQuery, CodiResponse> {
    override fun invoke(query: FindCodiQuery): CodiResponse {
        return CodiResponse(
            repository.find(query.id)
        )
    }
}
