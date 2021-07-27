package com.musinsa.codi.domain.entity

import com.musinsa.shared.domain.entity.Entity
import com.musinsa.shared.domain.valueobject.BrandCode
import com.musinsa.shared.domain.valueobject.BrandName

class Brand(
    val code: BrandCode,
    val name: BrandName
) : Entity<BrandCode>(code) {
    override fun toString(): String {
        return "Brand(code=$code, name=$name)"
    }
}
