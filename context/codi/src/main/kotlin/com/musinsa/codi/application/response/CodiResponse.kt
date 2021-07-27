package com.musinsa.codi.application.response

import com.musinsa.codi.domain.entity.Codi
import com.musinsa.shared.application.bus.query.Response

data class CodiResponse(
    val codi: Codi?
) : Response
