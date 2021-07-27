package com.musinsa.codi.core.request

import com.musinsa.codi.core.criteria.CodiSpecification
import com.musinsa.codi.domain.valueobject.CodiType
import com.musinsa.shared.domain.valueobject.SiteKind
import com.musinsa.shared.domain.valueobject.UseYn

data class FindAllRequest(
    val type: String?,
    val siteKind: Int = SiteKind.ALL.value,
    val useYn: String = UseYn.YES.value
) {
    fun toSpecification(): CodiSpecification {
        val spec = CodiSpecification.useYnEqual(
            UseYn.of(useYn)
        )
        if (type !== null) {
            spec.and(
                CodiSpecification.typeEqual(
                    CodiType.of(type.lowercase())
                )
            )
        }
        if (siteKind != SiteKind.ALL.value) {
            spec.and(
                CodiSpecification.siteKindEqual(
                    SiteKind.of(siteKind)
                )
            )
        }
        return spec
    }
}
