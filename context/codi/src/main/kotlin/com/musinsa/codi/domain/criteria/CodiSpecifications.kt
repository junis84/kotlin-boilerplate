package com.musinsa.codi.domain.criteria

import com.musinsa.codi.domain.entity.Codi
import com.musinsa.codi.domain.valueobject.CodiType
import com.musinsa.shared.domain.criteria.Specification
import com.musinsa.shared.domain.valueobject.SiteKind
import com.musinsa.shared.domain.valueobject.UseYn

interface CodiSpecifications : Specification<Codi> {
    fun typeEqual(type: CodiType): Specification<Codi>
    fun siteKindEqual(siteKind: SiteKind): Specification<Codi>
    fun useYnEqual(useYn: UseYn): Specification<Codi>
}
