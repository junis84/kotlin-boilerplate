package com.musinsa.codi.application.response

import com.musinsa.codi.domain.entity.Codi
import com.musinsa.codi.domain.valueobject.Codies
import com.musinsa.shared.application.bus.query.Response

data class CodiesResponse(
    val codies: Codies
) : Response, Iterable<Codi> {
    override fun iterator(): Iterator<Codi> {
        return codies.iterator()
    }
}
