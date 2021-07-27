package com.musinsa.codi.application.find

import com.musinsa.codi.application.response.CodiResponse
import com.musinsa.codi.domain.entity.Codi
import com.musinsa.codi.domain.repository.CodiRepository
import com.musinsa.codi.domain.valueobject.CodiId
import com.musinsa.shared.domain.criteria.Pageable
import com.musinsa.shared.domain.criteria.Specification
import io.kotest.core.spec.style.DescribeSpec
import io.kotest.matchers.shouldBe
import io.mockk.every
import io.mockk.mockk

class FindCodiQueryHandlerSpec : DescribeSpec(
    {
        val codiId = mockk<CodiId>()
        val query = FindCodiQuery(codiId)
        val repository = mockk<CodiRepository<Specification<Codi>, Pageable>>()
        val handler = FindCodiQueryHandler(
            repository
        )
        val codi = mockk<Codi>()
        every { repository.find(codiId) } returns codi

        describe("invoke()") {
            it("should return a CodiResponse") {
                handler(query) shouldBe CodiResponse(codi)
            }
        }
    }
)
