package com.musinsa.codi.application.findAll

import com.musinsa.codi.application.response.CodiesResponse
import com.musinsa.codi.domain.entity.Codi
import com.musinsa.codi.domain.repository.CodiRepository
import com.musinsa.codi.domain.valueobject.Codies
import com.musinsa.shared.domain.criteria.Pageable
import com.musinsa.shared.domain.criteria.Specification
import io.kotest.core.spec.style.DescribeSpec
import io.kotest.matchers.shouldBe
import io.mockk.every
import io.mockk.mockk

class FindAllCodiesQueryHandlerSpec : DescribeSpec(
    {
        val spec = mockk<Specification<Codi>>()
        val pageable = mockk<Pageable>()
        val query = FindAllCodiesQuery(spec, pageable)
        val repository = mockk<CodiRepository<Specification<Codi>, Pageable>>()
        val handler = FindAllCodiesQueryHandler(
            repository
        )
        val codiList = mockk<List<Codi>>()
        every { repository.findAll(spec, pageable) } returns codiList
        val codies = Codies(codiList)

        describe("invoke()") {
            it("should return a CodiesResponse") {
                handler(query) shouldBe CodiesResponse(codies)
            }
        }
    }
)
