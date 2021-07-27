package com.musinsa.codi.core.criteria

import com.musinsa.codi.core.model.QCodiModel.codiModel
import com.musinsa.codi.domain.criteria.CodiSpecifications
import com.musinsa.codi.domain.entity.Codi
import com.musinsa.codi.domain.valueobject.CodiType
import com.musinsa.shared.domain.valueobject.SiteKind
import com.musinsa.shared.domain.valueobject.UseYn
import com.musinsa.shared.infrastructure.criteria.QuerydslSpecification
import com.querydsl.core.BooleanBuilder
import com.querydsl.core.types.Predicate

class CodiSpecification(
    builder: BooleanBuilder = BooleanBuilder()
) : QuerydslSpecification<Codi>(builder) {

    constructor(predicate: Predicate) : this(BooleanBuilder(predicate))

    companion object : CodiSpecifications {
        override fun typeEqual(type: CodiType): CodiSpecification {
            return CodiSpecification(
                codiModel.type.eq(
                    type.value
                )
            )
        }

        override fun siteKindEqual(siteKind: SiteKind): CodiSpecification {
            return CodiSpecification(
                codiModel.siteKind.eq(
                    siteKind.value
                )
            )
        }

        override fun useYnEqual(useYn: UseYn): CodiSpecification {
            return CodiSpecification(
                codiModel.useYn.eq(
                    useYn.value
                )
            )
        }
    }
}
