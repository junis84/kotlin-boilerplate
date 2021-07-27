package com.musinsa.codi.core.service

import com.musinsa.codi.application.find.FindCodiQuery
import com.musinsa.codi.application.findAll.FindAllCodiesQuery
import com.musinsa.codi.application.response.CodiResponse
import com.musinsa.codi.application.response.CodiesResponse
import com.musinsa.codi.core.criteria.PageRequest
import com.musinsa.codi.core.request.FindAllRequest
import com.musinsa.codi.domain.valueobject.CodiId
import com.musinsa.shared.application.bus.query.QueryBus
import org.springframework.stereotype.Service

@Service
class CodiService(
    private val queryBus: QueryBus
) {

    fun find(id: CodiId): CodiResponse? {
        return queryBus.ask(
            FindCodiQuery(id)
        )
    }

    fun findAll(request: FindAllRequest, pageable: PageRequest): CodiesResponse? {
        return queryBus.ask(
            FindAllCodiesQuery(
                request.toSpecification(),
                pageable
            )
        )
    }
}
