package com.musinsa.codi.infrastructure.persistence

import com.musinsa.codi.domain.entity.Codi
import com.musinsa.codi.domain.repository.CodiRepository
import com.musinsa.codi.domain.valueobject.CodiId
import com.musinsa.shared.domain.criteria.Pageable
import com.musinsa.shared.infrastructure.criteria.InMemorySpecification

class InMemoryCodiRepository(
    private val records: MutableList<Codi> = mutableListOf()
) : CodiRepository<InMemorySpecification<Codi>, Pageable> {
    override fun save(codi: Codi): Codi {
        records.add(codi)
        return codi
    }

    override fun find(id: CodiId): Codi? {
        return records.firstOrNull {
            it.id == id
        }
    }

    override fun findAll(spec: InMemorySpecification<Codi>, pageable: Pageable): List<Codi> {
        return records.asSequence()
            .filter {
                val predicate = spec.toPredicate()
                predicate(it)
            }.drop(
                (pageable.page - 1) * pageable.size
            ).take(pageable.size)
            .toList()
    }
}
