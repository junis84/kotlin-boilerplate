package com.musinsa.codi.infrastructure.criteria

import com.musinsa.codi.domain.entity.Codi
import com.musinsa.codi.domain.valueobject.CodiType
import com.musinsa.shared.domain.valueobject.SiteKind
import com.musinsa.shared.domain.valueobject.UseYn
import io.kotest.core.spec.style.DescribeSpec
import io.kotest.matchers.shouldBe
import io.mockk.every
import io.mockk.mockk

class InMemoryCodiSpecificationSpec : DescribeSpec(
    {
        describe("InMemoryCodiSpecification.typeEqual()") {
            it("should return a predicate for checking equal type") {
                val spec = InMemoryCodiSpecification.typeEqual(
                    CodiType.STREET_SNAP
                )

                val codi = mockk<Codi>()
                every { codi.type } returns CodiType.STREET_SNAP
                (spec.toPredicate())(codi) shouldBe true

                every { codi.type } returns CodiType.BRAND_SNAP
                (spec.toPredicate())(codi) shouldBe false
            }
        }

        describe("InMemoryCodiSpecification.siteKindEqual()") {
            it("should return a predicate for checking equal siteKind") {
                val spec = InMemoryCodiSpecification.siteKindEqual(
                    SiteKind.MUSINSA
                )

                val codi = mockk<Codi>()
                every { codi.siteKind } returns SiteKind.MUSINSA
                (spec.toPredicate())(codi) shouldBe true

                every { codi.siteKind } returns SiteKind.WUSINSA
                (spec.toPredicate())(codi) shouldBe false
            }
        }

        describe("InMemoryCodiSpecification.useYnEqual()") {
            it("should return a predicate for checking equal useYn") {
                val spec = InMemoryCodiSpecification.useYnEqual(
                    UseYn.YES
                )

                val codi = mockk<Codi>()
                every { codi.useYn } returns UseYn.YES
                (spec.toPredicate())(codi) shouldBe true

                every { codi.useYn } returns UseYn.NO
                (spec.toPredicate())(codi) shouldBe false
            }
        }
    }
)
