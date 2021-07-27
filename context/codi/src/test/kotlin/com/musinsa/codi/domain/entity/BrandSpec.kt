package com.musinsa.codi.domain.entity

import com.musinsa.codi.faker.brand
import com.musinsa.codi.faker.clone
import io.kotest.core.spec.style.DescribeSpec
import io.kotest.matchers.shouldBe
import io.kotest.matchers.shouldNotBe
import io.kotest.property.arbitrary.arbitrary
import io.kotest.property.checkAll

class BrandSpec : DescribeSpec(
    {
        val brandArb = arbitrary { brand() }

        describe("equals()") {
            it("should be able equivalence comparisons") {
                brandArb.checkAll(100) { brand ->
                    val nullObject = null
                    (brand == brand) shouldBe true
                    (brand == nullObject) shouldBe false
                    (brand.equals(object {})) shouldBe false

                    val clone = brand.clone()
                    (brand === clone) shouldBe false
                    (brand == clone) shouldBe true

                    val other = brand()
                    (brand == other) shouldBe false
                }
            }
        }

        describe("hashCode()") {
            it("should return same value if some objects are checked for equality") {
                brandArb.checkAll(100) { brand ->
                    val clone = brand.clone()
                    (brand == clone) shouldBe true
                    brand.hashCode() shouldBe clone.hashCode()

                    val other = brand()
                    brand.hashCode() shouldNotBe other.hashCode()
                }
            }
        }

        describe("toString()") {
            it("should return string of properties") {
                brandArb.checkAll(100) { brand ->
                    with(brand) {
                        toString() shouldBe "Brand(code=$code, name=$name)"
                    }
                }
            }
        }
    }
)
