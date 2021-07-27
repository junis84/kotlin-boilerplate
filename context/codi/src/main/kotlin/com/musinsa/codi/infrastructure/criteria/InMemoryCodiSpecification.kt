package com.musinsa.codi.infrastructure.criteria

import com.musinsa.codi.domain.criteria.CodiSpecifications
import com.musinsa.codi.domain.entity.Codi
import com.musinsa.codi.domain.valueobject.CodiType
import com.musinsa.shared.domain.valueobject.SiteKind
import com.musinsa.shared.domain.valueobject.UseYn
import com.musinsa.shared.infrastructure.criteria.InMemorySpecification

class InMemoryCodiSpecification(
    predicate: (Codi) -> Boolean
) : InMemorySpecification<Codi>(predicate) {

    companion object : CodiSpecifications {
        override fun typeEqual(type: CodiType): InMemoryCodiSpecification {
            return InMemoryCodiSpecification {
                it.type.value == type.value
            }
        }

        override fun siteKindEqual(siteKind: SiteKind): InMemoryCodiSpecification {
            return InMemoryCodiSpecification {
                it.siteKind.value == siteKind.value
            }
        }

        override fun useYnEqual(useYn: UseYn): InMemoryCodiSpecification {
            return InMemoryCodiSpecification {
                it.useYn.value == useYn.value
            }
        }
    }
}
