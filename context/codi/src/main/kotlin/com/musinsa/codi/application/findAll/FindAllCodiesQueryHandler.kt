package com.musinsa.codi.application.findAll

import com.musinsa.codi.application.response.CodiesResponse
import com.musinsa.codi.domain.entity.Codi
import com.musinsa.codi.domain.repository.CodiRepository
import com.musinsa.codi.domain.valueobject.Codies
import com.musinsa.shared.application.bus.query.QueryHandler
import com.musinsa.shared.domain.criteria.Pageable
import com.musinsa.shared.domain.criteria.Specification

class FindAllCodiesQueryHandler(
    private val repository: CodiRepository<Specification<Codi>, Pageable>
) : QueryHandler<FindAllCodiesQuery, CodiesResponse> {
    override fun invoke(query: FindAllCodiesQuery): CodiesResponse {
        return CodiesResponse(
            Codies(
                repository.findAll(query.spec, query.pageable)
            )
        )
    }
}
