package com.musinsa.codi.domain.entity

import com.musinsa.codi.faker.clone
import com.musinsa.codi.faker.goods
import io.kotest.core.spec.style.DescribeSpec
import io.kotest.matchers.shouldBe
import io.kotest.matchers.shouldNotBe
import io.kotest.property.arbitrary.arbitrary
import io.kotest.property.checkAll

class GoodsSpec : DescribeSpec(
    {
        val goodsArb = arbitrary { goods() }

        describe("equals()") {
            it("should be able equivalence comparisons") {
                goodsArb.checkAll(100) { goods ->
                    val nullObject = null
                    (goods == goods) shouldBe true
                    (goods == nullObject) shouldBe false
                    (goods.equals(object {})) shouldBe false

                    val clone = goods.clone()
                    (goods === clone) shouldBe false
                    (goods == clone) shouldBe true

                    val other = goods()
                    (goods == other) shouldBe false
                }
            }
        }

        describe("hashCode()") {
            it("should return same value if some objects are checked for equality") {
                goodsArb.checkAll(100) { goods ->
                    val clone = goods.clone()
                    (goods == clone) shouldBe true
                    goods.hashCode() shouldBe clone.hashCode()

                    val other = goods()
                    goods.hashCode() shouldNotBe other.hashCode()
                }
            }
        }

        describe("toString()") {
            it("should return string of properties") {
                goodsArb.checkAll(100) { goods ->
                    with(goods) {
                        val expected =
                            "Goods(no=$no, seqNo=$seqNo, name=$name, brand=$brand, itemCategory=$itemCategory, " +
                                "image=$image, status=$status, price=$price, netPrice=$netPrice, " +
                                "discountRate=$discountRate, color=$color, createdAt=$createdAt)"
                        toString() shouldBe expected
                    }
                }
            }
        }
    }
)
