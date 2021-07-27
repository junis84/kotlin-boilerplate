package com.musinsa.codi.api.controller.v1

import com.musinsa.codi.application.response.CodiResponse
import com.musinsa.codi.application.response.CodiesResponse
import com.musinsa.codi.core.criteria.PageRequest
import com.musinsa.codi.core.request.FindAllRequest
import com.musinsa.codi.core.service.CodiService
import com.musinsa.codi.domain.valueobject.CodiId
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RestController

@RestController
class CodiController(
    private val codiService: CodiService
) {

    @GetMapping("/v1/codi/{id}")
    fun find(@PathVariable("id") id: String): CodiResponse? {
        return codiService.find(
            CodiId(id)
        )
    }

    @GetMapping("/v1/codies")
    fun findAll(
        request: FindAllRequest,
        pageable: PageRequest
    ): CodiesResponse? {
        return codiService.findAll(request, pageable)
    }
}
