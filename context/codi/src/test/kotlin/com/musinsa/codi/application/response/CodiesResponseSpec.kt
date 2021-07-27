package com.musinsa.codi.application.response

import com.musinsa.codi.domain.entity.Codi
import com.musinsa.codi.domain.valueobject.Codies
import io.kotest.core.spec.style.DescribeSpec
import io.kotest.matchers.should
import io.kotest.matchers.types.beInstanceOf
import io.mockk.every
import io.mockk.mockk

class CodiesResponseSpec : DescribeSpec(
    {
        val codies = mockk<Codies>()
        every { codies.iterator() } returns listOf(mockk<Codi>()).iterator()
        val response = CodiesResponse(codies)
        describe("iterator()") {
            it("should return iterator of codi list") {
                response.iterator() should beInstanceOf<Iterator<Codi>>()
                response.map {
                    it should beInstanceOf<Codi>()
                }
            }
        }
    }
)
