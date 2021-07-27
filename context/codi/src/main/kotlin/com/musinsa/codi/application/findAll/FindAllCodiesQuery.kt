package com.musinsa.codi.application.findAll

import com.musinsa.codi.domain.entity.Codi
import com.musinsa.shared.application.bus.query.Query
import com.musinsa.shared.domain.criteria.Pageable
import com.musinsa.shared.domain.criteria.Specification

data class FindAllCodiesQuery(
    val spec: Specification<Codi>,
    val pageable: Pageable
) : Query
