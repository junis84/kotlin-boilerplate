package com.musinsa.codi.domain.entity

import com.musinsa.codi.faker.clone
import com.musinsa.codi.faker.codi
import io.kotest.core.spec.style.DescribeSpec
import io.kotest.matchers.shouldBe
import io.kotest.matchers.shouldNotBe
import io.kotest.property.arbitrary.arbitrary
import io.kotest.property.checkAll

class CodiSpec : DescribeSpec(
    {
        val codiArb = arbitrary { codi() }

        describe("equals()") {
            it("should be able equivalence comparisons") {
                codiArb.checkAll(100) { codi ->
                    val nullObject = null
                    (codi == codi) shouldBe true
                    (codi == nullObject) shouldBe false
                    (codi.equals(object {})) shouldBe false

                    val clone = codi.clone()
                    (codi === clone) shouldBe false
                    (codi == clone) shouldBe true

                    val other = codi()
                    (codi == other) shouldBe false
                }
            }
        }

        describe("hashCode()") {
            it("should return same value if some objects are checked for equality") {
                codiArb.checkAll(100) { codi ->
                    val clone = codi.clone()
                    (codi == clone) shouldBe true
                    codi.hashCode() shouldBe clone.hashCode()

                    val other = codi()
                    codi.hashCode() shouldNotBe other.hashCode()
                }
            }
        }

        describe("toString()") {
            it("should return string of properties") {
                codiArb.checkAll(100) { codi ->
                    with(codi) {
                        val expected = "Codi(id=$id, type=$type, sourceId=$sourceId, thumbnail=$thumbnail, " +
                            "viewCount=$viewCount, commentCount=$commentCount, relatedGoods=$relatedGoods, " +
                            "labels=$labels, siteKind=$siteKind, sex=$sex, useYn=$useYn, createdAt=$createdAt, " +
                            "updatedAt=$updatedAt, lastCommentAddedAt=$lastCommentAddedAt)"
                        toString() shouldBe expected
                    }
                }
            }
        }
    }
)
