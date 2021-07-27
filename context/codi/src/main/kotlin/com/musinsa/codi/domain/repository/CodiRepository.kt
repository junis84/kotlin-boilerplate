package com.musinsa.codi.domain.repository

import com.musinsa.codi.domain.entity.Codi
import com.musinsa.codi.domain.valueobject.CodiId
import com.musinsa.shared.domain.criteria.Pageable
import com.musinsa.shared.domain.criteria.Specification

interface CodiRepository<S : Specification<*>, P : Pageable> {
    fun save(codi: Codi): Codi
    fun find(id: CodiId): Codi?
    fun findAll(spec: S, pageable: P): List<Codi>
}
